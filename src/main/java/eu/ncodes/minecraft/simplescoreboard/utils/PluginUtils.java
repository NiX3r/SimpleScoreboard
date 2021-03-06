package eu.ncodes.minecraft.simplescoreboard.utils;

import com.google.gson.Gson;
import eu.ncodes.minecraft.simplescoreboard.instances.nLines;
import eu.ncodes.minecraft.simplescoreboard.instances.nScoreboard;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

public class PluginUtils {

    public static Plugin plugin;
    public static ScoreboardManager manager;
    public static Scoreboard board;
    public static Objective objective;
    public static List<nLines> lines;
    public static int interval;

    public static void createFileDefaults(Consumer<Exception> callback){

        File file = new File(plugin.getDataFolder() + "/scoreboard.json");
        if(!file.exists()){
            try
            {
                InputStream stream = plugin.getResource("scoreboard.json");

                if (stream != null)
                {
                    Files.copy(stream, file.toPath());
                    callback.accept(null);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                callback.accept(e);
            } catch (IOException e) {
                e.printStackTrace();
                callback.accept(e);
            }
        }
        else
            callback.accept(null);

    }

    public static void loadFileDefaults(Consumer<nScoreboard> callback){

        File file = new File(plugin.getDataFolder() + "/scoreboard.json");

        if(file.exists()){
            try {
                String json = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
                nScoreboard scoreboard = new Gson().fromJson(json, nScoreboard.class);
                callback.accept(scoreboard);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                callback.accept(null);
            } catch (IOException e){
                e.printStackTrace();
                callback.accept(null);
            }
        }
        else
            callback.accept(null);

    }

    public static void loadDataToCache(nScoreboard cache, Consumer<Exception> callback){

        try{

            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
            objective.setDisplayName(cache.title);
            interval = cache.interval;
            lines = cache.lines;

            callback.accept(null);
        }
        catch (Exception e){
            e.printStackTrace();
            callback.accept(e);
        }

    }

    // TODO - Implement PlaceholderAPI formatting
    public static String formatMessage(String message, Player player){
        String output = message;

        output = PlaceholderAPI.setPlaceholders(player, output);
        output = ChatColor.translateAlternateColorCodes('&', output);

        return output;

    }

}
