package com.github.industrialcraft.minecraftcustomcontent;

import com.github.industrialcraft.minecraftcustomcontent.items.CustomItem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

public class CraftingEvent implements Listener {
    @EventHandler
    public void onCraft(PrepareItemCraftEvent e){
        for(ItemStack is : e.getInventory().getMatrix()){
            if(is == null)
                continue;
            if(CustomItem.isCustom(is)){
                e.getInventory().setResult(null);
                return;
            }
        }
    }
}
