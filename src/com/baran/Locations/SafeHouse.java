package com.baran.Locations;

import com.baran.Player;

public class SafeHouse extends NormalLocation {
    public SafeHouse(Player player) {
        super(player, "com.baran.Locations.SafeHouse");
    }

    @Override
    public boolean onLocation() {
        System.out.println("You are in the com.baran.Locations.SafeHouse");
        System.out.println("You healt is restored");
        return true;
    }
}
