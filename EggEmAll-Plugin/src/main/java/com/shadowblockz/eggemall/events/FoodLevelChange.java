package com.shadowblockz.eggemall.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodLevelChange implements Listener
{
    /**
     * Fired when the food level of a player changes.
     * @param event the food level change event.
     */
    @EventHandler
    private void foodLevelChangeEvent(FoodLevelChangeEvent event)
    {
        event.setFoodLevel(20);
    }
}
