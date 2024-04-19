package org.example.sawcka.InfectionGame.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.Plugin;
import org.example.sawcka.InfectionGame.ListInfected;

public class RespawnEvent implements Listener {

    private Plugin plugin;

    public RespawnEvent(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        ListInfected listInfected = ListInfected.getInstance();

        Player player = event.getPlayer();
        if (listInfected.isContainsPlayer(player.getName())) {
            Bukkit.getServer().getScheduler().runTaskLater(plugin, () -> {
                listInfected.apply(player.getName());
            }, 1);
        }

    }
}
