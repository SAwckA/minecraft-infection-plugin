package org.example.sawcka.test123.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.example.sawcka.test123.ListInfected;

import java.util.List;

public class ListInfectedCMD implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        ListInfected listInfected = ListInfected.getInstance();

        StringBuilder msg = new StringBuilder("Список заражённых игроков:\n");

        for (int i = 0; i < listInfected.getLength(); i++) {
            msg.append(String.format("%d. %s stage = %d duration %d\n",
                    i,
                    listInfected.getNickname(i),
                    listInfected.getStage(i),
                    listInfected.getDuration(i)
            ));
        }

        commandSender.sendMessage(msg.toString());

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return null;
    }
}
