package com.github.industrialcraft.minecraftcustomcontent;

import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

public class CCKeys {
    public final NamespacedKey CUSTOM_ITEM_ID;
    public final NamespacedKey CUSTOM_ITEM_CACHE;
    public final NamespacedKey CUSTOM_ITEM_DATA_CONTAINER;

    public final String CONFIG_CUSTOM_MODEL_DATAS = "custommodeldatas";

    public CCKeys(JavaPlugin plugin) {
        CUSTOM_ITEM_ID = new NamespacedKey(plugin, "custom_item_id");
        CUSTOM_ITEM_CACHE = new NamespacedKey(plugin, "custom_item_cache");
        CUSTOM_ITEM_DATA_CONTAINER = new NamespacedKey(plugin, "custom_item_data_container");
    }
}
