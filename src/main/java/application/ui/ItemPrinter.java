package application.ui;

import application.rdg.Item;

/**
 * @author Sebastian Jankovic
 *
 * Support class for Items, this class prints out instances given to it.
 */

public class ItemPrinter {
    private static final ItemPrinter INSTANCE = new ItemPrinter();
    public static ItemPrinter getInstance(){ return INSTANCE;}
    private ItemPrinter(){}

    public void print(Item item){
        if(item == null){
            throw new NullPointerException("item was null");
        }
        System.out.println("--------------");
        System.out.print("id :            ");
        System.out.println(item.getId());
        System.out.print("name :          ");
        System.out.println(item.getName());
        System.out.print("rarity :        ");
        System.out.println(item.getRarity());
        System.out.print("power stat :    ");
        System.out.println(item.getPower_stat());
        System.out.print("defense stat :    ");
        System.out.println(item.getDefense_stat());
        System.out.println("--------------");

    }
}
