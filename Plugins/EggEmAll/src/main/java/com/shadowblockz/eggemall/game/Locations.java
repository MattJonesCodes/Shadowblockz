package com.shadowblockz.eggemall.game;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Locations {

    // HACK - Locations within the game world.

    // ToDo: Move this into a section within the configuration!

    public static Location Lobby = new Location(Bukkit.getWorld("Map"), 1515.99, 13.0, 393.5, 0, 0);
    public static Location Spec = new Location(Bukkit.getWorld("Map"), 1506.5, 101.0, 377.5, 0, 0);
    public static Location board = new Location(Bukkit.getWorld("Map"), 1516.002, 16.35443, 404.7, 0, 0);

    public static List<Location> GameSpawns = new ArrayList<Location>();

    public static void loadGameSpawns(){
        GameSpawns.add(new Location(Bukkit.getWorld("Map"), 1562.5, 62.0, 389.5, 0, 0));
        GameSpawns.add(new Location(Bukkit.getWorld("Map"), 1561.5, 64.0, 407.5, 0, 0));
        GameSpawns.add(new Location(Bukkit.getWorld("Map"), 1548.5, 62.0, 420.5, 0, 0));
        GameSpawns.add(new Location(Bukkit.getWorld("Map"), 1546.5, 67.0, 439.5, 0, 0));
        GameSpawns.add(new Location(Bukkit.getWorld("Map"), 1527.5, 62.0, 433.5, 0, 0));
        GameSpawns.add(new Location(Bukkit.getWorld("Map"), 1505.5, 62.0, 431.5, 0, 0));
        GameSpawns.add(new Location(Bukkit.getWorld("Map"), 1499.5, 65.0, 444.5, 0, 0));
        GameSpawns.add(new Location(Bukkit.getWorld("Map"), 1484.5, 67.0, 424.5, 0, 0));
        GameSpawns.add(new Location(Bukkit.getWorld("Map"), 1482.5, 63.0, 418.5, 0, 0));
        GameSpawns.add(new Location(Bukkit.getWorld("Map"), 1499.5, 62.0, 416.5, 0, 0));
        GameSpawns.add(new Location(Bukkit.getWorld("Map"), 1473.5, 67.0, 424.5, 0, 0));
        GameSpawns.add(new Location(Bukkit.getWorld("Map"), 1459.5, 62.0, 405.5, 0, 0));
        GameSpawns.add(new Location(Bukkit.getWorld("Map"), 1464.5, 62.0, 381.5, 0, 0));
        GameSpawns.add(new Location(Bukkit.getWorld("Map"), 1449.5, 67.0, 363.5, 0, 0));
        GameSpawns.add(new Location(Bukkit.getWorld("Map"), 1467.5, 62.0, 346.5, 0, 0));
        GameSpawns.add(new Location(Bukkit.getWorld("Map"), 1482.5, 64.0, 322.5, 0, 0));
        GameSpawns.add(new Location(Bukkit.getWorld("Map"), 1501.5, 62.0, 326.5, 0, 0));
        GameSpawns.add(new Location(Bukkit.getWorld("Map"), 1526.5, 67.0, 323.5, 0, 0));
        GameSpawns.add(new Location(Bukkit.getWorld("Map"), 1528.5, 62.0, 337.5, 0, 0));
        GameSpawns.add(new Location(Bukkit.getWorld("Map"), 1546.5, 66.0, 344.5, 0, 0));
        GameSpawns.add(new Location(Bukkit.getWorld("Map"), 1553.5, 63.0, 356.5, 0, 0));
        GameSpawns.add(new Location(Bukkit.getWorld("Map"), 1572.5, 66.0, 365.5, 0, 0));
        GameSpawns.add(new Location(Bukkit.getWorld("Map"), 1516.5, 62.0, 346.5, 0, 0));
        GameSpawns.add(new Location(Bukkit.getWorld("Map"), 1495.5, 62.0, 345.5, 0, 0));
        GameSpawns.add(new Location(Bukkit.getWorld("Map"), 1481.5, 62.0, 355.5, 0, 0));
        GameSpawns.add(new Location(Bukkit.getWorld("Map"), 1481.5, 62.0, 393.5, 0, 0));
        GameSpawns.add(new Location(Bukkit.getWorld("Map"), 1523.5, 62.0, 408.5, 0, 0));
        GameSpawns.add(new Location(Bukkit.getWorld("Map"), 1540.5, 62.0, 393.5, 0, 0));
        GameSpawns.add(new Location(Bukkit.getWorld("Map"), 1542.5, 62.0, 371.5, 0, 0));
        GameSpawns.add(new Location(Bukkit.getWorld("Map"), 1523.5, 81.0, 374.5, 0, 0));
        GameSpawns.add(new Location(Bukkit.getWorld("Map"), 1515.5, 81.0, 362.5, 0, 0));
        GameSpawns.add(new Location(Bukkit.getWorld("Map"), 1506.5, 85.0, 375.5, 0, 0));
        GameSpawns.add(new Location(Bukkit.getWorld("Map"), 1496.5, 81.0, 362.5, 0, 0));
        GameSpawns.add(new Location(Bukkit.getWorld("Map"), 1494.5, 78.0, 385.5, 0, 0));
        GameSpawns.add(new Location(Bukkit.getWorld("Map"), 1520.5, 81.0, 391.5, 0, 0));

    }

}
