package org.example.sawcka.InfectionGame.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.plugin.Plugin;
import org.example.sawcka.InfectionGame.Drug;
import org.example.sawcka.InfectionGame.ListInfected;

public class ConsumingEvent implements Listener {

    private Plugin plugin;

    public ConsumingEvent(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onConsuming(PlayerItemConsumeEvent event) {
        ItemStack item = event.getItem();
        Player player = event.getPlayer();
        ListInfected listInfected = ListInfected.getInstance();


        if (item.getType().equals(Material.MILK_BUCKET)) {
            Bukkit.getServer().getScheduler().runTaskLater(plugin, () -> {
                listInfected.apply(player.getName());
            }, 1);

        } else if (item.getType().equals(Material.POTION)) {
            ItemMeta meta = item.getItemMeta();

            if (meta == null) {
                return;
            }

            PersistentDataContainer data = item.getItemMeta().getPersistentDataContainer();
            NamespacedKey key = Drug.key;

            if (key == null) {
                return;
            }

            if (data.has(key)) {
                listInfected.healPlayer(player.getName());
                Bukkit.getLogger().info("[DRUG] " +  player.getName() + "used drug");
            }
        }
    }
}
