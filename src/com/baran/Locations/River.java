package com.baran.Locations;

import com.baran.Item;
import com.baran.Monsters.Bear;
import com.baran.Player;

public class River extends BattleLoc {
    public River(Player player) {
        super(player, "River", new Bear(), new Item("Water"), 2);
    }
}
