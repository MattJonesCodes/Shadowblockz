package com.shadowblockz.eggemall.events;

import com.shadowblockz.eggemall.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Commands implements CommandExecutor, Listener
{
	/**
	 * Handles player commands.
	 * @param sender the player whom sent it.
	 * @param cmd the command itself.
	 * @param label the label.
	 * @param args the command arguments.
	 * @return true if the command was processed successfully.
	 */
    @Override
   	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args)
	{
    	if(sender instanceof Player)
		{
    		if(cmd.getName().equalsIgnoreCase("queue"))
			{
  		  		Player player = (Player) sender;
  		  		player.sendMessage(ChatColor.YELLOW + "[EEA] " + ChatColor.RESET + "Your position in the queue is currently" + (Main.gameManager.GetQueue().indexOf(player) + 1));

	  			if((Main.gameManager.GetQueue().indexOf(player) + 1) > 8)
    				player.sendMessage(ChatColor.YELLOW + "[EEA] " + ChatColor.RESET + "You will most likley be spectating next round.");
				else 
    				player.sendMessage(ChatColor.YELLOW + "[EEA] " + ChatColor.RESET + "You will most likley be playing next round.");
    		 }
    	 }
		 else
		 	return false; // Error, has to be a player.

		return true; 	
	}
}
