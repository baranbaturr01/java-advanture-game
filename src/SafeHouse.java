public class SafeHouse extends NormalLocation {
    public SafeHouse(Player player) {
        super(player, "SafeHouse");
    }

    @Override
    boolean onLocation() {
        System.out.println("You are in the SafeHouse");
        System.out.println("You healt is restored");
        return true;
    }
}
