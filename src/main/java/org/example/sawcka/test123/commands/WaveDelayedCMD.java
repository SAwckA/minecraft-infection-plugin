package org.example.sawcka.test123.commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.scheduler.BukkitTask;
import org.example.sawcka.test123.Test123;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class WaveDelayedCMD implements CommandExecutor, TabCompleter {

    private final String message = "нападение";
    private final String spawnMessage = "Вам всем пиздос";

    private final ArrayList<BukkitTask> timer_tasks = new ArrayList<>();

    private void clearTasks() {
        for (BukkitTask task : timer_tasks) {
            task.cancel();
        }
        timer_tasks.clear();
    }

    private void broadCastMessage(String message) {
        TextComponent textComponent = new TextComponent(message);
        textComponent.setColor(ChatColor.GOLD);

        Bukkit.getServer().spigot().broadcast(textComponent);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (args.length < 1) {
            clearTasks();
            return true;
        }

        int timeout = Integer.parseInt(args[5]) * 60;

        clearTasks();

        SpawnWaveCMD spawnWaveCMD = new SpawnWaveCMD();

        for (int i = 0; i <= timeout; i++) {

            int finalI = i;
            BukkitTask task = Bukkit.getServer().getScheduler().runTaskLater(Test123.getProvidingPlugin(Test123.class), () -> {
                int time = timeout - finalI;


                if (time == 0) {
                    Bukkit.broadcastMessage(spawnMessage);

                    spawnWaveCMD.onCommand(commandSender, command, s, args);
                }

                if (time <= 5) {
                    broadCastMessage(String.format("Через %d секунд произойдёт %s", time, message));
                    return;
                }

                if (time <= 30 && time % 10 == 0) {
                    broadCastMessage(String.format("Через %d секунд произойдёт %s", time, message));
                    return;
                }

                if (time >= 60 && time % 60 == 0) {
                    broadCastMessage(String.format("Через %d минут произойдёт %s", time / 60, message));
                    return;
                }

                if (time >= (60 * 10) && time % (60 * 10) == 0) {
                    broadCastMessage(String.format("Через %d минут произойдёт %s", time / 60, message));
                }
            }, 20L * i);
            timer_tasks.add(task);
        }


        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        if (args.length == 1 | args.length == 2 | args.length == 3) {
            return List.of("~");
        }

        if (args.length == 4) {
            return List.of("10", "20", "30", "<radius>");
        }

        if (args.length == 5) {
            return List.of("0", "1", "2", "3", "<difficulty>");
        }

        if (args.length == 6) {
            return List.of("1", "10", "60", "<timeout:minutes>");
        }
        return List.of();
    }
}
