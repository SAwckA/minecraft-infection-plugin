package org.example.sawcka.test123.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.example.sawcka.test123.Healer;

import java.util.List;

public class SpawnHealerCMD implements CommandExecutor, TabCompleter {
    private final int ingredientAmount = 1;
    private final int maxUses = 1;


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        Player player = (Player) commandSender;
        int maxTrades = 1;
        double x = 0;
        double y = 0;
        double z = 0;
        boolean isAi = true;
        int expReward = 100000;


        try {
            maxTrades = Integer.parseInt(args[0]);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException ignored) {
        }

        try {
            x = Double.parseDouble(args[1]);
            y = Double.parseDouble(args[2]);
            z = Double.parseDouble(args[3]);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException ignored) {
        }


        try {
            isAi = Boolean.parseBoolean(args[4]);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException ignored) {}

        try  {
            expReward = Integer.parseInt(args[5]);
        }
        catch (ArrayIndexOutOfBoundsException | NumberFormatException ignored) {}

        Location location = player.getLocation();
        if (!(x == 0 && y == 0 && z == 0)) {
            location = new Location(player.getWorld(), x, y, z);
        }

        Healer.createHealer(
                player.getWorld(),
                location,
                maxTrades,
                isAi,
                expReward
        );

        commandSender.sendMessage("villager spawned at " + player.getLocation().toString());

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 1) {
            return List.of(
                    "1",
                    "999999",
                    "<max_trades>"
            );
        }
        if (strings.length == 2 | strings.length == 3 | strings.length == 4) {
            return List.of("~");
        }

        if (strings.length == 5) {
            return List.of(
                    "true",
                    "false",
                    "<has_ai>"
            );
        }
        if (strings.length == 6) {
            return List.of(
                    "0",
                    "1000000",
                    "<exp_reward>"
            );
        }

        return List.of();
    }
}
