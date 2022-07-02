package application.ui;

import application.rdg.Klass;

/**
 * @author Sebastian Jankovic
 *
 * Support class for Klass, this class prints out instances given to it.
 */

public class KlassPrinter {
    private static final KlassPrinter INSTANCE = new KlassPrinter();
    public static KlassPrinter getInstance(){return INSTANCE;}
    private KlassPrinter(){}
    public void print(Klass klass){
        if(klass == null){
            throw new NullPointerException("class was null");
        }
        System.out.println();
        System.out.print("id :            ");
        System.out.println(klass.getId());
        System.out.print("name :          ");
        System.out.println(klass.getName());
        System.out.print("info :          ");
        System.out.println(klass.getInfo());
        System.out.print("basic hp :      ");
        System.out.println(klass.getClass_hp());
        System.out.print("basic power :   ");
        System.out.println(klass.getClass_power());
        System.out.print("basic defense : ");
        System.out.println(klass.getClass_defense());
        System.out.println();
    }
}
