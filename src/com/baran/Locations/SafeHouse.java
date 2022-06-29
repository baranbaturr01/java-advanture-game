package com.baran.Locations;

import com.baran.Player;

public class SafeHouse extends NormalLocation {
    public SafeHouse(Player player) {
        super(player, "SafeHouse");
    }

    @Override
    public boolean onLocation() {
        System.out.println("You are in the SafeHouse");
        System.out.println("You health is restored");
        this.getPlayer().setHealth(this.getPlayer().getOriginalHealth());
        return true;
    }
}
