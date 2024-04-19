package org.example.sawcka.test123.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.example.sawcka.test123.EnemyWave;

import java.util.List;

public class SpawnWaveCMD implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player player = (Player) commandSender;

        try {

            if (args.length == 0) {
                commandSender.sendMessage("Usage: /spawnwave <wave>");
                return true;
            }


            double x = args[0].equals("~") ? player.getLocation().getX() : Double.parseDouble(args[0]);
            double y = args[1].equals("~") ? player.getLocation().getY() : Double.parseDouble(args[1]);
            double z = args[2].equals("~") ? player.getLocation().getZ() : Double.parseDouble(args[2]);


            int radius = Integer.parseInt(args[3]);
            int difficulty = Integer.parseInt(args[4]);

            Location location = new Location(player.getWorld(), x, y, z);

            commandSender.sendMessage("Spawning wave " + difficulty + " difficulty");



            EnemyWave.spawnEnemies(player.getWorld(), location, radius, difficulty);


        } catch (IndexOutOfBoundsException ignored) {

        }


        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {

        if (strings.length == 1 | strings.length == 2 | strings.length == 3) {
            return List.of("~");
        }

        if (strings.length == 4) {
            return List.of("10", "20", "30", "<radius>");
        }

        if (strings.length == 5) {
            return List.of("0", "1", "2", "3", "<difficulty>");
        }

        return null;
    }
}
