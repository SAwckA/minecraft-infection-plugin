package org.example.sawcka.test123;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

public class EnemyWave {


    public static ArrayList<LivingEntity> spawnEnemies(World world, Location location, int radius, int difficulty) {

        ArrayList<LivingEntity> enemies = new ArrayList<>();

        double angle = 0;
        double dt = 0.1;

        while (angle <= 2*Math.PI) {
            double px = location.getX() + radius * Math.cos(angle);
            double pz = location.getZ() + radius * Math.sin(angle);

            double py = world.getHighestBlockYAt(new Location(world, px, location.getY(), pz)) + 1;

            spawnByWave(world, new Location(world, px, py, pz), difficulty);

            angle += dt;
        }

        return enemies;

    }

    private static void spawnByWave(World world, Location location, int difficulty) {
        double random = Math.random();

        if (random < 0.10) {
            spawnSkeleton(world, location, difficulty);
            return;
        }

        if (random < 0.15) {
            spawnCreeper(world, location, difficulty);
            return;
        }

        if (random < 0.30) {
            spawnSpider(world, location, difficulty);
            return;
        }

        spawnZombie(world, location, difficulty);
    }

    private static void spawnZombie(World world, Location location, int difficulty) {
        Zombie zombie = (Zombie) world.spawnEntity(location, EntityType.ZOMBIE);

        ItemStack mainHand = null;
        ItemStack helmet = null;
        ItemStack chest = null;
        ItemStack leggings = null;
        ItemStack boots = null;

        if (difficulty == 0) {
            return;
        }
        if (difficulty == 1) {
            zombie.setMaxHealth(40);
            return;
        }
        if (difficulty == 2) {
            helmet = new ItemStack(Material.TURTLE_HELMET);
            chest = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
            leggings = new ItemStack(Material.LEATHER_LEGGINGS);
            boots = new ItemStack(Material.GOLDEN_BOOTS);
        }
        if (difficulty == 3) {
            helmet = new ItemStack(Material.IRON_HELMET);
            chest = new ItemStack(Material.GOLDEN_CHESTPLATE);
            leggings = new ItemStack(Material.LEATHER_LEGGINGS);
            boots = new ItemStack(Material.GOLDEN_BOOTS);
        }

        if (Math.random() < 0.25 * difficulty) {
            mainHand = new ItemStack(Material.IRON_AXE);
        }

        zombie.getEquipment().setItemInMainHand(mainHand, true);
        zombie.getEquipment().setHelmet(helmet, true);
        zombie.getEquipment().setChestplate(chest, true);
        zombie.getEquipment().setLeggings(leggings, true);
        zombie.getEquipment().setBoots(boots, true);
    }

    private static void spawnSkeleton(World world, Location location, int difficulty) {
        Skeleton skeleton = (Skeleton) world.spawnEntity(location, EntityType.SKELETON);

        ItemStack mainHand = new ItemStack(Material.BOW);
        mainHand.addEnchantment(Enchantment.ARROW_DAMAGE, difficulty + 1);

        skeleton.getEquipment().setItemInMainHand(mainHand, true);
    }

    private static void spawnCreeper(World world, Location location, int difficulty) {
        Creeper creeper = (Creeper) world.spawnEntity(location, EntityType.CREEPER);
        creeper.setExplosionRadius(3 + difficulty);
    }

    private static void spawnSpider(World world, Location location, int difficulty) {
        Spider spider = (Spider) world.spawnEntity(location, EntityType.SPIDER);

        if (difficulty == 1) {
            spider.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, -1, 0));
        }

        if (difficulty == 2) {
            spider.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, -1, 0));
        }

        if (difficulty == 3) {
            spider.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, -1, 0));
        }
    }
}
