package org.example.sawcka.InfectionGame.commands;

import org.bukkit.GameRule;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

public class ToggleInfiniteWeatherCMD implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player player = (Player) commandSender;
        String weatherType = args[0];
        if (weatherType.equalsIgnoreCase("rain")) {
            if (player.getWorld().hasStorm()) {
                player.getWorld().setGameRule(GameRule.DO_WEATHER_CYCLE, true); // Restore cycle
                player.getWorld().setStorm(false);
                return true;
            }
            player.getWorld().setGameRule(GameRule.DO_WEATHER_CYCLE, false); // Disable cycle
            player.getWorld().setStorm(true);
            return true;
        }
        if (weatherType.equalsIgnoreCase("thunder")) {
            if (player.getWorld().isThundering()) {
                player.getWorld().setGameRule(GameRule.DO_WEATHER_CYCLE, true); // Restore cycle
                player.getWorld().setThundering(false);
                return true;
            }
            player.getWorld().setGameRule(GameRule.DO_WEATHER_CYCLE, false); // Disable cycle
            player.getWorld().setThundering(true);
            return true;
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 1) {
            return List.of("rain", "thunder");
        }

        return List.of();
    }
}
