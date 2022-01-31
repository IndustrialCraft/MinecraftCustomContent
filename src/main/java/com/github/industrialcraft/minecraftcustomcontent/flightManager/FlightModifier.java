package com.github.industrialcraft.minecraftcustomcontent.flightManager;

import org.bukkit.entity.Player;

public abstract class FlightModifier {
    private Player pl;
    private boolean isInvalid;
    void setPlayer(Player pl){
        this.pl = pl;
    }
    void invalidate(){
        this.isInvalid = true;
    }
    boolean isInvalid(){
        return this.isInvalid;
    }
    abstract boolean tick();
}
