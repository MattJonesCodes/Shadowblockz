package com.shadowblockz.eggemall.events;

import com.shadowblockz.eggemall.Main;
import com.shadowblockz.eggemall.game.Items;
import net.minecraft.server.v1_12_R1.PacketPlayInClientCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeath implements Listener
{
    /**
     * Fired when a player dies.
     * @param event the player death event.
     */
    @EventHandler
    private void playerDeathEvent(PlayerDeathEvent event)
    {
        // Null check
        if(event.getEntity() == null || event.getEntity().getKiller() == null)
            return;

        final Player player = event.getEntity();
        Player killer = event.getEntity().getKiller();
        event.setDeathMessage(""); // removes death message
        event.getDrops().clear(); // clears dropped items

        if(event.getEntity().getLastDamageCause().getCause() != EntityDamageEvent.DamageCause.PROJECTILE)
        {
            killer.getInventory().addItem(Items.GetEggItem());
            Bukkit.broadcastMessage(ChatColor.RED + killer.getName() + ChatColor.GREEN + " killed " + ChatColor.RED + player.getName());

            Main.gameManager.GetCurrentGame().IncreasePlayerScore(killer, 1);
            Main.mysql.GivePlayerPoints(killer, 1);

            // Check if they've won the game, if so end.
            if(Main.gameManager.GetCurrentGame().GetPlayerScore(killer) >= Main.configManager.GetGameScoreCap())
                Main.gameManager.GetCurrentGame().End();
        }

        if(event.getEntity().getLastDamageCause().getCause() != EntityDamageEvent.DamageCause.PROJECTILE &&
                event.getEntity().getLastDamageCause().getCause() != EntityDamageEvent.DamageCause.ENTITY_ATTACK)
        {
            // When a player wasn't killed by a player or an egg.
            Bukkit.broadcastMessage(ChatColor.RED + event.getEntity().getName() + ChatColor.GREEN + " died. ");
        }

        // Automatically respawn the player
        Main.plugin.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
            public void run() {
                if (player.isDead())
                    ((CraftPlayer) player).getHandle().playerConnection.a(new PacketPlayInClientCommand(PacketPlayInClientCommand.EnumClientCommand.PERFORM_RESPAWN));
            }
        });
    }
}
