package me.itzsimondk.custommotdplus;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomMotdPlus extends JavaPlugin implements Listener {
	
	
		@Override
		public void onEnable() {
			
			getLogger().info("Enabled CustomMotd+ by ItzSimonDK");
			getServer().getPluginManager().registerEvents(this, this);
			getConfig().options().copyDefaults(true);
			saveDefaultConfig();
		}
		
		@Override
		public void onDisable() {
			getLogger().info("Disabled CustomMotd+ by ItzSimonDK");
		}
		
		@EventHandler
		public void onPingEvent(ServerListPingEvent event) {
			event.setMotd(ChatColor.translateAlternateColorCodes('&', getConfig().getString("motd1") + "\n" + getConfig().getString("motd2")));
			event.setMaxPlayers(getConfig().getInt("maxplayers"));
		}
		
		
		public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
			if(sender instanceof Player) {
				Player player = (Player) sender;
				if(command.getName().equalsIgnoreCase("cmp")) {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("cmpprefix")) + ChatColor.RED + " Version: " + getDescription().getVersion());
				}
			} else {
				getLogger().info("Only players can use this command!");
			} return true;
		}
		
		@EventHandler
		public void onPlayerJoin(PlayerJoinEvent event) {
				event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("joinmessage").replace("{player}", event.getPlayer().getName())));
		}
		
		@EventHandler
		public void onPlayerQuit(PlayerQuitEvent event) {
				event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("quitmessage").replace("{player}", event.getPlayer().getName())));
		}
}
