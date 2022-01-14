package eu.ncodes.minecraft.simplescoreboard.listeners;

import eu.ncodes.minecraft.simplescoreboard.utils.PluginUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnPlayerJoin implements Listener {

    @EventHandler
    public void On(PlayerJoinEvent event){

        //event.getPlayer().setScoreboard(PluginUtils.board);

    }

}
