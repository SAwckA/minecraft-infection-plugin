package org.example.sawcka.InfectionGame.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class TestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Bukkit.getLogger().info("command execution");

        Player player = (Player) commandSender;

        commandSender.sendMessage("§2test");
        return true;
    }
}
