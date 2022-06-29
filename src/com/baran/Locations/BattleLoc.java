package com.baran.Locations;

import com.baran.Monsters.Obstacle;
import com.baran.Player;

import java.util.Random;

public abstract class BattleLoc extends Location {
    private Obstacle obstacle;
    private String award;
    private int maxObstacles;

    public int getMaxObstacles() {
        return maxObstacles;
    }

    public void setMaxObstacles(int maxObstacles) {
        this.maxObstacles = maxObstacles;
    }

    public BattleLoc(Player player, String name, Obstacle obstacle, String award, int maxObstacles) {
        super(player, name);
        this.obstacle = obstacle;
        this.award = award;
        this.maxObstacles = maxObstacles;
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
        int obsNumber = this.randomObstacle();
        System.out.println("You are in the " + getName());
        System.out.println("Be Careful!!You can see a " + obsNumber + " " + obstacle.getName());
        System.out.println("Do you want to fight or run?<F>Fight or <R>Run");
        String selectCase = Location.scanner.nextLine();
        selectCase = selectCase.toUpperCase();
        while (!selectCase.equals("F") && !selectCase.equals("R")) {
            System.out.println("Wrong !! Please enter your selection: ");
            selectCase = Location.scanner.nextLine();
            selectCase = selectCase.toUpperCase();
        }

        if (selectCase.equals("F")) {
            if (combat(obsNumber)) {
                System.out.println("You win");
                System.out.println("You get " + award);
                return true;
            } else {
                System.out.println("You lose");
                return false;
            }
        } else if (selectCase.equals("R")) {
            System.out.println("You ran away from the " + getName());
            return false;
        }
        return true;
    }

    public boolean combat(int obsNumber) {
        for (int i = 1; i <= obsNumber; i++) {
            this.getObstacle().setHealth(this.getObstacle().getOriginalHealth());
            playerStatus();
            obstacleStatus();
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
                        int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                        if (obstacleDamage < 0) {
                            obstacleDamage = 0;
                        }
                        this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                        afterHit();
                    } else {
                        System.out.println("The " + obstacle.getName() + " is dead");
                        this.setObstacle(null);
                        break;
                    }


                } else {
                    return false;
                }
            }
            if (this.getObstacle().getHealth() < this.getPlayer().getHealth()) {
                System.out.println("You win");
                System.out.println("You get " + award);
                this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getObstacle().getAward());
                System.out.println("Your last money " + this.getPlayer().getMoney());
                return true;
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
        System.out.println("You have " + this.getPlayer().getHealth() + " health");
        System.out.println("You have " + this.getPlayer().getTotalDamage() + " Damage");
        System.out.println("You have " + this.getPlayer().getMoney() + " Money");
        System.out.println("You have " + this.getPlayer().getWeapon().getName() + " Gun");
        System.out.println("You have " + this.getPlayer().getInventory().getArmor().getName() + " com.baran.Armor");
        System.out.println("You have " + this.getPlayer().getInventory().getArmor().getBlock() + " Blocking");

    }

    public void obstacleStatus() {
        System.out.println("The " + this.obstacle.getName() + " has " + this.obstacle.getHealth() + " health");
        System.out.println("The " + this.obstacle.getName() + " has " + this.obstacle.getDamage() + " Damage");
        System.out.println("The " + this.obstacle.getName() + " has " + this.obstacle.getAward() + " Award");
    }
}
