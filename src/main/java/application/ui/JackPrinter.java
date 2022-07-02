package application.ui;

import application.rdg.Jack;

/**
 * @author Sebastian Jankovic
 *
 * Support class for Jack   , this class prints out instances given to it.
 */

public class JackPrinter {
    private static final JackPrinter INSTANCE = new JackPrinter();
    public static JackPrinter getInstance(){return INSTANCE;}
    private JackPrinter(){}

    public void print(Jack jack){
        if(jack == null){
            throw new NullPointerException("there were no fights");
        }

        System.out.print("Month :            ");
        System.out.println(jack.getMonth());
        System.out.print("Week :             ");
        System.out.println(jack.getWeek());
        System.out.print("Character ID :     ");
        System.out.println(jack.getChar_id());
        System.out.print("Total kills :      ");
        System.out.println(jack.getKills());
        System.out.print("Killed males :     ");
        System.out.println(jack.getMales());
        System.out.print("Killed females :   ");
        System.out.println(jack.getFemales());
        System.out.println();
    }
}
