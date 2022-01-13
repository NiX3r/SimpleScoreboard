package eu.ncodes.minecraft.simplescoreboard.utils;

import com.google.gson.Gson;
import eu.ncodes.minecraft.simplescoreboard.instances.nLines;
import eu.ncodes.minecraft.simplescoreboard.instances.nScoreboard;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
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
import java.util.Scanner;
import java.util.function.Consumer;

public class PluginUtils {

    public static Plugin plugin;
    public static ScoreboardManager manager;
    public static Scoreboard board;
    public static Objective objective;
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
            objective.setDisplayName(formatMessage(cache.title));
            interval = cache.interval;

            for(nLines line : cache.lines){

                line.text = formatMessage(line.text);
                objective.getScore(line.text).setScore(line.score);

            }

            callback.accept(null);
        }
        catch (Exception e){
            e.printStackTrace();
            callback.accept(e);
        }

    }

    // TODO - Implement PlaceholderAPI formatting
    public static String formatMessage(String message){
        String output = message;

        output = ChatColor.translateAlternateColorCodes('&', output);

        return output;

    }

}
