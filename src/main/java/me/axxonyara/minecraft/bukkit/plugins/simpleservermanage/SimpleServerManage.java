package me.axxonyara.minecraft.bukkit.plugins.simpleservermanage;

import net.milkbowl.vault.permission.Permission;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class SimpleServerManage extends JavaPlugin {
	
	public static Permission permission = null;
	
	@Override
    public void onEnable(){
		log("Connecting to Permissions plugin...");
		if (!setupPermissions()) {
			error("You must have the Vault plugin!");
			error("You can download it here: http://dev.bukkit.org/server-mods/vault/");
		}
		log("Successfully connected to plugin " + permission.getName() + " via Vault!");
		log("Enabled SimpleServerManage v1.0!");
    }
 
    @Override
    public void onDisable() {
		log("Enabled SimpleServerManage v1.0!");
    }
    
    private boolean setupPermissions()
    {
        RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (permissionProvider != null) {
            permission = permissionProvider.getProvider();
        }
        return (permission != null);
    }
    
    public boolean canShutdown(CommandSender sender) {
    	if (sender instanceof Player) {
    		Player play = (Player) sender;
    		return permission.has(play, "simpleservermanage.shutdown");
    	} else {
    		return true;
    	}
    }
    
    public boolean canRestart(CommandSender sender) {
    	if (sender instanceof Player) {
    		Player play = (Player) sender;
    		return permission.has(play, "simpleservermanage.restart");
    	} else {
    		return true;
    	}
    }
    
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
    	if(cmd.getName().equalsIgnoreCase("shutdown")){ 
    		if (canShutdown(sender)) {
	    		Integer sec = 0;
	    		if (args.length > 0) {
	    			try {
	    				sec = Integer.parseInt(args[0]);
	    			} catch (Exception e) {
	    				sender.sendMessage(ChatColor.RED + "Invalid number!");
	    				return true;
	    			}
	    		}
	    		(new Thread(new ShutdownThread(sec, this))).start();
    		} else {
    			sender.sendMessage(ChatColor.RED + "You don't have permission to shut down the server!");
    		}
    		return true;
    	}
    	if(cmd.getName().equalsIgnoreCase("restart")){ 
    		if (canRestart(sender)) {
	    		Integer sec = 0;
	    		if (args.length > 0) {
	    			try {
	    				sec = Integer.parseInt(args[0]);
	    			} catch (Exception e) {
	    				sender.sendMessage(ChatColor.RED + "Invalid number!");
	    				return true;
	    			}
	    		}
	    		(new Thread(new RestartThread(sec, this))).start();
    		} else {
    			sender.sendMessage(ChatColor.RED + "You don't have permission to restart the server!");
    		}
    		return true;
    	}
    	return false; 
    }
    
    public void log(String text) {
    	getLogger().info(text);
    }
    
    public void error(String text) {
    	getLogger().severe(ChatColor.RED + text);
    }
}
