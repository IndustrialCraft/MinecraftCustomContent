package com.github.industrialcraft.minecraftcustomcontent.items.events;

import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public interface IInteractItemEvent {
    void onInteractEvent(PlayerInteractEvent event);
}
