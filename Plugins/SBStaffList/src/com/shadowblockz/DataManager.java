package com.shadowblockz;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;

public class DataManager
{
  private static Map<String, String> ranks = new LinkedHashMap();
  private static Map<String, String> prefixes = new HashMap();
  private static Map<String, Long> seen = new HashMap();
  private static List<String> hide;
  private static String defheader;
  private static String offheader;
  private static String online;
  private static String offline;
  private static String hidden;
  private static String footer;
  private static String nostaff;
  
  public static String getHeader()
  {
    return defheader;
  }
  
  public static String getOfflineHeader()
  {
    return offheader;
  }
  
  public static String getNoStaff()
  {
    return nostaff;
  }
  
  public static String getOnline()
  {
    return online;
  }
  
  public static String getOffline()
  {
    return offline;
  }
  
  public static String getHidden()
  {
    return hidden;
  }
  
  public static String getFooter()
  {
    return footer;
  }
  
  public static Map<String, String> getRanks()
  {
    return ranks;
  }
  
  public static Map<String, String> getPrefixes()
  {
    return prefixes;
  }
  
  public static List<String> getHiddenPlayers()
  {
    return hide;
  }
  
  public static void setSeen(String string, long currentTimeMillis)
  {
    seen.put(string, Long.valueOf(currentTimeMillis));
    ConfigUtil.c.set("seen", seen);

    try
    {
      ConfigUtil.provider.save(ConfigUtil.c, ConfigUtil.conf);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  
  public static long getSeen(String name)
  {
    Long seenl = (Long)seen.get(name);
    
    if (seenl == null) 
      return 0L;

    return seenl.longValue();
  }
  
  public static void updateList()
  {
    Configuration c = ConfigUtil.c;
    defheader = c.getString("default-header");
    offheader = c.getString("offline-header");
    nostaff = c.getString("nostaff");
    online = c.getString("online");
    offline = c.getString("offline");
    hidden = c.getString("hidden");
    footer = c.getString("footer");

    for (String s : c.getSection("ranks").getKeys())
    {
      List<String> l = c.getStringList("ranks." + s + ".users");

      for (String name : l) 
        ranks.put(name, s);

      String prefix = c.getString("ranks." + s + ".prefix");
      prefixes.put(s, prefix);
      hide = c.getStringList("hide");
    }

    Object seenmap = (HashMap)c.get("seen");
    if (seenmap != null) 
      seen = (Map)seenmap;
  }
}
