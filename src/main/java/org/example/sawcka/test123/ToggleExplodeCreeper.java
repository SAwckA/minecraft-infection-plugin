package org.example.sawcka.test123;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.entity.Creeper;

import java.util.List;


public class ToggleExplodeCreeper implements CommandExecutor, Listener, TabCompleter {

    private boolean enabled = false;

    public ToggleExplodeCreeper() {
        this.enabled = false;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (enabled) {
            commandSender.sendMessage("Creeper explosion disabled");
            enabled = false;
            return true;
        }

        commandSender.sendMessage("Creeper explosion enabled");
        enabled = true;
        return true;

    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        return null;
    }

    @EventHandler
    public void onCreeperExplode(EntityDamageEvent event) {
        if (!enabled) {
            return;
        }
        try {
            Creeper creeper = (Creeper) event.getEntity();
            creeper.ignite();
        } catch (ClassCastException ignored) {}
    }
}
