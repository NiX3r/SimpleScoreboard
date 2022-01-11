package eu.ncodes.minecraft.simplescoreboard;

import eu.ncodes.minecraft.simplescoreboard.listeners.OnPlayerJoin;
import eu.ncodes.minecraft.simplescoreboard.utils.PluginUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;

public final class SimpleScoreboard extends JavaPlugin {

    @Override
    public void onEnable() {

        // Initialize plugin
        PluginUtils.plugin = this;
        PluginUtils.manager = Bukkit.getScoreboardManager();
        PluginUtils.board = PluginUtils.manager.getNewScoreboard();
        PluginUtils.objective = PluginUtils.board.registerNewObjective("test", "test2");

        // Initialize scoreboard
        PluginUtils.objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        PluginUtils.objective.setDisplayName("Just a test");
        PluginUtils.objective.getScore("hello").setScore(0);

        // Initialize listeners
        getServer().getPluginManager().registerEvents(new OnPlayerJoin(), this);

        getLogger().info("[SimpleScoreboard] Successfully loaded");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
