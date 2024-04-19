package org.example.sawcka.test123.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.example.sawcka.test123.ListInfected;

import java.util.List;

public class InfectCMD implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        try {

            Player player = Bukkit.getPlayer(args[0]);
            ListInfected listInfected = ListInfected.getInstance();
            listInfected.addPlayer(player.getName());

            commandSender.sendMessage("Player " + args[0] + " is infected");

        } catch (Exception e) {
            Bukkit.getLogger().info(e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 1) {
            return Bukkit.getOnlinePlayers().stream().map(Player::getName).toList();
        }
        return null;
    }
}
