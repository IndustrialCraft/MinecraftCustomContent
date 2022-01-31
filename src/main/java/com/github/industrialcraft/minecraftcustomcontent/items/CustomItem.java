package com.github.industrialcraft.minecraftcustomcontent.items;

import com.github.industrialcraft.minecraftcustomcontent.CustomContentMain;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;

public abstract class CustomItem {
    private final NamespacedKey itemKey;
    private final int customModelData;
    private boolean valid;
    public CustomItem(NamespacedKey itemKey) {
        this.itemKey = itemKey;
        this.valid = true;
        this.customModelData = CustomContentMain.getInstance().getItemRegistry().register(this);
    }
    protected ItemStack _create(int amount, Material mat, String name, String... lore){
        ItemStack itemStack = new ItemStack(mat, amount);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(ChatColor.RESET + name);
        meta.setLore(Arrays.asList(lore));
        meta.setCustomModelData(customModelData);

        PersistentDataContainer container = meta.getPersistentDataContainer();
        container.set(CustomContentMain.getKeys().CUSTOM_ITEM_ID, PersistentDataType.STRING, getKey().toString());

        itemStack.setItemMeta(meta);

        tryPutCache(itemStack, this);

        return itemStack;
    }
    public abstract ItemStack create(int amount);
    public ItemStack create(){
        return create(1);
    }

    public boolean check(ItemStack stack){
        return getCustomItem(stack) == this;
    }

    public static boolean isCustom(ItemStack itemStack){
        ItemMeta meta = itemStack.getItemMeta();
        if(meta == null)
            return false;
        PersistentDataContainer container = meta.getPersistentDataContainer();

        return container.has(CustomContentMain.getKeys().CUSTOM_ITEM_ID, PersistentDataType.STRING);
    }

    public static CustomItem getCustomItem(ItemStack itemStack){
        if(itemStack == null)
            return null;
        CustomItem cacheCustomItem = tryGetCache(itemStack);
        if(cacheCustomItem != null && cacheCustomItem.isValid())
            return cacheCustomItem;

        ItemMeta meta = itemStack.getItemMeta();
        if(meta == null)
            return null;
        PersistentDataContainer container = meta.getPersistentDataContainer();

        String id = container.get(CustomContentMain.getKeys().CUSTOM_ITEM_ID, PersistentDataType.STRING);
        CustomItem customItem = CustomContentMain.getItemRegistry().get(id);
        if(customItem != null) {
            tryPutCache(itemStack, customItem);
            reapplyCustomModelData(itemStack, customItem.getCustomModelData());
        }
        return customItem;
    }

    private static void reapplyCustomModelData(ItemStack is, int customModelData){
        ItemMeta meta = is.getItemMeta();
        meta.setCustomModelData(customModelData);
        is.setItemMeta(meta);
    }

    private static CustomItem tryGetCache(ItemStack is){
        return null;
    }
    private static void tryPutCache(ItemStack is, CustomItem customItem){

    }

    public NamespacedKey getKey() {
        return itemKey;
    }
    public int getCustomModelData() {
        return customModelData;
    }
    public void invalidate(){
        this.valid = false;
    }
    public boolean isValid() {
        return valid;
    }
}
