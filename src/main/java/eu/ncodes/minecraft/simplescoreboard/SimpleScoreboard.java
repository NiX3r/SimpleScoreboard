package eu.ncodes.minecraft.simplescoreboard;

import eu.ncodes.minecraft.simplescoreboard.listeners.OnPlayerJoin;
import eu.ncodes.minecraft.simplescoreboard.utils.PluginUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;

import java.io.IOException;

public final class SimpleScoreboard extends JavaPlugin {

    @Override
    public void onEnable() {

        // Initialize plugin
        getLogger().info("Initializing plugin...");
        this.getDataFolder().mkdirs();
        PluginUtils.plugin = this;
        PluginUtils.manager = Bukkit.getScoreboardManager();
        PluginUtils.board = PluginUtils.manager.getNewScoreboard();
        PluginUtils.objective = PluginUtils.board.registerNewObjective("ss-§template", "ss-§template");

        // Initialize listeners
        getLogger().info("Initializing listeners...");
        getServer().getPluginManager().registerEvents(new OnPlayerJoin(), this);

        // Initialize scoreboard json
        getLogger().info("Initializing files...");
        PluginUtils.createFileDefaults( createError -> {

            if(createError == null){

                PluginUtils.loadFileDefaults( cache -> {

                    if(cache == null){

                        getLogger().warning("Can't load 'scoreboard.json' file. Please try again later! Turning plugin off...");
                        Bukkit.getPluginManager().disablePlugin(this);

                    } else{

                        PluginUtils.loadDataToCache(cache, loadError -> {

                            if(loadError == null){
                                getLogger().info("Initializing completed. Data load in cache. Plugin is ready to use");
                            } else {
                                getLogger().warning("Can't load data into cache. Turning plugin off...");
                                Bukkit.getPluginManager().disablePlugin(this);
                            }

                        });

                    }

                });

            }

        });

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
