package com.baran.Locations;

import com.baran.Monsters.Zombie;
import com.baran.Player;


public class Cave extends BattleLoc {
    public Cave(Player player) {
        super(player, "com.baran.Locations.Cave", new Zombie(), "Food", 3);
    }
}
