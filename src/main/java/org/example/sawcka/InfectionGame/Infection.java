package org.example.sawcka.InfectionGame;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

public class Infection {

    private final double radius = Settings.get().getInt("infection.radius");

    public void update() {

        ListInfected listInfected = ListInfected.getInstance();
        ArrayList<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());

        for (int i = 0; i < listInfected.getLength(); i++) {

            Player infectedPlayer = listInfected.getPlayer(i);

            if (infectedPlayer == null) {
                continue;
            }

            for (Player player : players) {
                if (listInfected.isContainsPlayer(player.getName())) {
                    continue;
                }
                if (player.getPotionEffect(PotionEffectType.LUCK) != null) {
                    continue;
                }
                if (checkCords(player, infectedPlayer)) {
                    Bukkit.getLogger().info(infectedPlayer.getName() + " trying to infect: " + player.getName());
                    listInfected.tryAdd(player);
                }
            }
        }
    }

    private boolean checkCords(Player player, Player infectedPlayer) {
        return Math.sqrt(
                (Math.pow(player.getLocation().getX() - infectedPlayer.getLocation().getX(), 2)) +
                        (Math.pow(player.getLocation().getY() - infectedPlayer.getLocation().getY(), 2)) +
                        (Math.pow(player.getLocation().getZ() - infectedPlayer.getLocation().getZ(), 2))
        ) <= radius;
    }


}
