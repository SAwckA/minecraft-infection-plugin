package org.example.sawcka.test123;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPortalEnterEvent;
import org.bukkit.World;
import java.util.List;

public class PortalBlackout implements CommandExecutor, TabCompleter, Listener {

    private boolean isActive = false;

    public PortalBlackout() {
        isActive = false;
    }

    @EventHandler
    public void onEnterPortal(EntityPortalEnterEvent event) {
        if (!isActive) {
            return;
        }
        try {
            Player player = (Player) event.getEntity();
            World world = player.getWorld();

            if (world.getName().equals("world-nether")) {
                return;
            }

            Block block = world.getBlockAt(player.getLocation());
            block.breakNaturally();

        } catch (Exception ignored) {

        }


    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            if (isActive) {
                isActive = false;
                sender.sendMessage("Portal Blackout disabled.");
            } else {
                isActive = true;
                sender.sendMessage("Portal Blackout enabled.");
            }
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return null;
    }

}
