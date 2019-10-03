package com.shadowblockz;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class OnlineCommand extends Command
{
  public OnlineCommand()
  {
    super("staff");
  }
  
  public void execute(CommandSender s, String[] arg1)
  {
    s.sendMessage(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', DataManager.getHeader())));
    boolean send = true;
    for (String key : DataManager.getRanks().keySet())
    {
      ProxiedPlayer p = ProxyServer.getInstance().getPlayer(key);
      if(p == null)
        continue;
      
      send = false;
      if (!DataManager.getHiddenPlayers().contains(key)) 
      {
        s.sendMessage(TextComponent.fromLegacyText(
          ChatColor.translateAlternateColorCodes('&', DataManager.getOnline().replace("{username}", key)
          .replace("{online_at_server}", p.getServer().getInfo().getName())
          .replace("{rank}", (CharSequence)DataManager.getPrefixes().get(DataManager.getRanks().get(key))))));
      } 
      else 
      {
        s.sendMessage(TextComponent.fromLegacyText(
          ChatColor.translateAlternateColorCodes('&', DataManager.getHidden().replace("{username}", key)
          .replace("{rank}", (CharSequence)DataManager.getPrefixes().get(DataManager.getRanks().get(key))))));
      }
    }

    if (send)
      s.sendMessage(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', DataManager.getNoStaff())));
    else
      s.sendMessage(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', DataManager.getFooter())));
  }
}
