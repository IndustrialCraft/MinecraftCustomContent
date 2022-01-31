package com.github.industrialcraft.minecraftcustomcontent.items.test;

import com.github.industrialcraft.minecraftcustomcontent.items.CustomItem;
import com.github.industrialcraft.minecraftcustomcontent.items.events.IConsumeItemEvent;
import com.github.industrialcraft.minecraftcustomcontent.items.events.IInteractItemEvent;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class TestBeerItem extends CustomItem implements IConsumeItemEvent {
    public TestBeerItem(JavaPlugin plugin) {
        super(new NamespacedKey(plugin, "beer"));
    }

    @Override
    public ItemStack create(int amount) {
        return _create(amount, Material.HONEY_BOTTLE, "Beer", "you get drunk");
    }

    @Override
    public void onConsumeEvent(PlayerItemConsumeEvent event) {
        event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 100, 0));
    }
}
