import Characters.Archer;
import Characters.GameChar;
import Characters.Knight;
import Characters.Samurai;

import java.util.Scanner;

public class Player {
    private int damage;
    private int health;
    private int money;
    private String playerName;
    private String charName;
    private Inventory inventory;

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    final Scanner scanner = new Scanner(System.in);


    public Player(String playerName) {

        this.playerName = playerName;
        this.inventory = new Inventory();
    }

    public void selectChar() {

        System.out.println("Characters");

        GameChar[] gameChar = {new Samurai(), new Knight(), new Archer()};
        for (GameChar gc : gameChar) {
            System.out.println(gc.getId() + ". " + gc.getName() + "\t" + "Damage: " + gc.getDamage() + "\t" + "Health: " + gc.getHealth() + "\t" + "Money: " + gc.getMoney());
        }
        System.out.println("-----------------------------------------------------");
        System.out.println("Enter your character number: ");
        int charNum = scanner.nextInt();

        switch (charNum) {
            case 1 -> initPlayer(new Samurai());
            case 2 -> initPlayer(new Knight());
            case 3 -> initPlayer(new Archer());
            default -> {
                System.out.println("Invalid character number");
                selectChar();
            }
        }
//        System.out.println("Character: " + this.getCharName() + ", Damage: " + this.getDamage() + ", Health: " + this.getHealth() + ", Money: " + this.getMoney());

    }

    public void printPlayerInfo() {
        System.out.println("Character: " + this.getCharName() + ", Damage: " + "Gun : " + this.getInventory().getWeapon().getName() + "\t" + "Armor : " + this.getInventory().getArmor().getName() + "\t" + "Money: " + this.getMoney());
    }

    public void selectLocation() {
    }

    public void initPlayer(GameChar gameChar) {
        this.setDamage(gameChar.getDamage());
        this.setHealth(gameChar.getHealth());
        this.setMoney(gameChar.getMoney());
        this.setCharName(gameChar.getName());
    }

    public int getTotalDamage() {
        return this.getDamage() + this.getInventory().getWeapon().getDamage();
    }

    public int getDamage() {
        return this.damage;
    }


    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public Weapon getWeapon() {
        if (this.inventory.getWeapon().getDamage() > 0) {
            return this.inventory.getWeapon();
        }
        return null;
    }
}
