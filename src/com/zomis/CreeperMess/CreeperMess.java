package com.zomis.CreeperMess;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.zomis.CreeperMess.CreeperMessEntityListener;


/**
 * CreeperMess 1.1 for Bukkit
 *
 * @author Zomis
 */

public class CreeperMess extends JavaPlugin {
    public FileConfiguration config = null;
    public static CreeperMess p;
    public String message;
    public boolean crossworld;

    public void onEnable() {
    	//Load config
    	p = this;
    	config = this.getConfig();
    	config.addDefault("message", "%C_DARK_GREEN%A creeper blew up at level %y% in %World%, %ClosestPlayerDistance% blocks from %ClosestPlayer%");
    	config.addDefault("crossworldmessages", true);

    	message = config.getString("message");
    	crossworld = config.getBoolean("crossworldmessages");
    	
    	config.set("message", message);
    	config.set("crossworldmessages", crossworld);
    	
    	try {
    		config.save(new File(p.getDataFolder(), "CreeperMess.conf"));
    	}
    	catch (IOException e) {
    		log(e.toString());
    	}
    	
/*    	if (changed) {
    		config.save();
    		changed = false;
    	}*/

        // Register events
    	getServer().getPluginManager().registerEvents(new CreeperMessEntityListener(this), this);

        log("Enabled!");
    }

    public void onDisable() {
        log("Disabled!");
    }
    
	public static void log(String msg) {
		Logger.getLogger("Minecraft").log(Level.INFO, "["+p.getDescription().getFullName()+"] "+msg);
	}
}

