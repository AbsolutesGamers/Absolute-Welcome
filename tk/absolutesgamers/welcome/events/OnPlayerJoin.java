package tk.absolutesgamers.welcome.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import tk.absolutesgamers.welcome.managers.FilesManager;

public class OnPlayerJoin implements Listener {
	
	private FilesManager files = FilesManager.load();
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if(player.hasPermission("welcome.see")) {
			player.sendMessage(files.getTranslation().getString("welcome.see.sucess").replaceAll("&", "§").replaceAll("%p", player.getName()));
		}
	}
}
