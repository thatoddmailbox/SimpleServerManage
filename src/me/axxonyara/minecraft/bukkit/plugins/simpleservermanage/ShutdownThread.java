package me.axxonyara.minecraft.bukkit.plugins.simpleservermanage;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ShutdownThread implements Runnable {

	static Integer i = 0;
	static SimpleServerManage plugin;
	
	public ShutdownThread(Integer wait, SimpleServerManage plug) {
    	i = wait;
    	plugin = plug;
	}
    public void run() {
		while (i > 0){
			for (Player sendTo : plugin.getServer().getOnlinePlayers()) {
				sendTo.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "Server shutdown in " + i + " second(s)...");
			}
			plugin.log(ChatColor.BLUE + "Server shutdown in " + i + " second(s)...");
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				plugin.error("An exception occured while waiting for a second! " + e.getMessage());
			}
			i = i - 1;
		}
		for (Player sendTo : plugin.getServer().getOnlinePlayers()) {
			sendTo.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "The server is shutting down!");
		}
		plugin.log(ChatColor.BLUE + "The server is shutting down!");
		try {
			Thread.sleep(500);
		} catch (Exception e) {
			plugin.error("An exception occured while waiting for half a second! " + e.getMessage());
		}
	    plugin.getServer().shutdown();
    }
    public static void main(String args[], SimpleServerManage plug) {

        
    }

}
