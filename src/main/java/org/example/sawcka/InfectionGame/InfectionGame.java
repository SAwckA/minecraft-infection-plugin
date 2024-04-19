package org.example.sawcka.InfectionGame;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.example.sawcka.InfectionGame.commands.*;
import org.example.sawcka.InfectionGame.events.*;

import java.io.FileNotFoundException;
import java.io.IOException;


public final class InfectionGame extends JavaPlugin {

    private ListInfected listInfected;

    @Override
    public void onEnable() {

        Bukkit.getLogger().info("Starting");

        getCommand("test").setExecutor(new TestCommand());

        GetDrugCMD getDrugCMD = new GetDrugCMD();
        getCommand("drug").setExecutor(getDrugCMD);
        getCommand("drug").setTabCompleter(getDrugCMD);

        SpawnHealerCMD spawnHealerCMD = new SpawnHealerCMD();
        getCommand("healer").setExecutor(spawnHealerCMD);
        getCommand("healer").setTabCompleter(spawnHealerCMD);

        InfectCMD infectCMD = new InfectCMD();
        getCommand("infect").setExecutor(infectCMD);
        getCommand("infect").setTabCompleter(infectCMD);

        ListInfectedCMD listInfectedCMD = new ListInfectedCMD();
        getCommand("list_infected").setExecutor(listInfectedCMD);
        getCommand("list_infected").setTabCompleter(listInfectedCMD);

        SetStageCMD setStageCMD = new SetStageCMD();
        getCommand("set_stage").setExecutor(setStageCMD);
        getCommand("set_stage").setTabCompleter(setStageCMD);

        RemoveInfectionCMD removeInfectionCMD = new RemoveInfectionCMD();
        getCommand("remove_infection").setExecutor(removeInfectionCMD);
        getCommand("remove_infection").setTabCompleter(removeInfectionCMD);

        SpawnWaveCMD spawnWaveCMD = new SpawnWaveCMD();
        getCommand("spawn_wave").setExecutor(spawnWaveCMD);
        getCommand("spawn_wave").setTabCompleter(spawnWaveCMD);

        ToggleInfiniteWeatherCMD toggleInfiniteWeatherCMD = new ToggleInfiniteWeatherCMD();
        getCommand("toggle_infinite_weather").setExecutor(toggleInfiniteWeatherCMD);
        getCommand("toggle_infinite_weather").setTabCompleter(toggleInfiniteWeatherCMD);

        PortalBlackout portalBlackout = new PortalBlackout();
        getCommand("toggle_portal_blackout").setExecutor(portalBlackout);
        getCommand("toggle_portal_blackout").setTabCompleter(portalBlackout);

        ToggleExplodeCreeper toggleExplodeCreeper = new ToggleExplodeCreeper();
        getCommand("toggle_explode_creeper").setExecutor(toggleExplodeCreeper);
        getCommand("toggle_explode_creeper").setTabCompleter(toggleExplodeCreeper);

        WaveDelayedCMD waveDelayedCMD = new WaveDelayedCMD();
        getCommand("wave_delayed").setExecutor(waveDelayedCMD);
        getCommand("wave_delayed").setTabCompleter(waveDelayedCMD);

        ToggleHealerAutoSpawnCMD toggleHealerAutoSpawnCMD = new ToggleHealerAutoSpawnCMD();
        getCommand("toggle_healer_auto_spawn").setExecutor(toggleHealerAutoSpawnCMD);
        getCommand("toggle_healer_auto_spawn").setTabCompleter(toggleHealerAutoSpawnCMD);

        SetInfectionDurationCMD setInfectionDurationCMD = new SetInfectionDurationCMD();
        getCommand("set_infection_duration").setExecutor(setInfectionDurationCMD);
        getCommand("set_infection_duration").setTabCompleter(setInfectionDurationCMD);

        try {
            listInfected = ListInfected.instantiate();
        } catch (FileNotFoundException e) {
            Bukkit.getLogger().info("Cant find previous infected list file [infectedList.json]");
            listInfected = ListInfected.getInstance();
        }

        Bukkit.getPluginManager().registerEvents(new ConsumingEvent(this), this);
        Bukkit.getPluginManager().registerEvents(new RespawnEvent(this), this);
        Bukkit.getPluginManager().registerEvents(new ToggleFlyEvent(), this);
        Bukkit.getPluginManager().registerEvents(portalBlackout, this);
        Bukkit.getPluginManager().registerEvents(toggleExplodeCreeper, this);
        Bukkit.getPluginManager().registerEvents(new HealerDespawnEvent(), this);
        Bukkit.getPluginManager().registerEvents(new HealerAcquireEvent(), this);
        Bukkit.getLogger().info("Enabled");

        Server server = Bukkit.getServer();
        int delay = 0;
        int period = 50;

        Infection infection = new Infection();

        BukkitTask task = server.getScheduler().runTaskTimer(this, infection::update, delay, period);
        BukkitTask updateDurationTask = server.getScheduler().runTaskTimer(this, listInfected::updateAllDuration, delay, 20);
        BukkitTask spawnHealerOnSpawn = server.getScheduler().runTaskTimer(this, Healer::spawnTemporaryVillager, delay, 20 * 60 * 20);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("Stopping");

        try {
            listInfected.save();
        } catch (IOException e) {
            Bukkit.getLogger().info(e.getMessage());
        }
    }
}
