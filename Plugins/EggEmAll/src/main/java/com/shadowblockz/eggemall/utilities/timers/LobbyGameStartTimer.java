package com.shadowblockz.eggemall.utilities.timers;

import com.shadowblockz.eggemall.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

public class LobbyGameStartTimer
{
    public int timeLeft;
    public int taskID;

    public LobbyGameStartTimer()
    {
        timeLeft = 10;
    }

    public void StartTimer()
    {
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        taskID = scheduler.scheduleSyncRepeatingTask(Main.plugin, new Runnable()
        {
            @Override
            public void run()
            {
                if(timeLeft > 0)
                {
                    --timeLeft;
                    if (Main.gameManager.GetQueue().size() < 4)
                    {
                        StopTimer();
                        Bukkit.broadcastMessage(ChatColor.YELLOW + "[EEA] " + ChatColor.RED + "There are not enough players online to start the game! Timer cancelled.");
                        return;
                    }

                    switch(timeLeft)
                    {
                        case 10:
                            Bukkit.broadcastMessage(ChatColor.YELLOW + "[EEA] " + ChatColor.GREEN + "Warping into battle in " + timeLeft + " seconds!");
                            break;
                        case 9:
                        case 8:
                        case 7:
                        case 6:
                            break;
                        case 5:
                        case 4:
                        case 3:
                        case 2:
                        case 1:
                            Bukkit.broadcastMessage(ChatColor.YELLOW + "[EEA] " + ChatColor.GREEN + "Warping into battle in " + timeLeft + " seconds!");
                            break;
                        case 0:
                            break;
                    }
                }
                else
                {
                    StopTimer();
                    Main.gameManager.StartGame();
                }
            }
        }, 20L, 20L);  
    }
    
    public void StopTimer()
    {
        Bukkit.getScheduler().cancelTask(taskID);
        timeLeft = 10;
        for(Player player : Bukkit.getOnlinePlayers())
            player.setLevel(0);
    }
}
