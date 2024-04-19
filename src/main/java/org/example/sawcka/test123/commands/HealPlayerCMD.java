package org.example.sawcka.test123.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.example.sawcka.test123.ListInfected;

import java.util.List;

public class HealPlayerCMD implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        Player player = Bukkit.getPlayer(args[0]);
        ListInfected listInfected = ListInfected.getInstance();

        listInfected.healPlayer(player.getName());

        commandSender.sendMessage(player.getName() + " has been healed");

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
