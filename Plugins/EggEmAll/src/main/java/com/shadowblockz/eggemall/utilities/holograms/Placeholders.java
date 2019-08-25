package com.shadowblockz.eggemall.utilities.holograms;

import com.shadowblockz.eggemall.Main;
import org.bukkit.entity.Player;
import me.clip.placeholderapi.external.EZPlaceholderHook;

public class Placeholders extends EZPlaceholderHook 
{
    @SuppressWarnings("unused")
    private Main plugin;

    public Placeholders(Main plugin)
    {
        super(plugin, "eeatop");
        this.plugin = plugin;
    }

    @Override
    public String onPlaceholderRequest(Player p, String identifier)
    {
        if (identifier.equals("1"))
        {
            try
            {
                String player = Main.mysql.GetLeaderboardPlayer(0);
                return "�61st " + player + " - " + Main.mysql.GetPoints(player) + " Points";
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        if (identifier.equals("2"))
        {
            try
            {
                String player = Main.mysql.GetLeaderboardPlayer(1);
                return "�32nd " + player + " - " + Main.mysql.GetPoints(player) + " Points";
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        if (identifier.equals("3"))
        {
            try
            {
                String player = Main.mysql.GetLeaderboardPlayer(2);
                return "�93rd " + player + " - " + Main.mysql.GetPoints(player) + " Points";
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        if (identifier.equals("4"))
        {
            try
            {
                String player = Main.mysql.GetLeaderboardPlayer(3);
                return "�74th " + player + " - " + Main.mysql.GetPoints(player) + " Points";
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        if (identifier.equals("5"))
        {
            try
            {
                String player = Main.mysql.GetLeaderboardPlayer(4);
                return "�75th " + player + " - " + Main.mysql.GetPoints(player) + " Points";
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        if (identifier.equals("6"))
        {
            try
            {
                String player = Main.mysql.GetLeaderboardPlayer(5);
                return "�76th " + player + " - " + Main.mysql.GetPoints(player) + " Points";
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        if (identifier.equals("7"))
        {
            try
            {
                String player = Main.mysql.GetLeaderboardPlayer(6);
                return "�77th " + player + " - " + Main.mysql.GetPoints(player) + " Points";
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        if (identifier.equals("8"))
        {
            try
            {
                String player = Main.mysql.GetLeaderboardPlayer(7);
                return "�78th " + player + " - " + Main.mysql.GetPoints(player) + " Points";
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        if (identifier.equals("9"))
        {
            try {
                String player = Main.mysql.GetLeaderboardPlayer(8);
                return "�79th " + player + " - " + Main.mysql.GetPoints(player) + " Points";
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        if (identifier.equals("10"))
        {
            try {
                String player = Main.mysql.GetLeaderboardPlayer(0);
                return "�710th " + player + " - " + Main.mysql.GetPoints(player) + " Points";
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return null;
    }
}