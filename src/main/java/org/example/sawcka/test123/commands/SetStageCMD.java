package org.example.sawcka.test123.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.example.sawcka.test123.ListInfected;

import java.util.List;

public class SetStageCMD implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        Player target = Bukkit.getPlayer(args[0]);
        int stage = Integer.parseInt(args[1]);

        ListInfected listInfected = ListInfected.getInstance();

        listInfected.setStage(target.getName(), stage);

        String msg = String.format("Set stage %d to %s", stage, target.getName());
        commandSender.sendMessage(msg);

        return true;

    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 1) {
            return Bukkit.getOnlinePlayers().stream().map(Player::getName).toList();
        }
        if (strings.length == 2) {
            return List.of("1", "2", "3");
        }
        return List.of();
    }
}
