package org.example.sawcka.test123.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.Bukkit;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.example.sawcka.test123.ListInfected;

public class ToggleFlyEvent implements Listener {

    @EventHandler
    public void onToggleFly(EntityToggleGlideEvent event) {

        ListInfected listInfected = ListInfected.getInstance();

        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            if (!player.isGliding()) {
                int stage = listInfected.getStage(player.getName());
                if (player.hasPotionEffect(PotionEffectType.LUCK)) {
                    return;
                }
                if (stage >= 2) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, -1, 9));
                }
            } else {
                player.removePotionEffect(PotionEffectType.CONFUSION);
            }
        }
    }
}
