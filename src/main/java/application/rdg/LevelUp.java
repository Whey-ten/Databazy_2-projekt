package application.rdg;

import java.sql.SQLException;

/**
 * @author Sebastian Jankovic
 *
 * Class for character leveling.
 * levelUp() - function level-ups character, it changes character statistics according to modifier from levels table
 * levelDown() - function is reverse of what levelUp() does
 */

public class LevelUp {
    private Character character;
    private Levels levels;
    private boolean fake;
    private int level_up_to_be;

    public int getLevel() {
        return level_up_to_be;
    }

    public void setLevel(int level) {
        this.level_up_to_be = level_up_to_be;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public Levels getLevels() {
        return levels;
    }

    public void setLevels(Levels levels) {
        this.levels = levels;
    }

    public boolean isFake() {
        return fake;
    }

    public void setFake(boolean fake) {
        this.fake = fake;
    }

    public void levelUp() throws SQLException {
        int exp;
        double hp = Math.floor(character.getMax_hp() * levels.getU_hp());
        double power = Math.floor(character.getPower() * levels.getU_power());
        double defense = Math.floor(character.getDefense() * levels.getU_defense());

        if(fake){
            exp = levels.getExp() / 2 ;
            level_up_to_be = character.getLevel() + 1;
        } else {
            exp = character.getExp();
            level_up_to_be = character.getLevel() + 1;

        }
        character.setCurrent_hp(hp);
        character.setMax_hp(hp);
        character.setPower(power);
        character.setDefense(defense);
        character.setExp(exp);
        character.setLevel(level_up_to_be);
        character.update();

    }

    public void levelDown() throws SQLException {
        int exp;
        double hp = Math.floor(character.getMax_hp() / levels.getU_hp());
        double power = Math.floor(character.getPower() / levels.getU_power());
        double defense = Math.floor(character.getDefense() / levels.getU_defense());

        if(fake){
            exp = levels.getExp();
            level_up_to_be = character.getLevel() - 1;
        } else {
            exp = character.getExp();
            level_up_to_be = character.getLevel() - 1;
        }
        character.setCurrent_hp(hp);
        character.setMax_hp(hp);
        character.setPower(power);
        character.setDefense(defense);
        character.setExp(exp);
        character.setLevel(level_up_to_be);
        character.update();

    }




}
