package com.shadowblockz.eggemall.events;

import com.shadowblockz.eggemall.game.Locations;
import com.shadowblockz.eggemall.game.Items;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.util.Collections;

public class PlayerRespawn implements Listener
{
    /**
     * Fired when a player respawns.
     * @param event the respawn event.
     */
    @EventHandler
    private void playerRespawnEvent(PlayerRespawnEvent event)
    {
        if(Locations.GameSpawns.size() <= 10)
        {
            Locations.loadGameSpawns();
            Collections.shuffle(Locations.GameSpawns);
            event.setRespawnLocation(Locations.GameSpawns.get(0));
            Locations.GameSpawns.remove(0);
            Items.GivePlayerInGameItems(event.getPlayer());
        }
        else
        {
            event.setRespawnLocation(Locations.GameSpawns.get(0));
            Locations.GameSpawns.remove(0);
            Items.GivePlayerInGameItems(event.getPlayer());
        }
    }
}
