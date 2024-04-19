package org.example.sawcka.test123.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.VillagerReplenishTradeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.MerchantInventory;
import org.example.sawcka.test123.Healer;

public class HealerDespawnEvent implements Listener {

    @EventHandler
    public void onHeal(InventoryClickEvent event) {

        if (event.getClickedInventory() instanceof MerchantInventory merchantInventory){

            try {
                Merchant villager = merchantInventory.getMerchant();

                Bukkit.getLogger().info(villager.getRecipes().get(0).getResult().toString());



            } catch (ClassCastException ignored) {
            }
        }
    }
}
