package eu.ncodes.minecraft.simplescoreboard.schedulers;

import eu.ncodes.minecraft.simplescoreboard.instances.nLines;
import eu.ncodes.minecraft.simplescoreboard.utils.PluginUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreboardScheduler implements Runnable{

    public static void doScheduler(){

        Bukkit.getScheduler().scheduleAsyncRepeatingTask(PluginUtils.plugin, new ScoreboardScheduler(), 0, PluginUtils.interval);

    }

    @Override
    public void run() {

        for(Player player : Bukkit.getOnlinePlayers()){

            Scoreboard scoreboard = PluginUtils.board;
            Objective objective = PluginUtils.objective;

            objective.setDisplayName(PluginUtils.formatMessage(objective.getDisplayName(), player));

            player.getScoreboard().getEntries().forEach( entry -> { player.getScoreboard().resetScores(entry); });

            for(nLines line : PluginUtils.lines){
                objective.getScore(PluginUtils.formatMessage(line.text, player)).setScore(line.score);
            }

            player.setScoreboard(scoreboard);

        }

    }

}