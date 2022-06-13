import java.util.Scanner;

public class Game {
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        System.out.println("Game started,Welcome to the game");
        System.out.println("Please enter your name");
        String playerName = scanner.nextLine();
        Player player = new Player(playerName);
        System.out.println(player.getPlayerName() + " Welcome to the game");
        System.out.println("Please select your character");
        player.selectChar();
    }
}
