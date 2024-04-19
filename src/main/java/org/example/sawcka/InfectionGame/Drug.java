package org.example.sawcka.InfectionGame;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

public class Drug {

    private static final String name = "Лекарство)";
    private static final String description = "Лекарство от чумы)";
    private static final PotionEffectType effectType = PotionEffectType.LUCK;
    private static final int duration = 15 * 20 * 60;
    public static final NamespacedKey key = NamespacedKey.fromString("drug");
    public static final PersistentDataType<Byte, Boolean> keyType = PersistentDataType.BOOLEAN;
    public static final boolean keyDefaultValue = true;


    public static ItemStack getDrug() {

        ItemStack drug = new ItemStack(Material.POTION, 1);

        PotionMeta meta = (PotionMeta) drug.getItemMeta();

        meta.setDisplayName(name);
        ArrayList<String> lore = new ArrayList<>();
        lore.add(description);
        meta.setLore(lore);

        meta.getPersistentDataContainer().set(key, keyType, keyDefaultValue);

        PotionEffect effect = new PotionEffect(effectType, duration, 0);
        meta.addCustomEffect(effect, true);

        drug.setItemMeta(meta);
        return drug;
    }
}
