package com.shadowblockz.eggemall.events;

import com.shadowblockz.eggemall.Main;
import com.shadowblockz.eggemall.managers.GameManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove implements Listener
{
    /**
     * Fired when the player moves.
     * Used for freezing players pre-game.
     * @param event
     */
    @EventHandler
    private void playerMoveEvent(PlayerMoveEvent event)
    {
        if(Main.gameManager.CurrentGameState == GameManager.GameState.PreGame)
        {
            if (Main.gameManager.GetCurrentGame().GetPlayers().contains(event.getPlayer()))
                event.setCancelled(true);
        }
    }
}
