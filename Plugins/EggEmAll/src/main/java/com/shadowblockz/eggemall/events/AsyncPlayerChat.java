package com.shadowblockz.eggemall.events;

import com.shadowblockz.eggemall.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncPlayerChat implements Listener
{
    /**
     * Fired when a player sends a message to the chat.
     * @param e the player chat event.
     */
    @EventHandler
    public void asyncPlayerChatEvent(AsyncPlayerChatEvent e)
    {
        Player p = e.getPlayer();
        String groupPrefix = Main.chat.getGroupPrefix(p.getWorld().getName(), Main.perms.getPrimaryGroup(p).toString());
        e.setFormat(groupPrefix + p.getDisplayName() + ChatColor.RESET + ChatColor.DARK_GRAY + ": " + ChatColor.WHITE + "%2$s");
    }
}
