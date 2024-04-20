package org.example.sawcka.InfectionGame.events;

import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;
import org.example.sawcka.InfectionGame.Drug;
import org.example.sawcka.InfectionGame.Healer;
import org.example.sawcka.InfectionGame.SoundsVisualEffects;

public class TemporaryVillagerDieEvent implements Listener {

    @EventHandler
    public void onVillagerDie(EntityDeathEvent event) {

        if (!(event.getEntity() instanceof Villager villager)) {
            return;
        }

        if(villager.getRecipes().isEmpty()) return;
        if(villager.getRecipes().get(0) == null) return;

        ItemStack drug = Drug.getDrug();
        MerchantRecipe recipe = villager.getRecipes().get(0);

        if (recipe.getResult().equals(drug) && recipe.getMaxUses() == 1) {
            Healer.isTemporaryVillagerAlive = false;
        }
    }
}
