package com.shadowblockz;

import java.io.IOException;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.config.ConfigurationProvider;

public class StaffReload extends Command
{
  public StaffReload()
  {
    super("staffreload");
  }
  
  public void execute(CommandSender s, String[] arg1)
  {
    if (!s.equals(ProxyServer.getInstance().getConsole()))
    {
      s.sendMessage(TextComponent.fromLegacyText(ChatColor.RED + "You can only execute this from the console!"));
      return;
    }

    try
    {
      ConfigUtil.c = ConfigUtil.provider.load(ConfigUtil.conf);
      DataManager.updateList();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    
    DataManager.updateList();
  }
}
