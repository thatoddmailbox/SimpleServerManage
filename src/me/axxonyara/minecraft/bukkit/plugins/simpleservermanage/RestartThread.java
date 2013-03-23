package me.axxonyara.minecraft.bukkit.plugins.simpleservermanage;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class RestartThread implements Runnable {

	static Integer i = 0;
	static SimpleServerManage plugin;
	
	public RestartThread(Integer wait, SimpleServerManage plug) {
    	i = wait;
    	plugin = plug;
	}
    public void run() {
		while (i > 0){
			for (Player sendTo : plugin.getServer().getOnlinePlayers()) {
				sendTo.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "Server restart in " + i + " second(s)...");
			}
			plugin.log(ChatColor.BLUE + "Server restart in " + i + " second(s)...");
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				plugin.error("An exception occured while waiting for a second! " + e.getMessage());
			}
			i = i - 1;
		}
		for (Player sendTo : plugin.getServer().getOnlinePlayers()) {
			sendTo.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "The server is restarting!");
		}
		plugin.log(ChatColor.BLUE + "The server is restarting!");
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
