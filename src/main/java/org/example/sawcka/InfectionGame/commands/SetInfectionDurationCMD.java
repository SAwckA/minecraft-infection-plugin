package org.example.sawcka.InfectionGame.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.example.sawcka.InfectionGame.ListInfected;

import java.util.List;

public class SetInfectionDurationCMD implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (args.length == 0) {
            commandSender.sendMessage("Infection duration is " + ListInfected.durationToChangeStage);
            return true;
        }
        int newDuration = Integer.parseInt(args[0]);

        ListInfected.durationToChangeStage = newDuration;
        commandSender.sendMessage("Infection duration is " + ListInfected.durationToChangeStage);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 1) {
            return List.of(
                    String.format("%d", 2 * 60 * 60),
                    "<duration:seconds>"
            );
        }
        return List.of();
    }
}
