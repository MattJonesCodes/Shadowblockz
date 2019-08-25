package com.shadowblockz.eggemall.events;

import com.nametagedit.plugin.NametagEdit;
import com.shadowblockz.eggemall.Main;
import com.shadowblockz.eggemall.managers.GameManager;
import com.shadowblockz.eggemall.utilities.NameTags;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener
{
    /**
     * Fired when a player joins the server.
     * @param event the event
     */
    @EventHandler
    private void playerJoinEvent(PlayerJoinEvent event)
    {
        Player player = event.getPlayer();
        event.setJoinMessage(ChatColor.GRAY + "[+]" + player.getName());

        // Set the players nametag, updates their profile in the database.
        NametagEdit.getApi().setPrefix(player, NameTags.GetNametag(player));
        Main.mysql.UpdateID(player);

        // Handle the player join for the appropriate game state.
        if(Main.gameManager.CurrentGameState == GameManager.GameState.InLobby)
            Main.gameManager.AddPlayerToQueue(player);
        else if(Main.gameManager.CurrentGameState == GameManager.GameState.PreGame)
            Main.gameManager.GetCurrentGame().AddSpectator(player);
        else if(Main.gameManager.CurrentGameState == GameManager.GameState.InGame)
            Main.gameManager.GetCurrentGame().AddSpectator(player);

        // Check if we can start the game.
        if(Main.gameManager.CurrentGameState == GameManager.GameState.InLobby)
        {
            if(Main.gameManager.CanStartGame())
                Main.lobbyGameStartTimer.StartTimer(); // Start countdown to move into pre-game phase.
            else
                Bukkit.getServer().broadcastMessage(ChatColor.YELLOW + "[EEA] " + ChatColor.RESET + (4 - Bukkit.getOnlinePlayers().size()) + " more player(s) required to start a game!");
        }
    }
}
