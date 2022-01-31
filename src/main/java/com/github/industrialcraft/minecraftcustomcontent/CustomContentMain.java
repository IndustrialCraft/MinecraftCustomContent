package com.github.industrialcraft.minecraftcustomcontent;

import com.github.industrialcraft.minecraftcustomcontent.commands.GiveCustomItemCommand;
import com.github.industrialcraft.minecraftcustomcontent.commands.test.TestFlightCommand;
import com.github.industrialcraft.minecraftcustomcontent.flightManager.FlightManager;
import com.github.industrialcraft.minecraftcustomcontent.items.CraftingEvent;
import com.github.industrialcraft.minecraftcustomcontent.items.ItemRegistry;
import com.github.industrialcraft.minecraftcustomcontent.items.events.ItemEvents;
import com.github.industrialcraft.minecraftcustomcontent.items.test.TestBeerItem;
import com.github.industrialcraft.minecraftcustomcontent.items.test.TestLightningStick;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class CustomContentMain extends JavaPlugin {
    ItemRegistry itemRegistry;
    CCKeys keys;
    FlightManager flightManager;
    @Override
    public void onEnable() {
        keys = new CCKeys(this);

        FileConfiguration config = getConfig();
        config.addDefault(getKeys().CONFIG_CUSTOM_MODEL_DATAS, new ArrayList<String>());
        config.options().copyDefaults(true);
        itemRegistry = new ItemRegistry(config);

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new ItemEvents(), this);
        pm.registerEvents(new CraftingEvent(), this);

        flightManager = new FlightManager(this);
        pm.registerEvents(flightManager, this);

        //TEST
        new TestLightningStick(this);
        new TestBeerItem(this);

        new TestFlightCommand(this);
        //-TEST

        new GiveCustomItemCommand(this);
    }
    @Override
    public void onDisable() {
        itemRegistry.disable(getConfig());
        saveConfig();
    }

    public static FlightManager getFlightManager() {
        return getInstance().flightManager;
    }
    public static ItemRegistry getItemRegistry() {
        return getInstance().itemRegistry;
    }
    public static CCKeys getKeys() {
        return getInstance().keys;
    }
    public static CustomContentMain getInstance(){
        return JavaPlugin.getPlugin(CustomContentMain.class);
    }
}
