package com.shadowblockz;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;

public class HideCommand extends Command
{
  public HideCommand()
  {
    super("staffhide");
  }
  
  public void execute(CommandSender p, String[] arg1)
  {
    if (!(p instanceof ProxiedPlayer))
      return;

    if (!DataManager.getRanks().containsKey(p.getName()))
    {
      TextComponent t = new TextComponent("You are not a staff member!");
      t.setColor(ChatColor.RED);
      p.sendMessage(t);
      return;
    }

    List<String> hide = DataManager.getHiddenPlayers();
    if (hide.contains(p.getName()))
    {
      hide.remove(p.getName());
      TextComponent comp = new TextComponent("You are now visible on the staff list!");
      comp.setColor(ChatColor.GREEN);
      p.sendMessage(comp);
    }
    else
    {
      hide.add(p.getName());
      TextComponent comp = new TextComponent("You have been hidden from the staff list!");
      comp.setColor(ChatColor.GREEN);
      p.sendMessage(comp);
    }

    try
    {
      ConfigUtil.c.set("hide", hide);
      ConfigUtil.provider.save(ConfigUtil.c, ConfigUtil.conf);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
