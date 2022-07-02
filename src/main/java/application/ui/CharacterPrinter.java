package application.ui;

import application.rdg.Character;

/**
 * @author Sebastian Jankovic
 *
 * Support class for Character, this class prints out instances given to it.
 */

public class CharacterPrinter {
    private static final CharacterPrinter INSTANCE = new CharacterPrinter();
    public static CharacterPrinter getInstance(){return INSTANCE;}
    private CharacterPrinter() {}
    public void print(Character character){
        if(character == null){
            throw new NullPointerException("character was null");
        }
        System.out.println("##################################");
        System.out.print("# id :             ");//19
        System.out.println(character.getId());
        System.out.print("# player_id :      ");
        System.out.println(character.getPlayer_id());
        System.out.print("# name :           ");
        System.out.println(character.getName());
        System.out.print("# sex :            ");
        System.out.println(character.getSex());
        System.out.print("# race_id :        ");
        System.out.println(character.getRace_id());
        System.out.print("# class_id :       ");
        System.out.println(character.getClass_id());
        System.out.print("# exp :            ");
        System.out.println(character.getExp());
        System.out.print("# level :          ");
        System.out.println(character.getLevel());
        System.out.print("# hp :             ");
        System.out.println(character.getMax_hp() + "/" + character.getCurrent_hp());
        System.out.print("# power :          ");
        System.out.println(character.getPower());
        System.out.print("# defense :        ");
        System.out.println(character.getDefense());
        System.out.print("# alive :          ");
        if(character.isIs_alive()){
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
        System.out.print("# look :           ");
        System.out.println(character.getHead() + ", " + character.getBody() + ", " + character.getHair() + ", " + character.getShirt() + ", " + character.getPants());
        System.out.print("# equip :          "); /*TOTO SPRAVIT LEPSIE*/
        System.out.println(character.getEquipment());
        System.out.println("##################################");
        System.out.println();

    }
}
