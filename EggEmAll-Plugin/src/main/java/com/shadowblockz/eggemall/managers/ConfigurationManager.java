package com.shadowblockz.eggemall.managers;

import com.shadowblockz.eggemall.Main;

public class ConfigurationManager
{
    // Database Settings.
    private String databaseHost;
    private String databasePort;
    private String databaseName;
    private String databaseUser;
    private String databasePassword;

    // Game Settings.
    private int gameMinPlayers;
    private int gameMaxPlayers;
    private int gameScoreCap;
    private int gameLengthSec;

    public ConfigurationManager()
    {
        // Database Settings.
        databaseHost = Main.plugin.getConfig().getString("Host");
        databasePort = Main.plugin.getConfig().getString("Port");
        databaseName = Main.plugin.getConfig().getString("Database");
        databaseUser = Main.plugin.getConfig().getString("User");
        databasePassword = Main.plugin.getConfig().getString("Password");

        // Game Settings;
        gameMinPlayers = Main.plugin.getConfig().getInt("MinPlayers");
        gameMaxPlayers = Main.plugin.getConfig().getInt("MaxPlayers");
        gameScoreCap = Main.plugin.getConfig().getInt("GameScoreCap");
        gameLengthSec = Main.plugin.getConfig().getInt("GameLengthSec");
    }

    public String GetDatabaseHost()
    {
        return databaseHost;
    }

    public String GetDatabasePort()
    {
        return databasePort;
    }

    public String GetDatabaseUser()
    {
        return databaseUser;
    }

    public String GetDatabasePassword()
    {
        return databasePassword;
    }

    public String GetDatabaseName()
    {
        return databaseName;
    }

    public Integer GetGameMinPlayers()
    {
        return gameMinPlayers;
    }

    public Integer GetGameMaxPlayers()
    {
        return gameMaxPlayers;
    }

    public Integer GetGameScoreCap()
    {
        return gameScoreCap;
    }

    public Integer GetGameLengthSec()
    {
        return gameLengthSec;
    }
}
