package com.github.industrialcraft.minecraftcustomcontent.items.events;

import org.bukkit.event.player.PlayerItemConsumeEvent;

public interface IConsumeItemEvent {
    void onConsumeEvent(PlayerItemConsumeEvent event);
}
