package com.shadowblockz;

import java.util.Map;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class OfflineCommand extends Command
{
  public OfflineCommand()
  {
    super("staffoffline");
  }
  
  public void execute(CommandSender s, String[] arg1)
  {
    s.sendMessage(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', DataManager.getOfflineHeader())));
    for (String key : DataManager.getRanks().keySet())
    {
      ProxiedPlayer p = ProxyServer.getInstance().getPlayer(key);
      if (p == null) 
      {
        s.sendMessage(TextComponent.fromLegacyText(
          ChatColor.translateAlternateColorCodes('&', DataManager.getOffline()
          .replace("{username}", key)
          .replace("{last_seen_date}", timeCalc(DataManager.getSeen(key)))
          .replace("{rank}", (CharSequence)DataManager.getPrefixes().get(DataManager.getRanks().get(key))))));
      }
    }
    s.sendMessage(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', DataManager.getFooter())));
  }
  
  private String timeCalc(long seen)
  {
    long milis = System.currentTimeMillis() - seen;
    if (seen == 0L)
      return "Never";

    if (milis > 86400000L)
      return (int)Math.floor(milis / 86400000L) + " Days";

    if (milis > 3600000L)
      return (int)Math.floor(milis / 3600000L) + " Hours";

    if (milis > 60000L)
      return (int)Math.floor(milis / 60000L) + " Minutes";

    return "Less than a minute ago";
  }
}
