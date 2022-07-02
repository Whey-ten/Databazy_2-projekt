package application.ui;

import application.rdg.Character;
import application.rdg.Player;
import ts.BountyHunter;

public class BountyHunterPrinter {
    private static final BountyHunterPrinter INSTANCE = new BountyHunterPrinter();
    public static BountyHunterPrinter getInstance(){ return INSTANCE;}
    private BountyHunterPrinter(){}

    public void print(BountyHunter bountyHunter){
        System.out.println("⚔⚔⚔⚔⚔⚔⚔⚔⚔⚔⚔⚔⚔⚔⚔⚔⚔⚔⚔⚔⚔⚔⚔⚔⚔⚔⚔⚔⚔");
        System.out.println("■      BEST HUNTER OF MONTH      ■");
        System.out.println("■--------------------------------■");
        System.out.print("■ Player:            ");
        System.out.println(bountyHunter.getPlayer_best_hunter().getId() + "       ■");
        System.out.print("■ Character ID :     ");
        System.out.println(bountyHunter.getCharacter_best_hunter().getId() + "         ■");
        System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■");
        System.out.println("■    TOP 3 KILLERS OF HUNTER     ■");
        System.out.println("■--------------------------------■");
        if(bountyHunter.getCharacter_h_hunter_1() == null){
            System.out.println("■ 1. : none                      ■");
        } else {
            System.out.print("■ 1. :            ");
            System.out.println(bountyHunter.getCharacter_h_hunter_1().getName() + "■");
        }
        if(bountyHunter.getCharacter_h_hunter_2() == null){
            System.out.println("■ 2. : none                      ■");
        } else {
            System.out.print("■ 2. :            ");
            System.out.println(bountyHunter.getCharacter_h_hunter_2().getName() + "■");
        }
        if(bountyHunter.getCharacter_h_hunter_3() == null){
            System.out.println("■ 3. : none                      ■");
        } else {
            System.out.print("■ 3. :            ");
            System.out.println(bountyHunter.getCharacter_h_hunter_3().getName() + "■");
        }

        System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■");
        System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■");

    }
}
