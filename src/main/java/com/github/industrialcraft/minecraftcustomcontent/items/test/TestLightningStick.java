package com.github.industrialcraft.minecraftcustomcontent.items.test;

import com.github.industrialcraft.minecraftcustomcontent.items.CustomItem;
import com.github.industrialcraft.minecraftcustomcontent.items.events.IInteractItemEvent;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class TestLightningStick extends CustomItem implements IInteractItemEvent {
    public TestLightningStick(JavaPlugin plugin) {
        super(new NamespacedKey(plugin, "test_lightning_stick"));
    }

    @Override
    public ItemStack create(int amount) {
        return _create(amount, Material.STICK, "Lightning rod", "strikes lightning bolt on click");
    }

    @Override
    public void onInteractEvent(PlayerInteractEvent event) {
        if(event.getAction() != Action.RIGHT_CLICK_BLOCK)
            return;
        Location loc = event.getClickedBlock().getRelative(event.getBlockFace()).getLocation();
        loc.getWorld().spawnEntity(loc, EntityType.LIGHTNING);
    }
}
