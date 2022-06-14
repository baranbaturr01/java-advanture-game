import java.util.Scanner;

public class Game {
    Scanner scanner = new Scanner(System.in);

    public void start() {
        System.out.println("Game started,Welcome to the game");
        System.out.println("Please enter your name");
        String playerName = scanner.nextLine();
        Player player = new Player(playerName);
        System.out.println(player.getPlayerName() + " Welcome to the game");
        System.out.println("Please select your character");
        player.selectChar();
        Location location = null;

        while (true) {
            System.out.println();
            System.out.println("########## Locations ##########");
            System.out.println();
            System.out.println("1- SafeHouse-----> This is a safe house where you can rest and recover your health");
            System.out.println("2- ToolStore ------->You can buy gun and tools");
            System.out.println("Please enter your location number: ");

            int selectLoc = scanner.nextInt();
            switch (selectLoc) {
                case 1 -> location = new SafeHouse(player);
                case 2 -> location = new ToolStore(player);
                default -> {
                    location = new SafeHouse(player);
                }
            }
            if (!location.onLocation()) {
                System.out.println("Game Over");
                break;
            }

        }
    }
}
