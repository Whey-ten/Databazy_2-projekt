package application.ui;

import application.rdg.Skill;

/**
 * @author Sebastian Jankovic
 *
 * Support class for Skill, this class prints out instances given to it.
 */

public class SkillPrinter {
    private static final SkillPrinter INSTANCE = new SkillPrinter();
    public static SkillPrinter getInstance(){ return INSTANCE;}
    private SkillPrinter(){}

    public void print(Skill skill) {
        if(skill == null){
            throw new NullPointerException("skill was null");
        }
        System.out.println("---------------");
        System.out.print("id :            ");
        System.out.println(skill.getId());
        System.out.print("name :          ");
        System.out.println(skill.getName());
        System.out.print("type :          ");
        System.out.println(skill.getType());
        System.out.print("effectivity :   ");
        System.out.println(skill.getEffectivity());
        System.out.println();
    }
}
