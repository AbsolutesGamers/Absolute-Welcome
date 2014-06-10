package tk.absolutesgamers.welcome.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tk.absolutesgamers.welcome.managers.FilesManager;

public class CommandWelcome implements CommandExecutor {
	
	private FilesManager files = FilesManager.load();
	
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		if(!(sender instanceof Player)) {
			if(command.getName().equalsIgnoreCase("welcome")) {
				if(args.length == 0) {
					sender.sendMessage(files.getTranslation().getString("welcome.see.sucess").replaceAll("&", "§").replaceAll("%p", "(Player Name)"));
					return true;
				}
				if((args.length > 0) && (args[0].equalsIgnoreCase("set"))) {
					StringBuilder welcomeMessage = new StringBuilder();
					for(Integer i = 1; i < args.length; i++) {
						welcomeMessage.append(args[i] + " ");
					}
					String stringWelcomeMessage = welcomeMessage.toString();
					files.getTranslation().set("welcome.see.sucess", stringWelcomeMessage);
					files.saveTranslation();
					sender.sendMessage(files.getTranslation().getString("welcome.set.sucess").replaceAll("&", "§").replaceAll("%nm", files.getTranslation().getString("welcome.see.sucess")).replaceAll("&", "§").replaceAll("%p", "(Player Name)"));
					return true;
				}
				sender.sendMessage(files.getTranslation().getString("welcome.invalidCommand").replaceAll("&", "§"));
				return true;
			}
		}
		
		Player player = (Player) sender;
		
		if(command.getName().equalsIgnoreCase("welcome")) {
			if((args.length == 0) && (player.hasPermission("welcome.see"))) {
				player.sendMessage(files.getTranslation().getString("welcome.see.sucess").replaceAll("&", "§").replaceAll("%p", player.getName()));
				return true;
			}
			if((args.length == 0) && (!player.hasPermission("welcome.see"))) {
				player.sendMessage(files.getTranslation().getString("welcome.see.noPermission").replaceAll("&", "§"));
				return true;
			}
			if((args.length > 0) && (player.hasPermission("welcome.set")) && (args[0].equalsIgnoreCase("set"))) {
				StringBuilder welcomeMessage = new StringBuilder();
				for(Integer i = 1; i < args.length; i++) {
					welcomeMessage.append(args[i] + " ");
				}
				String stringWelcomeMessage = welcomeMessage.toString();
				files.getTranslation().set("welcome.see.sucess", stringWelcomeMessage);
				files.saveTranslation();
				player.sendMessage(files.getTranslation().getString("welcome.set.sucess").replaceAll("&", "§").replaceAll("%nm", files.getTranslation().getString("welcome.see.sucess")).replaceAll("&", "§").replaceAll("%p", player.getName()));
				return true;
			}
			if((args.length > 0) && (!player.hasPermission("welcome.set")) && (args[0].equalsIgnoreCase("set"))) {
				player.sendMessage(files.getTranslation().getString("welcome.set.noPermission").replaceAll("&", "§"));
				return true;
			}
			player.sendMessage(files.getTranslation().getString("welcome.invalidCommand").replaceAll("&", "§"));
			return true;
		}
		return false;
	}
}
