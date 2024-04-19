package org.example.sawcka.test123;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

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

    public static void beginWave(World world, Location location){
        Bukkit.getLogger().info(location.toString() + "wave begin sound");
        world.playSound(location, Sound.ITEM_GOAT_HORN_SOUND_5, 1000f, 0.1f);
    }

    public static void sneeze(Player player){
        World world = player.getWorld();

        Location location = player.getLocation();
        location.setY(location.getY() + 1);

        Vector facing = location.getDirection();

        world.spawnParticle(
                Particle.SNOWFLAKE,
                location.getX() + facing.getX() / 3,
                location.getY() + facing.getY() + 0.5,
                location.getZ() + facing.getZ() / 3,
                1,
                0,
                0,
                0,
                0
        );
        world.playSound(player.getLocation(), Sound.ENTITY_SILVERFISH_HURT, 1f, 1f);
    }
}
