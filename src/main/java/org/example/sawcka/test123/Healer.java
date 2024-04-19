package org.example.sawcka.test123;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPortalEnterEvent;
import org.bukkit.event.entity.VillagerAcquireTradeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;

import java.util.ArrayList;

public class Healer {
    private static final Villager.Profession PROFESSION = Villager.Profession.CLERIC;
    private static final String NAME = "Целитель";
    public static String getName(){
        return NAME;
    }

    public static void createHealer(World world, Location location, Material ingredientMaterial, int tradesAmount, boolean isAI, int expReward){
        Villager villager = (Villager) world.spawnEntity(location, EntityType.VILLAGER);

        villager.setProfession(PROFESSION);
        villager.setVillagerLevel(2);
        villager.setCustomName(NAME);
        villager.setCustomNameVisible(true);
        villager.setAI(isAI);

        ItemStack drug = Drug.getDrug();
        ItemStack ingredient = new ItemStack(ingredientMaterial, 1);
        MerchantRecipe recipe = new MerchantRecipe(drug, 0, tradesAmount, true, expReward, 1f);

        recipe.addIngredient(ingredient);

        ArrayList<MerchantRecipe> recipe_list = new ArrayList<MerchantRecipe>();
        recipe_list.add(recipe);
        villager.setRecipes(recipe_list);
    }
}
