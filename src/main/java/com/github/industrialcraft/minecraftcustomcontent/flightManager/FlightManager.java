package com.github.industrialcraft.minecraftcustomcontent.flightManager;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FlightManager implements Listener {
    HashMap<UUID,FlightStorage> flightDataStorage;
    public FlightManager(JavaPlugin plugin) {
        this.flightDataStorage = new HashMap<>();

        for(Player pl : plugin.getServer().getOnlinePlayers()){
            flightDataStorage.put(pl.getUniqueId(), new FlightStorage(pl));
        }

        new BukkitRunnable(){
            @Override
            public void run() {
                for(Map.Entry<UUID,FlightStorage> entry : flightDataStorage.entrySet()){
                    entry.getValue().tick();
                }
            }
        }.runTaskTimer(plugin, 0, 20);
    }
    public FlightStorage get(Player pl){
        return flightDataStorage.get(pl.getUniqueId());
    }
    @EventHandler
    public void playerJoinEvent(PlayerJoinEvent e){
        flightDataStorage.put(e.getPlayer().getUniqueId(), new FlightStorage(e.getPlayer()));
    }
    @EventHandler
    public void playerQuitEvent(PlayerQuitEvent e){
        flightDataStorage.remove(e.getPlayer().getUniqueId());
    }
    public static class FlightStorage{
        private ArrayList<FlightModifier> modifiers;
        private Player pl;
        private boolean flyingBefore;
        public FlightStorage(Player pl) {
            this.modifiers = new ArrayList<>();
            this.pl = pl;
            this.flyingBefore = false;
        }
        public void add(FlightModifier modifier){
            modifier.setPlayer(pl);
            modifiers.add(modifier);
        }
        void tick(){
            modifiers.removeIf(flightModifier -> flightModifier.tick());
            modifiers.removeIf(flightModifier -> flightModifier.isInvalid());

            boolean allowFlight = (modifiers.size() > 0);
            pl.setAllowFlight(allowFlight);
            if(allowFlight && !flyingBefore)
                pl.setFlying(true);
            if(!allowFlight)
                pl.setFlying(false);
            this.flyingBefore = allowFlight;
        }
    }
}
