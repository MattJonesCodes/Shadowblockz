/*
    Copyright (C) 2019  Matthew Jones

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.shadowblockz.eggemall;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.shadowblockz.eggemall.events.*;
import com.shadowblockz.eggemall.managers.ConfigurationManager;
import com.shadowblockz.eggemall.managers.DatabaseManager;
import com.shadowblockz.eggemall.managers.GameManager;
import com.shadowblockz.eggemall.managers.ServerScoreboardManager;
import com.shadowblockz.eggemall.utilities.timers.EndGameCooldownTimer;
import com.shadowblockz.eggemall.utilities.timers.InGameTimer;
import com.shadowblockz.eggemall.utilities.timers.LobbyGameStartTimer;
import com.shadowblockz.eggemall.utilities.timers.PreGameTimer;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.shadowblockz.eggemall.utilities.holograms.Placeholders;

public class Main extends JavaPlugin implements PluginMessageListener
{
	public static Main plugin = null;
    public static Chat chat = null;
	public static Permission perms = null;
	public static DatabaseManager mysql = null;
	public static GameManager gameManager = null;
	public static ConfigurationManager configManager = null;

	public static EndGameCooldownTimer endGameCooldownTimer = null;
	public static InGameTimer inGameTimer = null;
	public static LobbyGameStartTimer lobbyGameStartTimer = null;
	public static PreGameTimer preGameTimer = null;

	/**
	 * Loads the plugin.
	 */
	@Override
	public void onEnable()
	{
		plugin = this;

		// Multiple prints to ensure log stays tidy (timestamps)
		System.out.println("[EEA] EggEmAll");
		System.out.println("[EEA] Version: 0.1");
		System.out.println("[EEA] Author: MattJonesCodes");
		System.out.println("[EEA] Loading plugin... ");

		// Sets up configuration.
		getConfig().options().copyDefaults(true);
		saveConfig();

		// Create new instances of managers
		configManager = new ConfigurationManager();
		mysql = new DatabaseManager();
		gameManager = new GameManager();
		endGameCooldownTimer = new EndGameCooldownTimer();
		inGameTimer = new InGameTimer();
		lobbyGameStartTimer = new LobbyGameStartTimer();
		preGameTimer = new PreGameTimer();

		// Setup permissions handler.
		if(!setupPermissions())
			System.out.println("[EEA] !!ERROR!! Failed to load permissions configuration.");

		// Setup chat handler.
		if(!setupChat())
			System.out.println("[EEA] !!ERROR!! Failed to load chat configuration.");

		// Setup database.
		mysql.SetupDB();

		// Register event handlers.
		getServer().getPluginManager().registerEvents(new Commands(), this);
		getServer().getPluginManager().registerEvents(new AsyncPlayerChat(), this);
		getServer().getPluginManager().registerEvents(new EntityDamage(), this);
		getServer().getPluginManager().registerEvents(new EntityDamageByEntity(), this);
		getServer().getPluginManager().registerEvents(new FoodLevelChange(), this);
		getServer().getPluginManager().registerEvents(new InventoryClick(), this);
		getServer().getPluginManager().registerEvents(new LeafDecay(), this);
		getServer().getPluginManager().registerEvents(new PlayerDeath(), this);
		getServer().getPluginManager().registerEvents(new PlayerDropItem(), this);
		getServer().getPluginManager().registerEvents(new PlayerEggThrow(), this);
		getServer().getPluginManager().registerEvents(new PlayerInteract(), this);
		getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
		getServer().getPluginManager().registerEvents(new PlayerLogin(), this);
		getServer().getPluginManager().registerEvents(new PlayerMove(), this);
		getServer().getPluginManager().registerEvents(new PlayerQuit(), this);
		getServer().getPluginManager().registerEvents(new PlayerRespawn(), this);
		getServer().getPluginManager().registerEvents(new WeatherChange(), this);

		// Register in/out plugin channels.
		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);
		
		// Sets up placeholder api for holograms.
        if(Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI"))
        	new Placeholders(this).hook();
        else
        	System.out.println("[EEA] !!INFO!! PlaceholderAPI was not found, install for full functionality.");

		ServerScoreboardManager.createLobbyScoreboard();
		System.out.println("[EEA] Plugin loaded!");
	}
	
    /**
	 * Unloads plugin.
	 */
	@Override
	public void onDisable()
	{
		mysql.CloseDB();
		gameManager.GetCurrentGame().End();
		gameManager.EndGame();
	}

	/**
	 * Sets up permissions manager.
	 * @return True if successful.
	 */
	private boolean setupPermissions()
	{
        RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (permissionProvider != null) 
            perms = permissionProvider.getProvider();
        return (perms != null);
    }

	/**
	 * Sets up chat manager.
	 * @return True if successful.
	 */
	private boolean setupChat()
	{
        RegisteredServiceProvider<Chat> chatProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.chat.Chat.class);
        if (chatProvider != null) 
            chat = chatProvider.getProvider();
        return (chat != null);
    }

	/**
	 * Sets up Plugin Message Channel
	 * for work with BungeeCord.
	 */
	public static String serverName;
	public void onPluginMessageReceived(String channel, Player player, byte[] message) 
	{
		if (!channel.equals("BungeeCord")) 
			return;

		DataInputStream in = new DataInputStream(new ByteArrayInputStream(message));
		try 
		{
			String subchannel = in.readUTF();
			if (subchannel.equals("GetServer")) 
				serverName = in.readUTF();
		} 
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}
}
