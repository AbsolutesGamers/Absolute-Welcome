package tk.absolutesgamers.welcome.managers;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class FilesManager {
	
	private File fileTranslation;
	private FileConfiguration translation;
	
	private static FilesManager load = new FilesManager();
	
	public static FilesManager load() {
		return load;
	}
	
	public void instalar(Plugin plugin) {
		fileTranslation = new File(plugin.getDataFolder(), "translation.yml");
		
		if(!fileTranslation.exists()) {
			plugin.saveResource("translation.yml", false);
		}
		
		translation = YamlConfiguration.loadConfiguration(fileTranslation);
	}
	
	public FileConfiguration getTranslation() {
		return translation;
	}
	
	public void saveTranslation() {
		try{
			translation.save(fileTranslation);
		}catch(IOException e) {
			Bukkit.getServer().getLogger().severe("Could not save the file translation.yml!");
		}
	}
}
