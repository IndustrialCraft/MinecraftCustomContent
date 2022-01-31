package com.github.industrialcraft.minecraftcustomcontent.items.events;

import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public interface IInteractEntityItemEvent {
    void onInteractEntityEvent(PlayerInteractEntityEvent event);
}
