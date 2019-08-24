package com.shadowblockz.eggemall.utilities;

import com.shadowblockz.eggemall.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class FormattedChatMessaged
{
    /**
     * Prints out the end game message (pre-formatted)
     * @param place1 the player whom is in first place.
     * @param place2 the player whom is in second place.
     * @param place3 the player whom is in third place.
     */
    public static void EndGameMessage(Player place1, Player place2, Player place3)
    {
        Bukkit.broadcastMessage("\n"
                + ChatColor.LIGHT_PURPLE + ChatColor.STRIKETHROUGH
                + "                                                                    "
                + ChatColor.RESET + "\n \n"
                + ChatColor.RED + ChatColor.BOLD + "GAME OVER!" + "\n \n"
                + "\n \n"
                + ChatColor.GOLD + "1st Place: " + ChatColor.RESET
                + Main.chat.getGroupPrefix(place1.getWorld().getName(), Main.perms.getPrimaryGroup(place1).toString())
                + place1.getName() + ChatColor.RESET + " - " + Main.gameManager.GetCurrentGame().GetPlayerScore(place1) + " Kills"
                + ChatColor.DARK_AQUA + "2nd Place: " + ChatColor.RESET
                + Main.chat.getGroupPrefix(place2.getWorld().getName(), Main.perms.getPrimaryGroup(place2).toString())
                + place2.getName() + ChatColor.RESET + " - " + Main.gameManager.GetCurrentGame().GetPlayerScore(place2) + " Kills"
                + ChatColor.BLUE + "3rd Place: " + ChatColor.RESET
                + Main.chat.getGroupPrefix(place3.getWorld().getName(), Main.perms.getPrimaryGroup(place3).toString())
                + place3.getName() + ChatColor.RESET + " - " + Main.gameManager.GetCurrentGame().GetPlayerScore(place3) + " Kills"
                + "\n" + ChatColor.LIGHT_PURPLE + ChatColor.STRIKETHROUGH
                + "                                                                    "
                + ChatColor.RESET + "\n \n");
    }

}
