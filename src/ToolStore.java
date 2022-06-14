public class ToolStore extends NormalLocation {
    public ToolStore(Player player) {
        super(player, "ToolStore");
    }

    @Override
    boolean onLocation() {
        System.out.println("You are in the ToolStore");
        System.out.println("What do you want to buy?");
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
            }
            case 2 -> {
                printArmor();
            }
            case 3 -> {
                System.out.println("You exit the ToolStore");
                return false;
            }
        }

        return true;
    }

    public void printArmor() {
        System.out.println("You have bought a armor");
    }

    public void printWeapon() {
        System.out.println("------------GUNS------------");
    }
}
