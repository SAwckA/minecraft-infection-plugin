package org.example.sawcka.InfectionGame;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;

import java.util.ArrayList;
import java.util.Random;

public class Healer {
    private static final Villager.Profession PROFESSION = Villager.Profession.CLERIC;
    private static final String NAME = "Целитель";
    private static final Material ingredientMaterial = Material.DIAMOND;
    private static final int ingredientAmount = 4;
    private static Villager mainVillager = null;
    private static Villager temporaryVillager = null;

    public static boolean doRespawnTemporaryVillager = false;

    public static void spawnTemporaryVillager(){
        if (Bukkit.getOnlinePlayers().isEmpty()) {
            return;
        }
        Player player = (Player) Bukkit.getOnlinePlayers().toArray()[new Random().nextInt(Bukkit.getOnlinePlayers().size())];

        if (temporaryVillager == null || temporaryVillager.isDead()) {
            if (!doRespawnTemporaryVillager) { return; }
            Location location = player.getRespawnLocation();
            temporaryVillager = createHealer(location.getWorld(), location, 1, true, 1000);
        }
    }

    public static Villager getMainVillager(){
        return mainVillager;
    }

    public static String getName(){
        return NAME;
    }

    public static Villager createHealer(World world, Location location, int tradesAmount, boolean isAI, int expReward){
        Villager villager = (Villager) world.spawnEntity(location, EntityType.VILLAGER);

        villager.setProfession(PROFESSION);
        villager.setVillagerLevel(2);
        villager.setCustomName(NAME);
        villager.setCustomNameVisible(true);
        villager.setAI(isAI);

        ItemStack drug = Drug.getDrug();
        ItemStack ingredient = new ItemStack(ingredientMaterial, ingredientAmount);
        MerchantRecipe recipe = new MerchantRecipe(drug, 0, tradesAmount, true, expReward, 1f);

        recipe.addIngredient(ingredient);

        ArrayList<MerchantRecipe> recipe_list = new ArrayList<MerchantRecipe>();
        recipe_list.add(recipe);
        villager.setRecipes(recipe_list);

        if (tradesAmount > 1) {
            if (mainVillager != null) {
                if (!mainVillager.isDead()) {
                    mainVillager.damage(20);
                }
            }
            mainVillager = villager;
        }
        return villager;
    }
}
