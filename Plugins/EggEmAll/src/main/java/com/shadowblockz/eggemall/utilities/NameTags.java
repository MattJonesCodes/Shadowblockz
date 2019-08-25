package com.shadowblockz.eggemall.utilities;

import com.shadowblockz.eggemall.Main;
import org.bukkit.entity.Player;

public class NameTags
{
    /**
     * Gets the nametag for the specified player.
     * @param player the specified player.
     * @return the nametag appropriate for them.
     */
    public static String GetNametag(Player player)
    {
        if(Main.perms.getPrimaryGroup(player).equals("default"))
            return "�7";
        else if(Main.perms.getPrimaryGroup(player).equals( "VIP"))
            return "�a";
        else if(Main.perms.getPrimaryGroup(player).equals("VIP+"))
            return "�b";
        else if(Main.perms.getPrimaryGroup(player).equals("MVP"))
            return "�d";
        else if(Main.perms.getPrimaryGroup(player).equals("Helper"))
            return "�6";
        else if(Main.perms.getPrimaryGroup(player).equals("Moderator"))
            return "�9";
        else if(Main.perms.getPrimaryGroup(player).equals("Admin"))
            return "�c";
        else if(Main.perms.getPrimaryGroup(player).equals("Owner"))
            return "�c";
        else
            return "�7";
    }

}
