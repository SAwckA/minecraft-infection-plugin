package org.example.sawcka.test123.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.example.sawcka.test123.ListInfected;

import java.util.List;

public class RemoveInfectionCMD implements CommandExecutor, TabCompleter {

    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String label, String[] args) {

        Player player = Bukkit.getPlayer(args[0]);
        ListInfected listInfected = ListInfected.getInstance();

        listInfected.removePlayer(player.getName());

        commandSender.sendMessage(player.getName() + " has been removed from the infected list");

        return true;

    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 1) {
            return Bukkit.getOnlinePlayers().stream().map(Player::getName).toList();
        }
        return List.of();
    }
}
