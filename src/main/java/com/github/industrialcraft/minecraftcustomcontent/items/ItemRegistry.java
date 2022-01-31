package com.github.industrialcraft.minecraftcustomcontent.items;

import com.github.industrialcraft.minecraftcustomcontent.AlreadyRegisteredException;
import com.github.industrialcraft.minecraftcustomcontent.CustomContentMain;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class ItemRegistry {
    private HashMap<String,CustomItem> customItems;
    private HashMap<Integer,String> cmd2key;
    public ItemRegistry(FileConfiguration config) {
        this.customItems = new HashMap<>();
        this.cmd2key = new HashMap<>();

        for(String cfg : config.getStringList(CustomContentMain.getKeys().CONFIG_CUSTOM_MODEL_DATAS)){
            String splitted[] = cfg.split(Pattern.quote("-"));
            if(splitted.length != 2)
                continue;
            if(this.cmd2key.containsKey(Integer.parseInt(splitted[0]))) {
                CustomContentMain.getInstance().getLogger().warning("duplicate custommodeldata, " + splitted[1] + " already registered with " + cmd2key.get(Integer.parseInt(splitted[0])));
                continue;
            }
            this.cmd2key.put(Integer.parseInt(splitted[0]), splitted[1]);
        }
    }
    public int register(CustomItem item) throws AlreadyRegisteredException {
        if(customItems.containsKey(item.getKey().toString()))
            throw new AlreadyRegisteredException(item.getKey());
        customItems.put(item.getKey().toString(), item);

        return getNextFreeID(item.getKey().toString());
    }

    public int getNextFreeID(String key){
        for(Map.Entry<Integer,String> en : cmd2key.entrySet()){
            if(en.getValue().equals(key))
                return en.getKey();
        }

        int id = 0;
        while(cmd2key.containsKey(id))
            id++;

        cmd2key.put(id, key);
        return id;
    }

    public void disable(FileConfiguration config){
        for(CustomItem citem : customItems.values())
            citem.invalidate();
        ArrayList<String> cfg = new ArrayList<>();
        for(Map.Entry<Integer,String> en : cmd2key.entrySet()){
            cfg.add(en.getKey() + "-" + en.getValue());
        }
        config.set(CustomContentMain.getKeys().CONFIG_CUSTOM_MODEL_DATAS, cfg);
    }

    public Collection<CustomItem> getAllCustomItems(){
        return customItems.values();
    }

    public CustomItem get(NamespacedKey key){
        return customItems.get(key.toString());
    }
    public CustomItem get(String key){
        return customItems.get(key);
    }
}
