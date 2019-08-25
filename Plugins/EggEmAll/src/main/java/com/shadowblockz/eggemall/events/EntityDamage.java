package com.shadowblockz.eggemall.events;

import com.shadowblockz.eggemall.Main;
import com.shadowblockz.eggemall.managers.GameManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamage implements Listener
{
    /**
     * Fired when an entity takes damage.
     * @param event
     */
    @EventHandler
    public void entityDamageEvent(EntityDamageEvent event){
        if(!(event.getEntity() instanceof Player))
            return;

        // Disable damage in lobby.
        if(Main.gameManager.CurrentGameState == GameManager.GameState.InLobby)
            event.setCancelled(true);
    }
}
