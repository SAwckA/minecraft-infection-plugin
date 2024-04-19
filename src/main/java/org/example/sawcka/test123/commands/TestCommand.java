package org.example.sawcka.test123.commands;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.example.sawcka.test123.SoundsVisualEffects;


public class TestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Bukkit.getLogger().info("command execution");

        Player player = (Player) commandSender;

        commandSender.sendMessage("§2test");
        return true;
    }
}
