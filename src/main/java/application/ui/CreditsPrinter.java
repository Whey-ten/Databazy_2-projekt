package application.ui;

import application.rdg.Credits;

/**
 * @author Sebastian Jankovic
 *
 * Support class for Credits, this class prints out instances given to it.
 */

public class CreditsPrinter {
    private static final CreditsPrinter INSTANCE = new CreditsPrinter();
    public static CreditsPrinter getInstance(){return INSTANCE;}
    private CreditsPrinter(){}

    public void print(Credits credits){
        if(credits == null){
            throw new NullPointerException("credits was null");
        }
        //System.out.println("-----------------");
        System.out.println("    " + credits.getType() + credits.getAmount() + " | " + credits.getDate());
        //System.out.println("----");
        //System.out.println();
    }
}
