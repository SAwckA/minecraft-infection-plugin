package org.example.sawcka.test123.events;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.VillagerAcquireTradeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.example.sawcka.test123.Drug;
import org.example.sawcka.test123.Healer;
import org.example.sawcka.test123.SoundsVisualEffects;

public class HealerAcquireEvent implements Listener {

    @EventHandler
    public void onVillagerAcquire(VillagerAcquireTradeEvent event) {
        Villager villager = (Villager) event.getEntity();
        if (villager.getName().equals(Healer.getName())) {
            if(villager.getRecipes().isEmpty()) return;
            if(villager.getRecipes().get(0) == null) return;

            ItemStack drug = Drug.getDrug();
            if (villager.getRecipes().get(0).getResult().equals(drug)) {
                SoundsVisualEffects.healerDeath(villager);

                villager.damage(villager.getHealth());

            }



        }
    }
}
