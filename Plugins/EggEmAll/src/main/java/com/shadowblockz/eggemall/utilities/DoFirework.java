package com.shadowblockz.eggemall.utilities;

import com.shadowblockz.eggemall.Main;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class DoFirework 
{
	/**
	 * Shoots a firework at the specified location.
	 * @param loc the specified location.
	 */
	public void shoot(Location loc)
	{
		FireworkEffect effect = FireworkEffect.builder().trail(false).flicker(false)
			.withColor(Color.RED).withFade(Color.ORANGE).with(FireworkEffect.Type.BALL).build();
		final Firework fw = loc.getWorld().spawn(loc, Firework.class);
		FireworkMeta meta = fw.getFireworkMeta();
		meta.addEffect(effect);
		meta.setPower(0);
		fw.setFireworkMeta(meta);

		new BukkitRunnable() 
		{
		    @Override
			public void run() 
			{
		      fw.detonate();
		      loc.getWorld().playSound(loc, Sound.BLOCK_LAVA_POP, 10F, -10F);
		    }
		}.runTaskLater(Main.plugin, 1L);
	}
}
