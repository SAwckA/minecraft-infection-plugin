package org.example.sawcka.InfectionGame;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;

public class Settings implements Serializable {

    private static File file;
    private static FileConfiguration customFile;

    public static void setup(){
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("InfectionGame").getDataFolder(), "config.yml");
        if (!file.exists()){
            try{
                file.createNewFile();
            }catch (IOException e){
            }
        }
        customFile = YamlConfiguration.loadConfiguration(file);
    }



    public static FileConfiguration get(){
        return customFile;
    }



    public static void save(){
        try{
            customFile.save(file);
        }catch (IOException e){
            Bukkit.getLogger().warning("Couldn't save file");
        }
    }

    public static void reload(){
        customFile = YamlConfiguration.loadConfiguration(file);
    }
}
