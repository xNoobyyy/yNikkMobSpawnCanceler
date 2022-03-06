package de.ynikk.mobspawncanceler;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class MobSpawnCanceler extends JavaPlugin {

    public static MobSpawnCanceler instance;
    public static File dataFile;
    public static YamlConfiguration dataConfig;

    @Override
    public void onEnable() {
        instance = this;

        this.getCommand("cancelmobspawns").setExecutor(new CancelMobSpawnsCommand());
        Bukkit.getPluginManager().registerEvents(new MobSpawnListener(), this);

        dataFile = new File("plugins/MobSpawnCanceler", "data.yml");
        dataConfig = YamlConfiguration.loadConfiguration(dataFile);
        dataConfig.options().copyDefaults(true);
        try {
            dataConfig.save(dataFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {

    }

}
