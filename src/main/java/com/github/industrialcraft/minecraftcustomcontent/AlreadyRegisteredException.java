package com.github.industrialcraft.minecraftcustomcontent;

import org.bukkit.NamespacedKey;

public class AlreadyRegisteredException extends RuntimeException{
    public AlreadyRegisteredException(NamespacedKey key) {
        super(key.toString() + " is already registered");
    }
}
