package ts;

import application.rdg.*;
import application.rdg.Character;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class Simulation {
    private Character character_1;
    private Double character_1_HP;
    private Double character_1_power;
    private Double character_1_defense;
    private List<Skill> character_1_spells;
    private String name_1;

    private Character character_2;
    private Double character_2_HP;
    private Double character_2_power;
    private Double character_2_defense;
    private List<Skill> character_2_spells;
    private String name_2;

    /**
     * Function generates random skill for chracter to use
     */
    public Skill skill_ch_1(){
        int rnd = (int)Math.floor(Math.random()*((character_1_spells.size()-1) - 0) + 0);
        return character_1_spells.get(rnd);
    }
    /**
     * Function generates random skill for chracter to use
     */
    public Skill skill_ch_2(){
        int rnd = (int)Math.floor(Math.random()*((character_2_spells.size()-1) - 0) + 0);
        return character_2_spells.get(rnd);
    }

    /**
     * Function simulates and prints data of one turn of character. It makes sure character cant heal while hes given fatal attack or that character overheals himself.
     * Character randomly chooses from skills available for his class
     */
    public void turn_ch_1(){
        Skill skill1;
        skill1 = skill_ch_1();
        if(skill1.getType().equals("support")){
            character_1_HP += skill1.getEffectivity();
            if(character_1_HP > character_1.getMax_hp()){
                character_1_HP = character_1.getMax_hp();
            }
            System.out.println(name_1 + " used spell: " + skill1.getName() + " and now has " + character_1_HP + "HP");
        } else {
            double dmg = (character_1_power * skill1.getEffectivity()) - character_2_defense;
            dmg = Math.round(dmg*100.0) / 100.0;
            if(dmg > 0){
                character_2_HP -= dmg;
                character_2_HP = Math.round(character_2_HP*100.0) / 100.0;
            }
            if (character_2_HP <= 0 ){
                System.out.println(name_1 + " used spell: " + skill1.getName() + " ->  " + name_2 + " got dealt " + dmg + " damage and DIED ");
            } else {
                System.out.println(name_1 + " used spell: " + skill1.getName() + " ->  " + name_2 + " got dealt " + dmg + " damage and now has " + character_2_HP + "HP");
            }
        }

    }

    /**
     * Function simulates and prints data of one turn of character. It makes sure character cant heal while hes given fatal attack or that character overheals himself.
     * Character randomly chooses from skills available for his class
     */
    public void turn_ch_2(){
        Skill skill2;
        skill2 = skill_ch_2();
        if(skill2.getType().equals("support")){
            character_2_HP += skill2.getEffectivity();
            if(character_2_HP > character_2.getMax_hp()){
                character_2_HP = character_2.getMax_hp();
            }
            System.out.println(name_2 + " used spell: " + skill2.getName() + " and now has " + character_2_HP + "HP");
        } else {
            double dmg = (character_2_power * skill2.getEffectivity()) - character_1_defense;
            dmg = Math.round(dmg*100.0) / 100.0;
            if(dmg > 0){
                character_1_HP -= dmg;
                character_1_HP = Math.round(character_1_HP*100.0) / 100.0;
            } else {
                dmg = 0;
            }
            if (character_1_HP <= 0 ){
                System.out.println(name_2 + " used spell: " + skill2.getName() + " ->  " + name_1 + " got dealt " + dmg + " damage and DIED ");
            } else {
                System.out.println(name_2 + " used spell: " + skill2.getName() + " ->  " + name_1 + " got dealt " + dmg + " damage and now has " + character_1_HP + "HP");
            }
        }

    }

    /**
     * Function simulates battle. Each round coin if flipped to see who will get first strike. Characters fight until one is killed.
     */
    public void battleSimulation() throws SQLException {
        Skill skill;
        System.out.println("Battle is started");
        System.out.println("Challenger: " + name_1 + " lvl." + character_1.getLevel());
        System.out.println("Challenged: " + name_2 + " lvl." + character_2.getLevel());
        System.out.println("===============================");
        int round = 1;
        while (character_1_HP > 0 && character_2_HP > 0){
            System.out.println("Round: " + round);
            double coin = Math.random();
            if(coin > 0.5){
                turn_ch_1();
                if(character_2_HP > 0){
                    turn_ch_2();
                }
            } else {
                turn_ch_2();
                if(character_1_HP > 0){
                    turn_ch_1();
                }
            }
            round ++;
            System.out.println();
        }
        if(character_1_HP <= 0){
            System.out.println("Winner of this battle is " + name_2 +"!!");

            character_1.setCurrent_hp(0.0);
            character_1.setIs_alive(false);
            character_1.update();

            character_2.setCurrent_hp(character_2_HP);
            character_2.setExp(character_2.getExp() + (50 * character_1.getLevel()));
            character_2.update();
            Levels levels = LevelsFinder.getInstance().findById(character_2.getLevel());
            if(levels.getExp() < character_2.getExp()){
                LevelUp levelUp = new LevelUp();
                levelUp.setCharacter(character_2);
                levelUp.setFake(false);
                levelUp.setLevels(LevelsFinder.getInstance().findById(character_2.getLevel() + 1));
                levelUp.levelUp();
                System.out.println(name_2 + " LEVELED UP!!");
            }
            Battlelog battlelog = new Battlelog();
            battlelog.setWinner_id(character_2.getId());
            battlelog.setLooser_id(character_1.getId());
            java.sql.Date sqlDate = java.sql.Date.valueOf(java.time.LocalDate.now());
            Timestamp ts = new Timestamp(sqlDate.getTime());
            battlelog.setTime_of_battle(ts);
            battlelog.insert();

        } else {
            System.out.println("Winner of this battle is " + name_1 +"!!");
            character_2.setCurrent_hp(0.0);
            character_2.setIs_alive(false);
            character_2.update();

            character_1.setCurrent_hp(character_1_HP);
            character_1.setExp(character_1.getExp() + (50 * character_2.getLevel()));
            character_1.update();
            Levels levels = LevelsFinder.getInstance().findById(character_1.getLevel());
            if(levels.getExp() < character_1.getExp()){
                LevelUp levelUp = new LevelUp();
                levelUp.setCharacter(character_1);
                levelUp.setFake(false);
                levelUp.setLevels(LevelsFinder.getInstance().findById(character_1.getLevel() + 1));
                levelUp.levelUp();
                System.out.println(name_1 + " LEVELED UP!!");
            }
            Battlelog battlelog = new Battlelog();
            battlelog.setWinner_id(character_1.getId());
            battlelog.setLooser_id(character_2.getId());
            java.sql.Date sqlDate = java.sql.Date.valueOf(java.time.LocalDate.now());
            Timestamp ts = new Timestamp(sqlDate.getTime());
            battlelog.setTime_of_battle(ts);
            battlelog.insert();
        }

    }

    public String getName_1() {
        return name_1;
    }

    public void setName_1(String name_1) {
        this.name_1 = name_1;
    }

    public String getName_2() {
        return name_2;
    }

    public void setName_2(String name_2) {
        this.name_2 = name_2;
    }

    public Character getCharacter_1() {
        return character_1;
    }

    public void setCharacter_1(Character character_1) {
        this.character_1 = character_1;
    }

    public Double getCharacter_1_HP() {
        return character_1_HP;
    }

    public void setCharacter_1_HP(Double character_1_HP) {
        this.character_1_HP = character_1_HP;
    }

    public Double getCharacter_1_power() {
        return character_1_power;
    }

    public void setCharacter_1_power(Double character_1_power) {
        this.character_1_power = character_1_power;
    }

    public Double getCharacter_1_defense() {
        return character_1_defense;
    }

    public void setCharacter_1_defense(Double character_1_defense) {
        this.character_1_defense = character_1_defense;
    }

    public List<Skill> getCharacter_1_spells() {
        return character_1_spells;
    }

    public void setCharacter_1_spells(List<Skill> character_1_spells) {
        this.character_1_spells = character_1_spells;
    }

    public Character getCharacter_2() {
        return character_2;
    }

    public void setCharacter_2(Character character_2) {
        this.character_2 = character_2;
    }

    public Double getCharacter_2_HP() {
        return character_2_HP;
    }

    public void setCharacter_2_HP(Double character_2_HP) {
        this.character_2_HP = character_2_HP;
    }

    public Double getCharacter_2_power() {
        return character_2_power;
    }

    public void setCharacter_2_power(Double character_2_power) {
        this.character_2_power = character_2_power;
    }

    public Double getCharacter_2_defense() {
        return character_2_defense;
    }

    public void setCharacter_2_defense(Double character_2_defense) {
        this.character_2_defense = character_2_defense;
    }

    public List<Skill> getCharacter_2_spells() {
        return character_2_spells;
    }

    public void setCharacter_2_spells(List<Skill> character_2_spells) {
        this.character_2_spells = character_2_spells;
    }
}
