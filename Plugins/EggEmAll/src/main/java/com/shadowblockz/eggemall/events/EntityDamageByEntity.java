package com.shadowblockz.eggemall.events;

import com.shadowblockz.eggemall.Main;
import com.shadowblockz.eggemall.game.Items;
import com.shadowblockz.eggemall.managers.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageByEntity implements Listener
{
    /**
     * Fired when an entity is damaged by an entity.
     * Used to process kills by eggs.
     * @param event the entity damage by entity event.
     */
    @EventHandler
    private void entityDamageByEntityEvent(EntityDamageByEntityEvent event)
    {
        // Handle entity vs entity within the lobby.
        if(Main.gameManager.CurrentGameState == GameManager.GameState.InLobby)
            event.setCancelled(true);

        // Handle Entity vs Entity within a game.
        if(Main.gameManager.CurrentGameState != GameManager.GameState.InGame)
        {
            // Must be a player damaged by an egg.
            if (!(event.getDamager() instanceof org.bukkit.entity.Egg) || !(event.getEntity() instanceof Player))
                return;

            Egg damageEgg = (Egg) event.getDamager();
            Player shooter = (Player) damageEgg.getShooter();
            Player victim = (Player) event.getEntity();

            // Null checking.
            if (victim == null || shooter == null)
                return;

            // The player cannot kill themselves.
            if (victim == shooter)
                return;

            // Process the kill.
            victim.setHealth(0);
            shooter.getInventory().addItem(Items.GetEggItem());
            Bukkit.broadcastMessage(ChatColor.RED + shooter.getName() + ChatColor.GREEN + " egged " + ChatColor.RED + victim.getName());

            Main.mysql.GivePlayerPoints(shooter, 1);
            Main.gameManager.GetCurrentGame().IncreasePlayerScore(shooter, 1);

            // Check if they've won the game, if so end.
            if (Main.gameManager.GetCurrentGame().GetPlayerScore(shooter) >= Main.configManager.GetGameScoreCap())
                Main.gameManager.GetCurrentGame().End();
        }
    }
}
