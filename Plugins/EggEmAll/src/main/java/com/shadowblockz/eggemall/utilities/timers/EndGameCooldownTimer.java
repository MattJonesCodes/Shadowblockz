package com.shadowblockz.eggemall.utilities.timers;

import com.shadowblockz.eggemall.Main;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitScheduler;

public class EndGameCooldownTimer
{
    private int time;
    private int taskID;

    public EndGameCooldownTimer()
    {
        time = 10;
    }
    
    public void StartTimer()
	{
    	BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        taskID = scheduler.scheduleSyncRepeatingTask(Main.plugin, new Runnable()
		{
            @Override
            public void run()
			{
            	if(time > 0)
            		time--;
            	else
            	{
            		StopTimer();
            		Main.gameManager.EndGame();
            	}
            }
        }, 20L, 20L);  
    }
    
    public void StopTimer()
	{
        Bukkit.getScheduler().cancelTask(taskID);
    }
}
