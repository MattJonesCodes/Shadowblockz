package com.shadowblockz.eggemall.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClick implements Listener
{
    /**
     * Fired when a player clicks an item in their
     * inventory.
     * @param event the inventory click event.
     */
    @EventHandler
    public void inventoryClickEvent(InventoryClickEvent event)
    {
        if(event.getWhoClicked() instanceof Player)
            event.setCancelled(true);
    }
}
