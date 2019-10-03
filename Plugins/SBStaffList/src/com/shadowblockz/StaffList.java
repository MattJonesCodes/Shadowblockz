package com.shadowblockz;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

public class StaffList extends Plugin
{
  private static Plugin plugin;
  
  public void onEnable()
  {
    plugin = this;
    ConfigUtil.createConfig();
    getProxy().getPluginManager().registerCommand(this, new OnlineCommand());
    getProxy().getPluginManager().registerCommand(this, new OfflineCommand());
    getProxy().getPluginManager().registerCommand(this, new HideCommand());
    getProxy().getPluginManager().registerCommand(this, new StaffReload());
    DataManager.updateList();
    getProxy().getPluginManager().registerListener(this, new EventListener());
  }
  
  public static Plugin getPlugin()
  {
    return plugin;
  }
}
