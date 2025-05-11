package org.powell.home;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HomeCommand implements CommandExecutor {
    private Home main;
    public HomeCommand(Home main){ this.main = main; }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player){
            Player player = (Player) commandSender;

            switch (args[0].toLowerCase()) {
                case "add":
                    player.getLocation();
                    String basePath = "homes." + player.getUniqueId() + "." + args[1];
                    main.getConfig().set(basePath + ".x", player.getLocation().getX());
                    main.getConfig().set(basePath + ".y", player.getLocation().getY());
                    main.getConfig().set(basePath + ".z", player.getLocation().getZ());
                    main.getConfig().set(basePath + ".world", player.getLocation().getWorld().getName());
                    main.saveConfig();
                    break;
                case "tp":
                    String path = "homes." + player.getUniqueId() + "." + args[1];
                    if (main.getConfig().get(path) != null) {
                        double x = main.getConfig().getDouble(path + ".x");
                        double y = main.getConfig().getDouble(path + ".y");
                        double z = main.getConfig().getDouble(path + ".z");
                        String worldpath = main.getConfig().getString(path + ".world");
                        World world = player.getServer().getWorld(worldpath);
                        Location location = new Location(world, x, y, z);
                        player.teleport(location);
                    } else {
                        player.sendMessage(ChatColor.RED + "Home does not exist!");
                    }
                    break;
                case "delete":
                    main.getConfig().set("homes." + player.getUniqueId() + "." + args[1], null);
                    main.saveConfig();
                    break;
            }
        }
        return false;
    }
}
