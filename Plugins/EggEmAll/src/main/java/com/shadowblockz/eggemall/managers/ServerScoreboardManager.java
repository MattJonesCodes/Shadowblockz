package com.shadowblockz.eggemall.managers;

import java.util.Iterator;
import java.util.Map;

import com.shadowblockz.eggemall.Main;
import com.shadowblockz.eggemall.utilities.MapUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class ServerScoreboardManager 
{
 
    private static int gameTaskID;

    public static void createGameScoreboard()
    {
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();

        final Objective obj = board.registerNewObjective("test", "dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName("    �6�lEGG EM ALL   ");
        final Team line1 = board.registerNewTeam("blank1");
        final Team line2 = board.registerNewTeam("time");
        final Team line3 = board.registerNewTeam("blank2");
        final Team line4 = board.registerNewTeam("website");
        final Team line5 = board.registerNewTeam("blank3");
        line1.addEntry(ChatColor.AQUA.toString());
        line2.addEntry("Time");
        line3.addEntry(ChatColor.BLACK.toString());
        line4.addEntry("�r�a");
        line5.addEntry("�a�r");
        obj.getScore(ChatColor.AQUA.toString()).setScore(11);
        obj.getScore("Time").setScore(10);
        obj.getScore(ChatColor.BLACK.toString()).setScore(9);
        obj.getScore("�a�r").setScore(1);
        obj.getScore("�r�a").setScore(0);
        line4.setSuffix("Shadowblockz.com");

        int x = 8;
        for(Player player : Main.gameManager.GetCurrentGame().GetPlayers())
        {
            final Team i = board.registerNewTeam("line" + x);
            i.addEntry("�" + x + "�r");
            obj.getScore("�" + x + "�r").setScore(x);;
            i.setPrefix(player.getName());
            i.setSuffix(": �a" + 0);
            x--;
        }

        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        gameTaskID = scheduler.scheduleSyncRepeatingTask(Main.plugin, new Runnable()
        {
            @SuppressWarnings({ "rawtypes"})
            @Override
            public void run()
            {
                line2.setSuffix(": �a" + Main.inGameTimer.timeLeft);

                int x = 8;
                Map<Player, Integer> map = MapUtil.sortByValue(Main.gameManager.GetCurrentGame().GetPlayerScores());;
                Iterator it = map.entrySet().iterator();
                while (it.hasNext())
                {
                    Map.Entry pair = (Map.Entry)it.next();
                    Player player = (Player) pair.getKey();
                    Integer score = (Integer) pair.getValue();

                    Team i = board.getTeam("line" + x);
                    i.setPrefix(player.getName());
                    i.setSuffix(": �a" + score);

                    it.remove();
                    x--;
                }
                map.clear();

                if(Bukkit.getOnlinePlayers().size() > 0)
                {
                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        if(!(player.getScoreboard() == board))
                            player.setScoreboard(board);
                    }
                }
            }
        }, 20L, 20L);
    }

    public static void stopGameScoreboard()
    {
        Bukkit.getScheduler().cancelTask(gameTaskID);
    }

    private static int lobbyTaskID;
    public static void createLobbyScoreboard()
    {
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();

        final Objective obj = board.registerNewObjective("test", "dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName("    �6�lEGG EM ALL   ");
        final Team line2 = board.registerNewTeam("blank2");
        final Team line3 = board.registerNewTeam("blank3");
        final Team line4 = board.registerNewTeam("blank4");
        final Team line5 = board.registerNewTeam("blank5");
        final Team line6 = board.registerNewTeam("blank6");
        final Team line7 = board.registerNewTeam("blank7");
        line2.addEntry("�b�r");
        line3.addEntry(ChatColor.BLUE.toString());
        line4.addEntry(ChatColor.DARK_AQUA.toString());
        line5.addEntry(ChatColor.DARK_BLUE.toString());
        line6.addEntry(ChatColor.DARK_GRAY.toString());
        line7.addEntry(ChatColor.DARK_GREEN.toString());
        obj.getScore("�b�r").setScore(6);
        obj.getScore(ChatColor.BLUE.toString()).setScore(5);
        obj.getScore(ChatColor.DARK_AQUA.toString()).setScore(4);
        obj.getScore(ChatColor.DARK_BLUE.toString()).setScore(3);
        obj.getScore(ChatColor.DARK_GRAY.toString()).setScore(2);
        obj.getScore(ChatColor.DARK_GREEN.toString()).setScore(1);
        line7.setPrefix("�b");
        line7.setSuffix("Shadowblockz.com");

        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        lobbyTaskID = scheduler.scheduleSyncRepeatingTask(Main.plugin, new Runnable()
        {
            @Override
            public void run()
            {
                if(Main.gameManager.GetQueue().size() < 4)
                {
                    line3.setSuffix("�rWaiting... ");
                    line4.setSuffix("�rPlayers: �a" + Main.gameManager.GetQueue().size());
                }
                else
                {
                    line3.setSuffix("�rGame starting");
                    line4.setSuffix("�rin.. " + Main.lobbyGameStartTimer.timeLeft);
                }

                if(Bukkit.getOnlinePlayers().size() > 0)
                {
                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        if(!(player.getScoreboard() == board))
                            player.setScoreboard(board);
                    }
                }
            }
        }, 20L, 10L);
    }

    public static void stopLobbyScoreboard()
    {
        Bukkit.getScheduler().cancelTask(lobbyTaskID);
    }
}
 
 
