package org.example.sawcka.InfectionGame.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.example.sawcka.InfectionGame.InfectionGame;
import org.example.sawcka.InfectionGame.ListInfected;

public class PotionEffectEvent implements Listener {

    @EventHandler
    public void onModifiedEffect(EntityPotionEffectEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        if (!ListInfected.getInstance().isContainsPlayer(player.getName())) return;

        if (event.getCause() == EntityPotionEffectEvent.Cause.EXPIRATION) {
            Bukkit.getServer().getScheduler().runTaskLater(InfectionGame.getProvidingPlugin(InfectionGame.class), () -> {
                ListInfected.getInstance().apply(player.getName());
            }, (1));
        }
    }
}