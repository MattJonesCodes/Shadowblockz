package com.shadowblockz.eggemall.events;

import com.shadowblockz.eggemall.Main;
import com.shadowblockz.eggemall.managers.GameManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener
{
    /**
     * Fired when the player leaves the server.
     * @param event the quit event.
     */
    @EventHandler
    private void playerQuitEvent(PlayerQuitEvent event)
    {
        Player player = event.getPlayer();
        event.setQuitMessage(ChatColor.GRAY + "[-]" + player.getName());

        // Remove them from any local lists.
        if(Main.gameManager.GetQueue().contains(player))
            Main.gameManager.RemovePlayerFromQueue(player);

        if(Main.gameManager.CurrentGameState != GameManager.GameState.InLobby)
        {
            if(Main.gameManager.GetCurrentGame().GetPlayers().contains(player))
                Main.gameManager.GetCurrentGame().RemovePlayer(player);
            if(Main.gameManager.GetCurrentGame().GetSpectators().contains(player))
                Main.gameManager.GetCurrentGame().RemoveSpectator(player);
        }
    }
}
