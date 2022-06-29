package com.baran.Locations;

import com.baran.Item;
import com.baran.Monsters.Zombie;
import com.baran.Player;


public class Cave extends BattleLoc {
    public Cave(Player player) {
        super(player, "Cave", new Zombie(), new Item("Food"), 3);
    }
}
