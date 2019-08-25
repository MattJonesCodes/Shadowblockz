package com.shadowblockz.eggemall.game;

import com.shadowblockz.eggemall.Main;
import com.shadowblockz.eggemall.managers.ServerScoreboardManager;
import com.shadowblockz.eggemall.utilities.FormattedChatMessaged;
import com.shadowblockz.eggemall.utilities.MapUtil;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.*;

public class Game
{
    private List<Player> players; // Players
    private List<Player> spectators; // Spectators of the game.
    private HashMap<Player, Integer> playerScores; // Players scores.

    /**
     * Gets the current players within the game.
     * @return the players within the game.
     */
    public List<Player> GetPlayers()
    {
        return this.players;
    }

    /**
     * Gets the spectators within the game.
     * @return the spectators within the game.
     */
    public List<Player> GetSpectators()
    {
        return this.spectators;
    }

    /**
     * Gets the specified players score for this game.
     * @param player the specified player.
     * @return their score.
     */
    public int GetPlayerScore(Player player)
    {
        return this.playerScores.get(player);
    }

    /**
     * Increases the specified players score by the
     * specified amount.
     * @param player the specified player.
     * @param amount the amount to increase their score by.
     */
    public void IncreasePlayerScore(Player player, int amount)
    {
        this.playerScores.put(player, this.playerScores.get(player) + amount);
    }

    /**
     * Removes the specified player from the game.
     * @param player the specified player.
     */
    public void RemovePlayer(Player player)
    {
        players.remove(player);
    }

    /**
     * Removes the specified spectator from the game.
     * @param player the specified player.
     */
    public void RemoveSpectator(Player player)
    {
        spectators.remove(player);
    }

    /**
     * Gets all player scores.
     * @return the scores of all players within the game.
     */
    public HashMap<Player, Integer> GetPlayerScores()
    {
        return playerScores;
    }

    /**
     * Adds the specified player as a spectator of
     * the game.
     * @param player the specified player.
     */
    public void AddSpectator(Player player)
    {
        spectators.add(player);
        player.teleport(Locations.Spec);
        player.setGameMode(GameMode.SPECTATOR);
        player.sendMessage(ChatColor.YELLOW + "[EEA] " + ChatColor.RESET + "You have joined" + ChatColor.RED + " Spectators");
    }

    /**
     * Constructor of a new game object.
     * @param players the players who are part of the game.
     * @param spectators the players who are spectators of the game.
     */
    public Game(List<Player> players, List<Player> spectators)
    {
        this.playerScores = new HashMap<Player, Integer>();
        this.players = new ArrayList<Player>();
        this.spectators = new ArrayList<Player>();

        for(Player player : players)
        {
            this.players.add(player);
            this.playerScores.put(player, 0);
        }

        for(Player player : spectators)
            AddSpectator(player);
    }

    /**
     * Starts the game from the Game Object point of view.
     */
    public void Start()
    {
        // Tp all players into game
        Locations.loadGameSpawns();
        Collections.shuffle(Locations.GameSpawns);

        for(int i = 0; i < this.players.size(); i++)
        {
            Player player = this.players.get(i);
            player.teleport(Locations.GameSpawns.get(0));
            Locations.GameSpawns.remove(0);

            player.getInventory().clear(); // Ensure clear inventory.

            for(int y = 0; y < this.players.size(); i++)
                this.players.get(i).hidePlayer(player); // Hide from each other until start.
        }

        for(Player player : spectators)
            player.teleport(Locations.Spec);

        Locations.GameSpawns.clear();

        ServerScoreboardManager.stopLobbyScoreboard();
        ServerScoreboardManager.createGameScoreboard();

        // Start the timer until the game fully starts. (Frozen game load)
        Main.preGameTimer.StartTimer();
    }

    /**
     * Ends the game from the Game Object point of view.
     */
    public void End()
    {
        for(Player player : this.players)
        {
            player.getInventory().clear();
            player.setGameMode(GameMode.SPECTATOR);
        }

        // Output winners
        Map<Player, Integer> map = MapUtil.sortByValue(playerScores);;
        FormattedChatMessaged.EndGameMessage(
                (Player) map.keySet().toArray()[0],
                (Player) map.keySet().toArray()[1],
                (Player) map.keySet().toArray()[2]
                );

        // 10s to GG, then let GameManager cleanup.
        Main.endGameCooldownTimer.StartTimer();
    }
}
