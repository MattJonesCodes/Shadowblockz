package com.shadowblockz.eggemall.events;

import com.shadowblockz.eggemall.Main;
import com.shadowblockz.eggemall.managers.GameManager;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class PlayerInteract implements Listener
{
    /**
     * Fired when a player interacts with something
     * within their inventory. Used to prevent eggs
     * firing during the pre-game.
     * @param event
     */
    @EventHandler
    private void playerInteractEvent(PlayerInteractEvent event)
    {
        Player player = event.getPlayer();
        // Handle interactions while in Pregame.
        if(Main.gameManager.CurrentGameState == GameManager.GameState.PreGame)
        {
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                event.setCancelled(true);
        }

        // Handle ReturnToLobby interactions.
        if (player.getItemInHand().getType() == Material.SLIME_BALL)
        {
            if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || event.getAction().equals(Action.RIGHT_CLICK_AIR))
            {
                event.setCancelled(true);
                try
                {
                    ByteArrayOutputStream b = new ByteArrayOutputStream();
                    DataOutputStream out = new DataOutputStream(b);
                    out.writeUTF("Connect");
                    out.writeUTF("hub");
                    player.sendPluginMessage(Main.plugin, "BungeeCord", b.toByteArray());
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        }

        // Handle soil interactions
        if (event.getAction() == Action.PHYSICAL) // When you jump on it.
        {
            Block block = event.getClickedBlock();
            if (block == null) return;
            // If the block is farmland (soil)
            if (block.getType() == Material.SOIL)
            {
                // Deny event and set the block
                event.setUseInteractedBlock(org.bukkit.event.Event.Result.DENY);
                event.setCancelled(true);
                block.setTypeIdAndData(block.getType().getId(), block.getData(), true);
            }
        }
    }
}
