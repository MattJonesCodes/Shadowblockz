package com.shadowblockz.eggemall.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherChange implements Listener
{
    /**
     * Fired when the weather for a world changes.
     * @param event the weather change event.
     */
    @EventHandler
    private void weatherChangeEvent(WeatherChangeEvent event)
    {
        event.setCancelled(event.toWeatherState());
    }
}
