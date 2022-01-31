package com.github.industrialcraft.minecraftcustomcontent.flightManager;

public class CooldownFlightModifier extends FlightModifier{
    int cooldown;
    public CooldownFlightModifier(int cooldown) {
        this.cooldown = cooldown;
    }

    @Override
    boolean tick() {
        cooldown--;
        return cooldown <= 0;
    }
}
