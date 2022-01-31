package com.github.industrialcraft.minecraftcustomcontent.commands.test;

import com.github.industrialcraft.minecraftcustomcontent.CustomContentMain;
import com.github.industrialcraft.minecraftcustomcontent.flightManager.CooldownFlightModifier;
import com.github.industrialcraft.minecraftcustomcontent.items.CustomItem;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestFlightCommand implements CommandExecutor {
    public TestFlightCommand(JavaPlugin plugin) {
        PluginCommand cmd = plugin.getCommand("testflight");
        cmd.setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            CustomContentMain.getFlightManager().get((Player) sender).add(new CooldownFlightModifier(5));
            return true;
        } else {
            sender.sendMessage(ChatColor.RED + "command must be executed as player");
            return true;
        }
    }
}
