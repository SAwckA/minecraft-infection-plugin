package org.example.sawcka.test123;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;

import java.util.ArrayList;

public class Healer {
    private static final Villager.Profession PROFESSION = Villager.Profession.CLERIC;
    private static final String NAME = "Целитель";

    public static String getName(){
        return NAME;
    }

    public static Villager createHealer(World world, Location location, Material ingredientMaterial, int tradesAmount, boolean isAI){
        Villager villager = (Villager) world.spawnEntity(location, EntityType.VILLAGER);

        villager.setProfession(PROFESSION);
        villager.setVillagerLevel(2);
        villager.setCustomName(NAME);
        villager.setCustomNameVisible(true);
        villager.setAI(isAI);

        ItemStack drug = Drug.getDrug();
        ItemStack ingredient = new ItemStack(ingredientMaterial, 1);
        MerchantRecipe recipe = new MerchantRecipe(drug, tradesAmount);

        recipe.addIngredient(ingredient);

        ArrayList<MerchantRecipe> recipe_list = new ArrayList<MerchantRecipe>();
        recipe_list.add(recipe);
        villager.setRecipes(recipe_list);

        return villager;
    }
}
