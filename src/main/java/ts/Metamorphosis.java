package ts;

import application.DbContext;
import application.rdg.*;
import application.rdg.Character;

import java.sql.Connection;
import java.sql.SQLException;

public class Metamorphosis {
    private Player player;
    private Integer player_id;
    private Character character_1;
    private Integer character_1_id;
    private Character character_2;
    private Integer character_2_id;
    private Character new_character;
    private String name;
    private Integer weapon;
    private Integer armor;

    public Integer getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(Integer player_id) {
        this.player_id = player_id;
    }

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

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWeapon() {
        return weapon;
    }

    public void setWeapon(Integer weapon) {
        this.weapon = weapon;
    }

    public Integer getArmor() {
        return armor;
    }

    public void setArmor(Integer armor) {
        this.armor = armor;
    }

    private double number(){
        return Math.random()*(1.2-0.8) + 0.8;
    }

    /**
     * Function creates new character. Randomly chooses gender,race and equipment from one of characters. Than sets level and exp on start.
     * New HP,power and defense is calculated from sum of those from characters and than randomly multiplied with number between 0,8-1,2.
     * New visual of character is set randomly(so user has to paid if he doesnt like it).
     * New character is inserted into database and original ones are deleted from it.
     */
    public void fuse() throws SQLException{
        new_character = new Character();
        new_character.setPlayer_id(player.getId());
        new_character.setName(name);
        double rnd = Math.random();
        if(rnd >= 0.5){
            new_character.setSex(character_1.getSex());
            new_character.setRace_id(character_1.getRace_id());
            new_character.setClass_id(character_1.getClass_id());
            weapon = EquipFinder.getInstance().findById(character_1.getId()).getWeapon();
            armor = EquipFinder.getInstance().findById(character_1.getId()).getArmor();
        } else {
            new_character.setSex(character_2.getSex());
            new_character.setRace_id(character_2.getRace_id());
            new_character.setClass_id(character_2.getClass_id());
            weapon = EquipFinder.getInstance().findById(character_2.getId()).getWeapon();
            armor = EquipFinder.getInstance().findById(character_2.getId()).getArmor();
        }
        new_character.setExp(0);
        new_character.setLevel(1);
        double hp = character_1.getMax_hp() + character_2.getMax_hp();
        hp = Math.floor(hp * number());
        new_character.setMax_hp(hp);
        new_character.setCurrent_hp(hp);
        double power = character_1.getPower() + character_2.getPower();
        power = Math.floor(power * number());
        new_character.setPower(power);
        double defense = character_1.getDefense() + character_2.getDefense();
        defense = Math.floor(defense * number());
        new_character.setDefense(defense);
        new_character.setIs_alive(true);
        new_character.setHead(HeadFinder.getInstance().random().getId());
        new_character.setBody(BodyFinder.getInstance().random().getId());
        new_character.setHair(HairFinder.getInstance().random().getId());
        new_character.setShirt(ShirtFinder.getInstance().random().getId());
        new_character.setPants(PantsFinder.getInstance().random().getId());

        Equip equip = new Equip();
        equip.setWeapon(weapon);
        equip.setArmor(armor);
        equip.insert();
        new_character.setEquipment(equip.getId());

        new_character.insert();

        character_1.delete();
        character_2.delete();

    }

    /**
     * @return
     * @throws SQLException is thrown when one of tests is deemed wrong and instance is unusable or doesnt meet given conditions.
     * Function than calls "fuse".
     */
    public Character fuse_test() throws SQLException, Exception1 {
        Connection c = DbContext.getConnection();
        c.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        int counter = 15;
        while (0 < counter-- ) {
            c.setAutoCommit(false);
            try {
                player = PlayerFinder.getInstance().findById(player_id);
                if (player == null) {
                    throw new Exception1("There is no player with this ID");
                }
                character_1 = CharacterFinder.getInstance().findById(character_1_id);
                if (character_1 == null) {
                    throw new Exception1("There is no character with this ID");
                }
                if (!character_1.getPlayer_id().equals(player_id)) {
                    throw new Exception1("You don't own this character.");
                }
                character_2 = CharacterFinder.getInstance().findById(character_2_id);
                if (character_2 == null) {
                    throw new Exception1("There is no character with this ID");
                }
                if (!character_2.getPlayer_id().equals(player_id)) {
                    throw new Exception1("You don't own this character.");
                }
                fuse();
                c.commit();
                return new_character;
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
        return new_character;
    }
}
