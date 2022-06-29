package com.baran;

import com.baran.Locations.*;

import java.util.Scanner;

import com.baran.Player;


public class Game {
    Scanner scanner = new Scanner(System.in);

    public void start() {
        System.out.println("com.baran.Game started,Welcome to the game");
        System.out.println("Please enter your name");
        String playerName = scanner.nextLine();
        Player player = new Player(playerName);
        System.out.println(player.getPlayerName() + " Welcome to the game");
        System.out.println("Please select your character");
        player.selectChar();
        Location location = null;

        while (true) {

            player.printPlayerInfo();
            System.out.println();
            System.out.println("########## com.baran.Locations ##########");
            System.out.println();
            System.out.println("1- com.baran.Locations.SafeHouse----->      This is a safe house where you can rest and recover your health");
            System.out.println("2- com.baran.Locations.ToolStore ------->   You can buy gun and tools");
            System.out.println("3- com.baran.Locations.Cave ------->   You can enter the com.baran.Locations.Cave and fight with the monster");
            System.out.println("4- com.baran.Locations.Forest ------->   You can enter the com.baran.Locations.Forest and fight with the monster");
            System.out.println("5- com.baran.Locations.River ------->   You can enter the com.baran.Locations.River and fight with the monster");
            System.out.println("0- Exit ------->        You can close the game");

            System.out.println("Please enter your location number: ");

            int selectLoc = scanner.nextInt();
            switch (selectLoc) {
                case 0 -> {
                    location = null;
                    break;
                }
                case 1 -> location = new SafeHouse(player);
                case 2 -> location = new ToolStore(player);
                case 3 -> location = new Cave(player);
                case 4 -> location = new Forest(player);
                case 5 -> location = new River(player);

                default -> {
                    location = new SafeHouse(player);
                }
            }
            if (location == null) {
                break;
            }
            if (!location.onLocation()) {
                System.out.println("com.baran.Game Over");
                break;
            }

        }
    }
}
