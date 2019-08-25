package com.shadowblockz.eggemall.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.LeavesDecayEvent;

public class LeafDecay implements Listener
{
    /**
     * Fired when a leaf decays.
     * @param event the leaf decay event.
     */
    @EventHandler
    private void leafDecayEvent(LeavesDecayEvent event)
    {
        event.setCancelled(true);
    }
}
