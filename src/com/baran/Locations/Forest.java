package com.baran.Locations;

import com.baran.Monsters.Vampire;
import com.baran.Player;

public class Forest extends BattleLoc {
    public Forest(Player player) {
        super(player, "com.baran.Locations.Forest", new Vampire(), "FireWood",3);
    }
}
