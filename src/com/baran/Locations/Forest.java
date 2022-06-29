package com.baran.Locations;

import com.baran.Item;
import com.baran.Monsters.Vampire;
import com.baran.Player;

public class Forest extends BattleLoc {
    public Forest(Player player) {
        super(player, "Forest", new Vampire(), new Item("Firewood"), 3);
    }
}
