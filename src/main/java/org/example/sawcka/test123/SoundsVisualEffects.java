package org.example.sawcka.test123;

import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Villager;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SoundsVisualEffects {

    public static void healerDeath(Villager villager){
        World world = villager.getWorld();
        villager.setSilent(true);
        villager.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, -1, 0));

        world.playSound(villager.getLocation(), Sound.AMBIENT_CAVE, 1f, 1f);
        world.spawnParticle(Particle.DRAGON_BREATH, villager.getLocation(), 500);
    }

    public static void spawnEffect(World world, Location location){
        world.spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, location, 250);
    }
}
