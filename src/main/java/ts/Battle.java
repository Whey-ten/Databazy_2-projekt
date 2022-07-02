package ts;

import application.DbContext;
import application.rdg.*;
import application.rdg.Character;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Battle {
    private Integer character_1_id;
    private Integer character_2_id;
    private Character character_1;
    private Character character_2;

    private List<Skill> character_1_spells = new ArrayList<>();
    private List<Skill> character_2_spells = new ArrayList<>();

    private Equip character_1_equip;
    private Equip character_2_equip;

    private Item character_1_weapon;
    private Item character_1_armor;
    private Item character_2_weapon;
    private Item character_2_armor;

    private Double character_1_weapon_stat;
    private Double character_2_weapon_stat;
    private Double character_1_armor_stat;
    private Double character_2_armor_stat;

    public Integer getCharacter_1_id() {
        return character_1_id;
    }

    public void setCharacter_1_id(Integer character_1_id) {
        this.character_1_id = character_1_id;
    }

    public Integer getCharacter_2_id() {
        return character_2_id;
    }

    public void setCharacter_2_id(Integer character_2_id) {
        this.character_2_id = character_2_id;
    }

    public List<Skill> getCharacter_1_spells() {
        return character_1_spells;
    }

    public void setCharacter_1_spells(List<Skill> character_1_spells) {
        this.character_1_spells = character_1_spells;
    }

    public List<Skill> getCharacter_2_spells() {
        return character_2_spells;
    }

    public void setCharacter_2_spells(List<Skill> character_2_spells) {
        this.character_2_spells = character_2_spells;
    }

    public Double getCharacter_1_weapon() {
        return character_1_weapon_stat;
    }

    public void setCharacter_1_weapon(Double character_1_weapon) {
        this.character_1_weapon_stat = character_1_weapon;
    }

    public Double getCharacter_2_weapon() {
        return character_2_weapon_stat;
    }

    public void setCharacter_2_weapon(Double character_2_weapon) {
        this.character_2_weapon_stat = character_2_weapon;
    }

    public Double getCharacter_1_armor() {
        return character_1_armor_stat;
    }

    public void setCharacter_1_armor(Double character_1_armor) {
        this.character_1_armor_stat = character_1_armor;
    }

    public Double getCharacter_2_armor() {
        return character_2_armor_stat;
    }

    public void setCharacter_2_armor(Double character_2_armor) {
        this.character_2_armor_stat = character_2_armor;
    }

    public Character getCharacter_1() {
        return character_1;
    }

    public void setCharacter_1(Character character_1) {
        this.character_1 = character_1;
    }

    public Character getCharacter_2() {
        return character_2;
    }

    public void setCharacter_2(Character character_2) {
        this.character_2 = character_2;
    }

    public Equip getCharacter_1_equip() {
        return character_1_equip;
    }

    public void setCharacter_1_equip(Equip character_1_equip) {
        this.character_1_equip = character_1_equip;
    }

    public Equip getCharacter_2_equip() {
        return character_2_equip;
    }

    public void setCharacter_2_equip(Equip character_2_equip) {
        this.character_2_equip = character_2_equip;
    }

    public void setCharacter_1_weapon(Item character_1_weapon) {
        this.character_1_weapon = character_1_weapon;
    }

    public void setCharacter_1_armor(Item character_1_armor) {
        this.character_1_armor = character_1_armor;
    }

    public void setCharacter_2_weapon(Item character_2_weapon) {
        this.character_2_weapon = character_2_weapon;
    }

    public void setCharacter_2_armor(Item character_2_armor) {
        this.character_2_armor = character_2_armor;
    }

    public Double getCharacter_1_weapon_stat() {
        return character_1_weapon_stat;
    }

    public void setCharacter_1_weapon_stat(Double character_1_weapon_stat) {
        this.character_1_weapon_stat = character_1_weapon_stat;
    }

    public Double getCharacter_2_weapon_stat() {
        return character_2_weapon_stat;
    }

    public void setCharacter_2_weapon_stat(Double character_2_weapon_stat) {
        this.character_2_weapon_stat = character_2_weapon_stat;
    }

    public Double getCharacter_1_armor_stat() {
        return character_1_armor_stat;
    }

    public void setCharacter_1_armor_stat(Double character_1_armor_stat) {
        this.character_1_armor_stat = character_1_armor_stat;
    }

    public Double getCharacter_2_armor_stat() {
        return character_2_armor_stat;
    }

    public void setCharacter_2_armor_stat(Double character_2_armor_stat) {
        this.character_2_armor_stat = character_2_armor_stat;
    }

    /**
     * Function loads characters, their skills and equipment
     */
    public void load_stuff() throws SQLException {
        //loading skills for characters
        List<Available> help_list = new ArrayList<>();
        help_list = AvailableFinder.getInstance().findAvailable(character_1.getClass_id());
        for(Available available : help_list){
            character_1_spells.add(SkillFinder.getInstance().findById(available.getSkill_id()));
        }
        help_list = new ArrayList<>();
        help_list = AvailableFinder.getInstance().findAvailable(character_2.getClass_id());
        for(Available available : help_list){
            character_2_spells.add(SkillFinder.getInstance().findById(available.getSkill_id()));
        }

        //loading equipment for characters
        character_1_equip = EquipFinder.getInstance().findById(character_1.getEquipment());
        character_2_equip = EquipFinder.getInstance().findById(character_2.getEquipment());

        character_1_weapon = ItemFinder.getInstance().findById(character_1_equip.getWeapon());
        character_1_armor = ItemFinder.getInstance().findById(character_1_equip.getArmor());
        character_2_weapon = ItemFinder.getInstance().findById(character_2_equip.getWeapon());
        character_2_armor = ItemFinder.getInstance().findById(character_2_equip.getArmor());

        character_1_weapon_stat = character_1_weapon.getPower_stat();
        character_2_weapon_stat = character_2_weapon.getPower_stat();
        character_1_armor_stat = character_1_armor.getDefense_stat();
        character_2_armor_stat = character_2_armor.getDefense_stat();
    }

    /**
     * Function creates new simulation of battle loads data and calls it
     */
    public void callSimulation() throws SQLException {
        Simulation simulation = new Simulation();
        simulation.setCharacter_1(character_1);
        simulation.setCharacter_1_HP(character_1.getCurrent_hp());
        simulation.setCharacter_1_power(character_1.getPower()*character_1_weapon_stat);
        simulation.setCharacter_1_defense(character_1.getDefense()*character_1_armor_stat);
        simulation.setCharacter_1_spells(character_1_spells);
        simulation.setName_1(character_1.getName());

        simulation.setCharacter_2(character_2);
        simulation.setCharacter_2_HP(character_2.getCurrent_hp());
        simulation.setCharacter_2_power(character_2.getPower()*character_2_weapon_stat);
        simulation.setCharacter_2_defense(character_2.getDefense()*character_2_armor_stat);
        simulation.setCharacter_2_spells(character_2_spells);
        simulation.setName_2(character_2.getName());

        simulation.battleSimulation();
    }

    /**
     * Function checks if instances are correct and locks database.
     * First it calls "load_stuff" to load all needed data.
     * After that calls "call_simulation".
     */
    public void start() throws SQLException, Exception1 {
        Connection c = DbContext.getConnection();
        c.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
        int counter = 15;
        while (0 < counter-- ) {
            c.setAutoCommit(false);
            try {
                character_1 = CharacterFinder.getInstance().findById(character_1_id);
                if (character_1 == null) {
                    throw new Exception1("There is no character with this ID");
                }
                character_2 = CharacterFinder.getInstance().findById(character_2_id);
                if (character_2 == null) {
                    throw new Exception1("There is no character with this ID");
                }
                if (character_1.getPlayer_id().equals(character_2.getPlayer_id())) {
                    throw new Exception1("You cant fight your own character.");
                }
                load_stuff();
                callSimulation();
                c.commit();
                return;
            } catch (SQLException e) {
                c.rollback();
                if ("40001".equals(e.getSQLState()) == false) {
                    throw e;
                }
            } catch (Exception e) {
                c.rollback();
                throw e;
            } finally {
                c.setAutoCommit(true);
            }

        }

    }

}
