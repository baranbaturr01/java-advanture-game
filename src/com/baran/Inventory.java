package com.baran;

import java.util.ArrayList;

public class Inventory {
    private Weapon weapon;
    private Armor armor;

    ArrayList<Item> itemList;

    public Inventory() {
        this.weapon = new Weapon("Yumruk", 0, 0, 0);
        this.armor = new Armor(0, "Paçavra", 0, 0);
        this.itemList = new ArrayList<Item>();
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }


    public void addItem(Item item) {
        itemList.add(item);
    }

    public ArrayList<Item> getItemList() {
        return itemList;
    }
}
