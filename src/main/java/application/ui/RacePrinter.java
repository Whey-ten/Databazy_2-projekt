package application.ui;

import application.rdg.Race;

/**
 * @author Sebastian Jankovic
 *
 * Support class for Race, this class prints out instances given to it.
 */

public class RacePrinter {
    private static final RacePrinter INSTANCE = new RacePrinter();

    public static RacePrinter getInstance() {
        return INSTANCE;
    }

    private RacePrinter() {}

    public void print(Race race){
        if(race == null){
            throw new NullPointerException("race was null");
        }
        System.out.println();
        System.out.print("id :            ");
        System.out.println(race.getId());
        System.out.print("name :          ");
        System.out.println(race.getName());
        System.out.print("lore :          ");
        System.out.println(race.getLore());
        System.out.println();
    }
}
