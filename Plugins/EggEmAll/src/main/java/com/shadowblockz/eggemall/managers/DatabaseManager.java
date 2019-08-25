package com.shadowblockz.eggemall.managers;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.UUID;

import com.shadowblockz.eggemall.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.shadowblockz.eggemall.database.mysql.MySQL;

public class DatabaseManager 
{	
    private MySQL db;

    /**
     * Sets up the database connection.
     */
    public void SetupDB()
    {
        try
        {
            this.db = new MySQL(Main.configManager.GetDatabaseHost(),
                    Main.configManager.GetDatabasePort(),
                    Main.configManager.GetDatabaseName(),
                    Main.configManager.GetDatabaseUser(),
                    Main.configManager.GetDatabasePassword()
            );
            this.db.getConnection();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     * Ensures the database connection's open.
     */
    public void OpenDB()
    {
        try
        {
            if (!this.db.checkConnection())
                this.db.getConnection();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     * Closes the database connection.
     */
    public void CloseDB()
    {
        try
        {
            this.db.closeConnection();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     * Updates the UUID of the specified player
     * in the database.
     * @param p the player to update.
     */
    public void UpdateID(Player p)
    {
        try
        {
            String pname = p.getName();
            UUID pid = p.getUniqueId();
            OpenDB();

            Statement statement = this.db.openConnection().createStatement();
            ResultSet RS = statement.executeQuery("SELECT * FROM `MiniGames_EEA` WHERE `UUID`='" + pid + "';");
            if (RS.next()) {
                statement.executeUpdate("UPDATE `MiniGames_EEA` SET `Username`='" + pname + "' WHERE `UUID`='" + pid + "';");
                statement.close();
            } else {
                ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM Players");
                resultSet.next();
                statement.executeUpdate("INSERT INTO `MiniGames_EEA` (`UUID`,`Username`) VALUES ('" + pid + "','" + pname + "');");
                statement.close();
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     * Gets the current vote count for
     * the specified player.
     * @param p the specified player.
     * @return the amount of votes they have.
     */
    public int GetVotes(Player p)
    {
        try
        {
            UUID pid = p.getUniqueId();
            OpenDB();
            Statement statement = this.db.openConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM `votesql_june` WHERE `id`='"+ pid + "';");
            if (rs.next())
                return rs.getInt("votes");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return 0;
    }

    /**
     * Gets the total number of
     * points for the specified player.
     * @param p the specified player.
     * @return the number of points they have.
     */
    public int GetPoints(String p)
    {
        try
        {
            OpenDB();
            Statement statement = this.db.openConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM `MiniGames_EEA` WHERE `Username`='" + p + "';");
            if (rs.next())
                return rs.getInt("Points");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return 0;
    }

    /**
     * Gets the player whom is in the specified
     * position on the leaderboard.
     * @param position the specified position.
     * @return the player's name.
     */
    public String GetLeaderboardPlayer(Integer position)
    {
        try
        {
            OpenDB();
            Statement statement = this.db.openConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM `MiniGames_EEA` ORDER BY Points DESC;");
            if (rs.getString(position) != null)
                return rs.getString(position);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return "-";
    }

    /**
     * Gives the specified player the specified number
     * of points. This amends it on to whatever they
     * already have.
     * @param player the specified player.
     * @param points the amount of points to add.
     */
    public void GivePlayerPoints(Player player, Integer points)
    {
        try
        {
            OpenDB();
            String playerName = player.getName();
            int playerCurrentPoints = GetPoints(playerName);
            Statement statement = db.getConnection().createStatement();
            if (playerCurrentPoints >= 0) // If they already have points, need to update.
            {
                statement.executeUpdate("UPDATE `MiniGames_EEA` SET `Points`='" + (playerCurrentPoints + points) +
                        "' WHERE `UUID`='" + player.getUniqueId() + "';");
                player.sendMessage(ChatColor.GREEN + "[EEA] " + ChatColor.YELLOW + "+" + points + " Points. (" + playerCurrentPoints + " total)");
            }
            statement.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
