package org.powell.home;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class Home extends JavaPlugin {
    private File yml;
    private YamlConfiguration config;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "Homeify Enabled!");
        getCommand("homeify").setExecutor(new HomeCommand(this));

        yml = new File(getDataFolder(), "homes.yml");
        if (!yml.exists()) {
            try {
                yml.createNewFile();
            } catch (IOException e) {
                System.out.println("Cant load homes.yml!");
                return;
            }
        }
        config = YamlConfiguration.loadConfiguration(yml);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public File getYml() { return yml; }

    public YamlConfiguration getConfig() { return config; }
}
