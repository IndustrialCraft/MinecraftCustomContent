package com.github.industrialcraft.minecraftcustomcontent.commands;

import com.github.industrialcraft.minecraftcustomcontent.CustomContentMain;
import com.github.industrialcraft.minecraftcustomcontent.items.CustomItem;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GiveCustomItemCommand implements CommandExecutor, TabCompleter {
    public GiveCustomItemCommand(JavaPlugin plugin) {
        PluginCommand cmd = plugin.getCommand("givecustom");
        cmd.setExecutor(this);
        cmd.setTabCompleter(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            if(args.length < 1 || args.length > 2){
                sender.sendMessage(ChatColor.RED + "usage: /givecustom <item> [count]");
                return true;
            }
            CustomItem customItem = CustomContentMain.getItemRegistry().get(args[0]);
            int count = 1;
            if(args.length > 1){
                try {
                    count = Integer.parseInt(args[1]);
                } catch (NumberFormatException e){
                    sender.sendMessage(ChatColor.RED + "invalid count");
                    return true;
                }
            }
            ((Player) sender).getInventory().addItem(customItem.create(count));
            return true;
        } else {
            sender.sendMessage(ChatColor.RED + "command must be executed as player");
            return true;
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(args.length == 1)
            return CustomContentMain.getItemRegistry().getAllCustomItems().stream().map(customItem -> customItem.getKey().toString()).filter(s -> s.contains(args[0])).collect(Collectors.toList());
        if(args.length == 2)
            return Arrays.asList("1", "16", "32", "64");
        return new ArrayList<>();
    }
}
