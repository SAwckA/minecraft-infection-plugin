package org.example.sawcka.InfectionGame.events;

import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.VillagerAcquireTradeEvent;
import org.bukkit.inventory.ItemStack;
import org.example.sawcka.InfectionGame.Drug;
import org.example.sawcka.InfectionGame.Healer;
import org.example.sawcka.InfectionGame.SoundsVisualEffects;

public class HealerAcquireEvent implements Listener {

    @EventHandler
    public void onVillagerAcquire(VillagerAcquireTradeEvent event) {

        Villager villager = (Villager) event.getEntity();
        if(villager.getRecipes().isEmpty()) return;
        if(villager.getRecipes().get(0) == null) return;

        ItemStack drug = Drug.getDrug();
        if (villager.getRecipes().get(0).getResult().equals(drug)) {
            SoundsVisualEffects.healerDeath(villager);
            villager.damage(villager.getHealth());
        }
    }
}
