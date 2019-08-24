package com.shadowblockz.eggemall.events;

import com.shadowblockz.eggemall.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerLogin implements Listener
{
    /**
     * Fired when the player attempts to logon
     * to the server. (Used during events).
     * @param event the login event.
     */
    @EventHandler
    private void playerLoginEvent(PlayerLoginEvent event)
    {
        Player player = event.getPlayer();
        if(!(Main.mysql.GetVotes(player) >= 30))
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "You need to have voted at least 30 times during the month of June to take part in this tournament!");
        else
            event.allow();
    }
}
