package com.baran.Locations;

import com.baran.Monsters.Bear;
import com.baran.Player;

public class River extends BattleLoc {
    public River(Player player) {
        super(player, "com.baran.Locations.River", new Bear(), "Water",2);
    }
}
