package com.baran.Locations;
import com.baran.Weapon;
import com.baran.Armor;
import com.baran.Player;
public class ToolStore extends NormalLocation {
    public ToolStore(Player player) {
        super(player, "com.baran.Locations.ToolStore");
    }

    @Override
   public boolean onLocation() {
        System.out.println("You are in the com.baran.Locations.ToolStore");
        System.out.println("What do you want to buy?");
        boolean flag = true;
        while (flag) {
            System.out.println("1- Gun");
            System.out.println("2- Tools");
            System.out.println("3- Exit");

            System.out.println("Please enter your selection: ");
            int select = Location.scanner.nextInt();
            while (select < 1 || select > 3) {
                System.out.println("Wrong !! Please enter your selection: ");
                select = Location.scanner.nextInt();
            }
            switch (select) {
                case 1 -> {
                    printWeapon();
                    buyWeapon();
                    break;
                }
                case 2 -> {
                    printArmor();
                    buyArmor();
                    break;
                }
                case 3 -> {
                    System.out.println("You exit the com.baran.Locations.ToolStore");
                    flag = false;
                    break;
                }
            }
        }

        return true;
    }

    public void printArmor() {
        System.out.println("------------ARMORS------------");
        for (Armor a : Armor.armors()) {
            System.out.println(a.getId() + " - " + a.getName() + " <Para : " + a.getPrice() + " com.baran.Armor : " + a.getBlock() + ">");
        }

    }

    public void printWeapon() {
        System.out.println("------------GUNS------------");
        for (Weapon w : Weapon.weapons()) {
            System.out.println(w.getId() + " - " + w.getName() + " <Para : " + w.getPrice() + " Hasar : " + w.getDamage() + ">");
        }
    }

    public void buyWeapon() {

        System.out.println("Please enter your gun selection: ");
        int selectedWeaponId = Location.scanner.nextInt();

        while (selectedWeaponId < 1 || selectedWeaponId > Weapon.weapons().length) {
            System.out.println("Wrong !! Please enter your selection: ");
            selectedWeaponId = Location.scanner.nextInt();
        }
        Weapon selectedWeapon = Weapon.getWeaponObjectById(selectedWeaponId);

        if (selectedWeapon != null) {

            if (this.getPlayer().getMoney() >= selectedWeapon.getPrice()) {

                this.getPlayer().setMoney(this.getPlayer().getMoney() - selectedWeapon.getPrice());
                System.out.println("You have bought " + selectedWeapon.getName());
                System.out.println("Kalan Paranız: " + this.getPlayer().getMoney());
                System.out.println("Önceki Silahınız " + this.getPlayer().getInventory().getWeapon().getName());
                this.getPlayer().getInventory().setWeapon(selectedWeapon);
                System.out.println("Şuan ki Silahınız " + this.getPlayer().getInventory().getWeapon().getName());

            } else {
                System.out.println("You have not enough money!!");
            }
        }
    }

    public void buyArmor() {
        System.out.println("Please enter your com.baran.Armor selection: ");
        int selectedArmorId = Location.scanner.nextInt();

        while (selectedArmorId < 1 || selectedArmorId > Armor.armors().length) {
            System.out.println("Wrong !! Please enter your selection: ");
            selectedArmorId = Location.scanner.nextInt();
        }

        Armor selectedArmor = Armor.getArmorObjectById(selectedArmorId);

        if (selectedArmor != null) {
            if (this.getPlayer().getMoney() >= selectedArmor.getPrice()) {
                this.getPlayer().setMoney(this.getPlayer().getMoney() - selectedArmor.getPrice());
                System.out.println("You have bought " + selectedArmor.getName());
                System.out.println("Kalan Paranız: " + this.getPlayer().getMoney());
                System.out.println("Önceki Armorınız " + this.getPlayer().getInventory().getArmor().getName());
                this.getPlayer().getInventory().setArmor(selectedArmor);
                System.out.println("Şuan ki Armorınız " + this.getPlayer().getInventory().getArmor().getName());
            } else {
                System.out.println("You have not enough money!!");
            }
        }
    }
}
