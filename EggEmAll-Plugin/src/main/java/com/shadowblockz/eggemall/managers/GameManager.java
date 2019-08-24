package com.shadowblockz.eggemall.managers;

import com.shadowblockz.eggemall.Main;
import com.shadowblockz.eggemall.game.Game;
import com.shadowblockz.eggemall.game.Locations;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private Main main;
    private List<Player> Queue;
    private Game CurrentGame;

    // The state of the game/server.
    public GameState CurrentGameState;
    public enum GameState {
        InLobby,
        PreGame,
        InGame
    }

    /**
     * Constructor.
     */
    public GameManager() {
        this.Queue = new ArrayList<Player>();
    }

    /**
     * Retsets local variables and
     * settings for the lobby.
     */
    public void Reset() {
        this.Queue.clear();
    }

    /**
     * Gets the current queue for the game.
     *
     * @return the game queue as a list of players.
     */
    public List<Player> GetQueue() {
        return this.Queue;
    }

    /**
     * Adds a player to the queue for the game.
     *
     * @param player the player to add to the queue.
     */
    public void AddPlayerToQueue(Player player) {
        this.Queue.add(player);
    }

    /**
     * Removes a player from the queue for the game.
     *
     * @param player the player to remove from the queue.
     */
    public void RemovePlayerFromQueue(Player player) {
        this.Queue.remove(player);
    }

    /**
     * Returns the currenet game in progress.
     *
     * @return the current game.
     */
    public Game GetCurrentGame() {
        return this.CurrentGame;
    }

    /**
     * Checks if there are enough players
     * for a game to be started.
     *
     * @return true if a game can be started.
     */
    public boolean CanStartGame() {
        if (this.Queue.size() >= Main.configManager.GetGameMinPlayers())
            return true;

        return false;
    }

    /**
     * Starts a new game.
     */
    public void StartGame()
    {
        // Filter players into their respective roles.
        List<Player> players = new ArrayList<Player>();
        List<Player> spectators = new ArrayList<Player>();

        for(int i = 0; i < this.Queue.size(); i++)
        {
            if(i < Main.configManager.GetGameMaxPlayers())
            {
                players.add(this.Queue.get(0));
                this.Queue.remove(0);
            }
            else
            {
                spectators.add(this.Queue.get(0));
            }
        }

        // Ensure nobody's missed.
        for(Player player : Bukkit.getOnlinePlayers())
        {
            if(players.contains(player) || spectators.contains(player))
                continue;
            
            spectators.add(player);
        }

        // Start a game with those players.
        Game game = new Game(players, spectators);
        game.Start();
        CurrentGame = game;
        CurrentGameState = GameState.PreGame;
    }

    /**
     * Ends the game from the GameManagers point of view,
     * brings players back into the lobby etc.
     */
    public void EndGame()
    {
        // Move everyone back into the lobby.
        for(Player player : Bukkit.getOnlinePlayers())
            player.teleport(Locations.Lobby);

        // Add all the players in the last game back onto end of the queue.
        this.CurrentGame.GetPlayers().addAll(this.Queue);
        this.CurrentGame = null; // Destroy

        // Set the lobby back up again.
        CurrentGameState = GameState.InLobby;
        ServerScoreboardManager.stopGameScoreboard();
        ServerScoreboardManager.createLobbyScoreboard();
    }
}