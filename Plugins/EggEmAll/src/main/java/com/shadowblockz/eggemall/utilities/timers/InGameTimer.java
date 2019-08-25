package com.shadowblockz.eggemall.utilities.timers;

import com.shadowblockz.eggemall.Main;
import com.shadowblockz.eggemall.managers.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

public class InGameTimer
{
	public int timeLeft;
    private int taskID;

    public InGameTimer()
	{
		timeLeft = Main.configManager.GetGameLengthSec();
	}
    
    public void StartTimer()
	{
    	BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        taskID = scheduler.scheduleSyncRepeatingTask(Main.plugin, new Runnable()
		{
            @Override
            public void run()
			{
            	if(Main.gameManager.CurrentGameState == GameManager.GameState.InGame)
            	{
	            	if(timeLeft > 0)
	            	{
	            		if(Main.gameManager.GetCurrentGame().GetPlayers().size() < 2)
	            		{
	            			Bukkit.broadcastMessage(ChatColor.YELLOW + "[EEA] " + ChatColor.RESET + "There are not enough players playing to continue the game. Therefore, the game has been finished early.");
	            			Main.gameManager.GetCurrentGame().End();
	            			StopTimer();
	            			return;
	            		}
	            		switch(timeLeft)
						{
		            		case 10:
		            		case 9:
		            		case 8:
		            		case 7:
		            		case 6:
		            		case 5:
		            		case 4:
		            		case 3:
		            		case 2:
		            		case 1:
		            			for(Player player : Main.gameManager.GetCurrentGame().GetPlayers())
		            				player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 1F, 1F);
		            			break;
		            		}
	            		timeLeft--;
	            	}
	            	else
	            	{
	            		StopTimer();
						Main.gameManager.GetCurrentGame().End();

	            	}
            	}
            	else
            	{
            		StopTimer();
            	}
            }
        }, 20L, 20L);  
    }
    
    public void StopTimer()
	{
        Bukkit.getScheduler().cancelTask(taskID);
        timeLeft = Main.configManager.GetGameLengthSec();
    }
}

