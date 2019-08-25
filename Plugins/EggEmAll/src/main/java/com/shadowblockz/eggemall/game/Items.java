package com.shadowblockz.eggemall.game;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Items
{
    /**
     * Returns the Egg Item (ingame item)
     * @return the egg item.
     */
    public static ItemStack GetEggItem()
    {
        // Defining the Egg item
        ItemStack Egg = new ItemStack(Material.EGG, 1);
        ItemMeta EggMeta = (ItemMeta) Egg.getItemMeta();
        EggMeta.setDisplayName(ChatColor.GOLD + "Egg");
        List<String> EggList = new ArrayList<String>();
        EggList.add(ChatColor.GRAY + "For Splatting");
        EggMeta.setLore(EggList);
        Egg.setItemMeta(EggMeta);
        return Egg;
    }

    /**
     * Gives the specified player the
     * initial ingame items.
     * @param player player to give items too.
     */
    public static void GivePlayerInGameItems(Player player)
    {
        // Defining the sword item
        ItemStack Sword = new ItemStack(Material.WOOD_SWORD, 1);
        ItemMeta SwordMeta = (ItemMeta) Sword.getItemMeta();
        SwordMeta.setDisplayName(ChatColor.GOLD + "Sword");
        SwordMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
        SwordMeta.addEnchant(Enchantment.DURABILITY, 10, true);
        List<String> SwordList = new ArrayList<String>();
        SwordList.add(ChatColor.GRAY + "For Slaying");
        SwordMeta.setLore(SwordList);
        Sword.setItemMeta(SwordMeta);

        player.getInventory().setItem(0, Sword);
        player.getInventory().setItem(1, GetEggItem());
    }

    /**
     * Gives lobby items to the specified
     * player.
     * @param player player to give the items too.
     */
    public static void GiveLobbyItemsToPlayer(Player player){
        player.teleport(Locations.Lobby);
        player.setGameMode(GameMode.ADVENTURE);
        player.setHealth(20);
        player.setFoodLevel(20);
        player.getInventory().clear();

        ItemStack Quit = new ItemStack(Material.SLIME_BALL, 1);
        ItemMeta QuitMeta = (ItemMeta) Quit.getItemMeta();
        QuitMeta.setDisplayName(ChatColor.RED + "Return to Hub");
        Quit.setItemMeta(QuitMeta);

        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta bm = (BookMeta) book.getItemMeta();
        bm.setPages(Arrays.asList("\n"
                        + "\n"
                        + "\n"
                        + "�5###################"
                        + "\n�9        Welcome To"
                        + "\n�1       Egg em' All"
                        + "\n�2            V0.1"
                        + "\n�5###################"
                        + "\n"
                        + "\n"
                        + "\n"
                        + "�6WWW.SHADOWBLOCKZ.COM",
                "     �4�l�nHow To Play"
                        + "\n"
                        + "\n"
                        + "�r�0The aim of the game is to reach 20 kills or get the most kills by the end of the time limit."
                        + "\n"
                        + "\n"
                        + "You will be given a wooden sword and 1 egg when you spawn. The egg is a '1 hit kill'. When you kill a player you ",
                "receive 1 egg."));

        bm.setAuthor("Shadowblockz");
        bm.setTitle("�eEgg 'em All");
        book.setItemMeta(bm);
        player.getInventory().addItem(book);
        player.getInventory().setItem(8, Quit);
    }
}
