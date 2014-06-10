package tk.absolutesgamers.welcome;

import org.bukkit.plugin.java.JavaPlugin;

import tk.absolutesgamers.welcome.commands.CommandWelcome;
import tk.absolutesgamers.welcome.events.OnPlayerJoin;
import tk.absolutesgamers.welcome.managers.FilesManager;

public class Welcome extends JavaPlugin {
	
	public void onEnable() {
		FilesManager.load().instalar(this);
		getServer().getPluginManager().registerEvents(new OnPlayerJoin(), this);
		getCommand("welcome").setExecutor(new CommandWelcome());
		getLogger().info("Plugin Activated!");
	}
	
	public void onDisable() {
		getLogger().info("Plugin Desactivated!");
	}
}
