package org.example.sawcka.InfectionGame.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.example.sawcka.InfectionGame.Healer;

import java.util.List;

public class ToggleHealerAutoSpawnCMD implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Healer.doRespawnTemporaryVillager = !Healer.doRespawnTemporaryVillager;
        commandSender.sendMessage("Toggled healer respawn temporary villager to " + Healer.doRespawnTemporaryVillager);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return List.of();
    }
}
