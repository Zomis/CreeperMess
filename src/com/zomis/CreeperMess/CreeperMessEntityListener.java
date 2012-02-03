package com.zomis.CreeperMess;

import org.bukkit.Location;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityListener;
import org.bukkit.ChatColor;

public class CreeperMessEntityListener extends EntityListener {
	
	private CreeperMess p = CreeperMess.p;
	
	public CreeperMessEntityListener(CreeperMess p) {
		this.p = p;
	}

	public static Player findClosestPlayer(Location loc) {
		Player closest = null;
		double minDistance = 2147483647;
		double distance = 0;
		
		for (Player player : CreeperMess.p.getServer().getOnlinePlayers()) {
			if (player.getWorld().equals(loc.getWorld())) {
				distance = player.getLocation().distance(loc);
				if (distance < minDistance) {
					minDistance = distance;
					closest = player;
				}
			}
		}
		
		return closest;
	}
	
	@Override
	public void onEntityExplode(EntityExplodeEvent event)
	{
		if (!(event.getEntity() instanceof Creeper)) return;
		
		Location loc = event.getLocation();
		
		String mess = p.message;
		mess = mess.replace("%C_AQUA%", ChatColor.AQUA.toString());
		mess = mess.replace("%C_BLACK%", ChatColor.BLACK.toString());
		mess = mess.replace("%C_BLUE%", ChatColor.BLUE.toString());
		mess = mess.replace("%C_DARK_AQUA%", ChatColor.DARK_AQUA.toString());
		mess = mess.replace("%C_DARK_BLUE%", ChatColor.DARK_BLUE.toString());
		mess = mess.replace("%C_DARK_GRAY%", ChatColor.DARK_GRAY.toString());
		mess = mess.replace("%C_DARK_GREEN%", ChatColor.DARK_GREEN.toString());
		mess = mess.replace("%C_DARK_PURPLE%", ChatColor.DARK_PURPLE.toString());
		mess = mess.replace("%C_DARK_RED%", ChatColor.DARK_RED.toString());
		mess = mess.replace("%C_GOLD%", ChatColor.GOLD.toString());
		mess = mess.replace("%C_GRAY%", ChatColor.GRAY.toString());
		mess = mess.replace("%C_GREEN%", ChatColor.GREEN.toString());
		mess = mess.replace("%C_LIGHT_PURPLE%", ChatColor.LIGHT_PURPLE.toString());
		mess = mess.replace("%C_RED%", ChatColor.RED.toString());
		mess = mess.replace("%C_WHITE%", ChatColor.WHITE.toString());
		mess = mess.replace("%C_YELLOW%", ChatColor.YELLOW.toString());
		mess = mess.replace("%Entity%", event.getEntity().toString());
		mess = mess.replace("%World%", loc.getWorld().getName());
		mess = mess.replace("%x%", Integer.toString(loc.getBlockX()));
		mess = mess.replace("%y%", Integer.toString(loc.getBlockY()));
		mess = mess.replace("%z%", Integer.toString(loc.getBlockZ()));
		
		Player closest = findClosestPlayer(loc);
		double minDistance;
		if (closest != null) {
			minDistance = closest.getLocation().distance(loc);
			minDistance = Math.floor( minDistance * 10 ) / 10;
			mess = mess.replace("%ClosestPlayer%", closest.getName());
			mess = mess.replace("%ClosestPlayerDistance%", Double.toString(minDistance));
		}
		else {
			mess = mess.replace("%ClosestPlayer%", "no one");
			mess = mess.replace("%ClosestPlayerDistance%", "very far");
		}
		CreeperMess.log(mess);
		for (Player player : p.getServer().getOnlinePlayers())
		if (p.crossworld || player.getWorld().equals(closest.getWorld())) {
			player.sendMessage(mess);
		}
	}
}
