package com.shadowblockz.eggemall.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerDropItem implements Listener
{
    /**
     * Fired when a player drops an item.
     * @param event the player drop item event.
     */
    @EventHandler
    private void playerDropItemEvent(PlayerDropItemEvent event)
    {
        event.setCancelled(true);
    }
}
