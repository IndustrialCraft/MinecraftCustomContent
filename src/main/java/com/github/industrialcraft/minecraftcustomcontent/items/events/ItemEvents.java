package com.github.industrialcraft.minecraftcustomcontent.items.events;

import com.github.industrialcraft.minecraftcustomcontent.items.CustomItem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;

public class ItemEvents implements Listener {
    @EventHandler
    public void consumeItemEvent(PlayerItemConsumeEvent e){
        CustomItem citem = CustomItem.getCustomItem(e.getItem());
        if(citem != null){
            if(citem instanceof IConsumeItemEvent)
                ((IConsumeItemEvent)citem).onConsumeEvent(e);
        }
    }
    @EventHandler
    public void interactItemEvent(PlayerInteractEvent e){
        CustomItem citem = CustomItem.getCustomItem(e.getItem());
        if(citem != null){
            if(citem instanceof IInteractItemEvent)
                ((IInteractItemEvent) citem).onInteractEvent(e);
        }
    }
    @EventHandler
    public void interactEntityItemEvent(PlayerInteractEntityEvent e){
        CustomItem citem = CustomItem.getCustomItem(e.getPlayer().getItemInUse());
        if(citem != null){
            if(citem instanceof IInteractEntityItemEvent)
                ((IInteractEntityItemEvent)citem).onInteractEntityEvent(e);
        }
    }
}
