package com.baran.Locations;

import com.baran.Item;
import com.baran.Monsters.Obstacle;
import com.baran.Player;

import java.util.Random;

public abstract class BattleLoc extends Location {
    private Obstacle obstacle;
    private String award;
    private int maxObstacles;

    private Item item;

    public int getMaxObstacles() {
        return maxObstacles;
    }

    public void setMaxObstacles(int maxObstacles) {
        this.maxObstacles = maxObstacles;
    }

    public BattleLoc(Player player, String name, Obstacle obstacle, Item item, int maxObstacles) {
        super(player, name);
        this.obstacle = obstacle;
        this.setItem(item);
        this.maxObstacles = maxObstacles;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public int randomObstacle() {
        Random r = new Random();
        return r.nextInt(this.getMaxObstacles()) + 1;
    }

    @Override
    public boolean onLocation() {
        int obsNumber = this.randomObstacle();//Canavar sayısı her seferinde random üretilsin ufak bir heyecan için eklendi:)

        System.out.println("You are in the " + getName());
        System.out.println("Be Careful!!You can see a " + obsNumber + " " + obstacle.getName());
        System.out.println("Do you want to fight or run? <F> Fight or <R> Run");

        String selectCase = Location.scanner.nextLine();
        selectCase = selectCase.toUpperCase();

        //Yanlış seçimde tekrar sormak için kullanıldı
        while (!selectCase.equals("F") && !selectCase.equals("R")) {
            System.out.println("Wrong !! Please enter your selection: ");
            selectCase = Location.scanner.nextLine();
            selectCase = selectCase.toUpperCase();
        }

        if (selectCase.equals("F")) {

            if (combat(obsNumber)) {

                switch (this.getName()) {

                    case "Cave", "River", "Forest" -> {
                        System.out.println("You won the battle and got a " + this.getItem().getName() + " and " + this.getAward() + "It is added inventory"); //Award is the reward of the battle
                        this.getPlayer().getInventory().addItem(this.getItem());
                        return true;
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + this.getName());
                }
            }
        }
        if (this.getPlayer().getHealth() <= 0) {
            System.out.println("You lose");
            return false;
        }
        return true;
    }

    public boolean combat(int obsNumber) {
        for (int i = 1; i <= obsNumber; i++) {
            this.getObstacle().setHealth(this.getObstacle().getOriginalHealth());
            playerStatus();
            obstacleStatus(i);

            double firstKick = Math.random();
            if (firstKick < 0.5) {
                System.out.println("You kicked the " + obstacle.getName() + " and it lost " + this.getPlayer().getDamage() + " health");

                while (this.getPlayer().getHealth() > 0 && obstacle.getHealth() > 0) {
                    System.out.println("Do you want to attack or defend?<A>Attack or <R>Run");
                    String selectCombat = Location.scanner.nextLine();
                    selectCombat = selectCombat.toUpperCase();

                    while (!selectCombat.equals("A") && !selectCombat.equals("D")) {
                        System.out.println("Wrong !! Please enter your selection: ");
                        selectCombat = Location.scanner.nextLine();
                        selectCombat = selectCombat.toUpperCase();
                    }

                    if (selectCombat.equals("A")) {
                        System.out.println("You attacked the " + obstacle.getName());
                        this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getDamage());
                        afterHit();
                        if (this.getObstacle().getHealth() > 0) {
                            System.out.println("The " + obstacle.getName() + " attacked you");
                            int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                            if (obstacleDamage < 0) {
                                obstacleDamage = 0;
                            }

                            this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                            afterHit();
                        }

                    } else {
                        return false;
                    }
                }

            } else {

                System.out.println("First monster kicked");

                while (this.getPlayer().getHealth() > 0 && obstacle.getHealth() > 0) {
                    System.out.println();
                    System.out.println("The monster kicked you and you lost " + this.getObstacle().getDamage() + " health");
                    int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                    if (obstacleDamage < 0) {
                        obstacleDamage = 0;
                    }
                    this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                    afterHit();
                    if (this.getPlayer().getHealth() > 0) {
                        boolean flag = true;
                        while (flag) {
                            System.out.println("Do you want to attack or defend?<A>Attack or <R>Run");
                            String selectCombat = Location.scanner.nextLine();
                            selectCombat = selectCombat.toUpperCase();
                            while (!selectCombat.equals("A") && !selectCombat.equals("D")) {
                                System.out.println("Wrong !! Please enter your selection: ");
                                selectCombat = Location.scanner.nextLine();
                                selectCombat = selectCombat.toUpperCase();
                            }
                            if (selectCombat.equals("A")) {
                                System.out.println("You attacked the " + obstacle.getName());
                                this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getDamage());
                                afterHit();
                                flag = false;
                            } else {
                                return false;
                            }
                        }
                    }
                }
            }
            if (this.getObstacle().getHealth() < this.getPlayer().getHealth()) {
                System.out.println("You win");
                System.out.println("You get " + this.getObstacle().getAward());
                this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getObstacle().getAward());
                System.out.println("Your last money " + this.getPlayer().getMoney());
            } else {
                System.out.println("You lose");
                return false;
            }
        }
        return true;
    }

    public void afterHit() {
        System.out.println("Your health is " + this.getPlayer().getHealth());
        System.out.println("The " + obstacle.getName() + " health is " + obstacle.getHealth());
        System.out.println();
    }

    public void playerStatus() {
        System.out.println("Your " + " health : " + this.getPlayer().getHealth() + " Damage : " + this.getPlayer().getTotalDamage() + " Money : " + this.getPlayer().getMoney() + " Gun : " + this.getPlayer().getWeapon().getName() + " Armor : " + this.getPlayer().getInventory().getArmor().getName() + " Blocking : " + this.getPlayer().getInventory().getArmor().getBlock());
    }

    public void obstacleStatus(int i) {
        System.out.println(i + ": " + "The " + this.obstacle.getName() + " Health : " + this.obstacle.getHealth() + ", Damage : " + this.obstacle.getDamage() + ", Award : " + this.obstacle.getAward());
    }
}
