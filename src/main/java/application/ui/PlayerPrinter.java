package application.ui;

import application.rdg.Player;

/**
 * @author Sebastian Jankovic
 *
 * Support class for Player, this class prints out instances given to it.
 */

public class PlayerPrinter {
    private static final PlayerPrinter INSTANCE = new PlayerPrinter();

    public static PlayerPrinter getInstance() {
        return INSTANCE;
    }

    private PlayerPrinter() {}

    public void print(Player player){
        if(player == null){
            throw new NullPointerException("player was null");
        }

        System.out.print("id :            ");
        System.out.println(player.getId());
        System.out.print("username :      ");
        System.out.println(player.getUsername());
        System.out.print("first name :    ");
        System.out.println(player.getFirst_name());
        System.out.print("last name :     ");
        System.out.println(player.getLast_name());
        System.out.print("email :         ");
        System.out.println(player.getEmail());
        System.out.print("credits :       ");
        System.out.println(player.getCredits());
        System.out.println();

    }
}
