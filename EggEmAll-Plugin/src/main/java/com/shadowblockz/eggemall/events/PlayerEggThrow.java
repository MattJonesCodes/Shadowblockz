package com.shadowblockz.eggemall.events;

import com.shadowblockz.eggemall.Main;
import com.shadowblockz.eggemall.managers.GameManager;
import com.shadowblockz.eggemall.utilities.DoFirework;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEggThrowEvent;

public class PlayerEggThrow implements Listener
{
    /**
     * Funnily enough, this is fired when a
     * player throws an egg entity.
     * @param event the egg throw event.
     */
    @EventHandler
    private void playerEggThrowEvent(PlayerEggThrowEvent event)
    {
        if(Main.gameManager.CurrentGameState != GameManager.GameState.InGame)
            return;

        event.setHatching(false);
        DoFirework fw = new DoFirework();
        fw.shoot(event.getEgg().getLocation());
    }
}
