package org.example.sawcka.InfectionGame;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.*;
import java.util.ArrayList;

public class ListInfected implements Serializable {

    public static int durationToChangeStage = 60 * 60 * 2;
    private static final double chanceToInfect = 0.25;
    private static final double sneezeChance = 0.01;

    public static class InfectedPlayer implements Serializable {
        public int duration;
        public int stage;
        public String nickname;

        public InfectedPlayer(String nickname) {
            duration = 0;
            stage = 0;
            this.nickname = nickname;
        }
    }
    private ArrayList<InfectedPlayer> infectedPlayers = new ArrayList<>();

    private ArrayList<InfectedPlayer> getInfectedPlayers() {return infectedPlayers;};

    public int getLength() {
        return infectedPlayers.size();
    }

    public Player getPlayer(int index) {
        return Bukkit.getPlayer(getInfectedPlayers().get(index).nickname);
    }
    private InfectedPlayer getPlayer(String nickname) {
        for (InfectedPlayer infectedPlayer : infectedPlayers) {
            if (infectedPlayer.nickname.equals(nickname)) {
                return infectedPlayer;
            }
        }
        return null;
    }

    public void removePlayer(String name) {
        int index = -1;
        for (int i = 0; i < infectedPlayers.size(); i++) {
            if (infectedPlayers.get(i).nickname.equals(name)) {
                index = i;
                clearEffects(infectedPlayers.get(i));
                break;
            }
        }
        if (index >= 0) {
            infectedPlayers.remove(index);
        }
    }

    public void healPlayer(String name) {
        for (InfectedPlayer infectedPlayer : infectedPlayers) {
            if (infectedPlayer.nickname.equals(name)) {
                infectedPlayer.duration = 0;
                clearEffects(infectedPlayer);
                break;
            }
        }
    }

    public boolean isContainsPlayer(String name) {
        for (InfectedPlayer infectedPlayer : infectedPlayers) {
            if (infectedPlayer.nickname.equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void tryAdd(Player player) {
        if (Math.random() <= chanceToInfect) {
            addPlayer(player.getName());
            Bukkit.getLogger().info("[SUCCESS] Infected: " + player.getName());
        }
    }
    public void addPlayer(String nickname) {
        if (isContainsPlayer(nickname)) {
            return;
        }
        infectedPlayers.add(new InfectedPlayer(nickname));
    }

    private void clearEffects(InfectedPlayer infectedPlayer) {
        Player player = Bukkit.getPlayer(infectedPlayer.nickname);

        if (player == null) {
            return;
        }

        player.removePotionEffect(PotionEffectType.WEAKNESS);
        player.removePotionEffect(PotionEffectType.HUNGER);
        player.removePotionEffect(PotionEffectType.SLOW_DIGGING);
        player.setMaxHealth(20);
    }

    private void apply(InfectedPlayer infectedPlayer) {
        int stage = infectedPlayer.stage;
        Player player = Bukkit.getPlayer(infectedPlayer.nickname);

        clearEffects(infectedPlayer);

        assert player != null;

        if (stage >= 1) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, -1, 0));
        }
        if (stage == 3) {
            player.setMaxHealth(14.0);
            player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, -1, 0));
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, -1, 0));
        }
    }

    public void apply(String nickname) {
        InfectedPlayer infectedPlayer = getPlayer(nickname);
        if (infectedPlayer == null) {
            return;
        }
        apply(infectedPlayer);
    }

    public void setStage(String nickname, int stage) {
        infectedPlayers.forEach(infectedPlayer -> {
            if (infectedPlayer.nickname.equals(nickname)) {
                infectedPlayer.stage = stage;
                infectedPlayer.duration = 0;
                apply(infectedPlayer);
            }
        });
    }

    private void nextStage(InfectedPlayer infectedPlayer) {
        if (infectedPlayer.stage >= 3) {
            return;
        }
        infectedPlayer.stage++;
        infectedPlayer.duration = 0;
    }

    private void updateDuration(InfectedPlayer infectedPlayer) {
        infectedPlayer.duration++;

        apply(infectedPlayer);
        if (infectedPlayer.duration >= durationToChangeStage) {
            nextStage(infectedPlayer);
        }
    }

    public void updateAllDuration() {
        for (InfectedPlayer infectedPlayer : infectedPlayers) {
            Player player = Bukkit.getPlayer(infectedPlayer.nickname);
            if (player != null) {
                if (player.hasPotionEffect(PotionEffectType.LUCK)) {
                    continue;
                }
                if (Math.random() <= sneezeChance) {
                    SoundsVisualEffects.sneeze(player);
                }
                updateDuration(infectedPlayer);
            }
        }
    }

    public int getStage(int index) {
        return infectedPlayers.get(index).stage;
    }

    public String getNickname(int index) {
        return infectedPlayers.get(index).nickname;
    }
    public int getStage(String nickname) {
        InfectedPlayer infectedPlayer = getPlayer(nickname);
        if (infectedPlayer == null) {
            return -1;
        }
        return infectedPlayer.stage;
    }

    public int getDuration(int index) {
        return infectedPlayers.get(index).duration;
    }

    public static final ListInfected INSTANCE = new ListInfected();

    private ListInfected() {}

    public static ListInfected getInstance() {
        return INSTANCE;
    }

    public static ListInfected instantiate() throws FileNotFoundException {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader("infectedPlayers.json"));
        ListInfected data = gson.fromJson(reader, ListInfected.class);

        INSTANCE.infectedPlayers = data.infectedPlayers;
        return INSTANCE;
    }

    public void save() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (Writer writer = new FileWriter("infectedPlayers.json")) {
            gson.toJson(this, writer);
        }
    }
}
