package com.zomis.CreeperMess;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event;
//import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;

import com.zomis.CreeperMess.CreeperMessEntityListener;


/**
 * CreeperMess for Bukkit
 *
 * @author Zomis
 */

public class CreeperMess extends JavaPlugin {
    private final CreeperMessEntityListener entityListener = new CreeperMessEntityListener(this);
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
//    	PluginDescriptionFile pdfFile = this.getDescription();

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
    	PluginManager pm = getServer().getPluginManager();
        pm.registerEvent(Event.Type.ENTITY_EXPLODE, entityListener, Priority.Normal, this);

        log("Enabled!");
    }

    public void onDisable() {
        log("Disabled!");
    }
    
	public static void log(String msg) {
		Logger.getLogger("Minecraft").log(Level.INFO, "["+p.getDescription().getFullName()+"] "+msg);
	}
}

