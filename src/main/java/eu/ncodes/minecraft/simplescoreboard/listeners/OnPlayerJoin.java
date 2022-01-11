package eu.ncodes.minecraft.simplescoreboard.listeners;

import eu.ncodes.minecraft.simplescoreboard.utils.PluginUtils;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnPlayerJoin implements Listener {

    public void On(PlayerJoinEvent event){

        event.getPlayer().setScoreboard(PluginUtils.board);

    }

}
