package com.shadowblockz.eggemall.utilities.timers;

import com.shadowblockz.eggemall.Main;
import com.shadowblockz.eggemall.game.Items;
import com.shadowblockz.eggemall.managers.GameManager;
import com.shadowblockz.eggemall.utilities.Title;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.List;

public class PreGameTimer
{
    public int timeLeft;
    private int taskID;

    public PreGameTimer()
    {
        timeLeft = 10;
    }

    public void StartTimer()
    {
        List<Player> players = Main.gameManager.GetCurrentGame().GetPlayers();
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        taskID = scheduler.scheduleSyncRepeatingTask(Main.plugin, new Runnable()
        {
            @Override
            public void run()
            {
                if(timeLeft <= 0)
                {
                    StopTimer();
                    Main.inGameTimer.StartTimer();

                    Bukkit.broadcastMessage(ChatColor.YELLOW + "[EEA] " + ChatColor.RED + "Let the battle commence!");
                    for(Player p : players)
                    {
                        p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 2F, 2F);
                        new Title("�cFight!","",0,2,1).send(p);

                        // Shows all the players to all other players
                        for(Player p2 : Main.gameManager.GetCurrentGame().GetPlayers())
                            p.showPlayer(p2);
                    }

                    for(Player player : Main.gameManager.GetCurrentGame().GetPlayers())
                        Items.GivePlayerInGameItems(player);

                    Main.gameManager.CurrentGameState = GameManager.GameState.InGame;
                    return;
                }

                switch(timeLeft)
                {
                    case 10:
                    case 9:
                    case 8:
                    case 7:
                    case 6:
                        for(Player player : players)
                            new Title("�6Egg Em All",("�dStarting in " + timeLeft + " seconds"),0,1,0).send(player);
                        break;
                    case 5:
                    case 4:
                    case 3:
                    case 2:
                    case 1:
                        for(Player player : players){
                            new Title("�6Egg Em All",("�dStarting in " + timeLeft + " seconds"),0,1,0).send(player);
                            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_SNARE, 0.25F, 3F);
                        }
                        break;
                }
                timeLeft--;
            }
        }, 20L, 20L);
    }
    
    public void StopTimer()
    {
        timeLeft = 10;
        Bukkit.getScheduler().cancelTask(taskID);
    }
}
