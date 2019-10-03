package com.shadowblockz;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class ConfigUtil
{
  public static Configuration c;
  public static ConfigurationProvider provider = ConfigurationProvider.getProvider(YamlConfiguration.class);
  public static File conf;
  
  static
  {
    File f = StaffList.getPlugin().getDataFolder();
    conf = new File(f, "config.yml");
  }
  
  public static void createConfig()
  {
    try
    {
      // Populate configuration if no values already exist.
      StaffList.getPlugin().getDataFolder().mkdirs();
      conf.createNewFile();
      Configuration config = provider.load(conf);
      c = config;

      if (config.getString("default-header").isEmpty())
        config.set("default-header", "&a&m+--------------&e[&bStaff Members&e]&a&m----------------+");
      
      if (config.getString("offline-header").isEmpty())
        config.set("offline-header", "&a&m+-------------------&e[&f[&4OFFLINE&f] &bStaff Members&e]&a&m-------------------+");
      
      if (config.getString("online").isEmpty())
        config.set("online", "{rank} &6{username} - &a{online_at_server}");
      
      if (config.getString("offline").isEmpty())
        config.set("offline", "{rank} &6{username} - &a&n({last_seen_date}) - Offline");
      
      if (config.getString("nostaff").isEmpty())
        config.set("nostaff", "There is no staff online at the moment.");
      
      if (config.getString("hidden").isEmpty())
        config.set("hidden", "{rank} &6{username} - &a&n(Hidden) - Online");
      
      if (config.getString("footer").isEmpty())
        config.set("footer", "&a&m+---------------------------------------------------+");
      
      if (config.getSection("ranks").getKeys().isEmpty())
      {
        config.set("ranks.owner.prefix", "&4[Owner]");
        config.set("ranks.admin.prefix", "&4[Admin]");
        config.set("ranks.moderator.prefix", "&1[Moderator]");
        config.set("ranks.trialmod.prefix", "&2[Trial-Mod]");
        config.set("ranks.owner.users", Arrays.asList(new String[] { "user1", "user2" }));
        config.set("ranks.admin.users", Arrays.asList(new String[] { "user1", "user2" }));
        config.set("ranks.moderator.users", Arrays.asList(new String[] { "user1", "user2" }));
        config.set("ranks.trialmod.users", Arrays.asList(new String[] { "user1", "user2" }));
      }

      provider.save(config, conf);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
