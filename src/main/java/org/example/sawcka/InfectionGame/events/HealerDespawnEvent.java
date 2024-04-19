package org.example.sawcka.InfectionGame.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.MerchantInventory;

public class HealerDespawnEvent implements Listener {

    @EventHandler
    public void onHeal(InventoryClickEvent event) {

        if (event.getClickedInventory() instanceof MerchantInventory merchantInventory){

            try {
                Merchant villager = merchantInventory.getMerchant();

            } catch (ClassCastException ignored) {
            }
        }
    }
}
