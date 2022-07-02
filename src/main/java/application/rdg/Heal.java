package application.rdg;

import java.sql.SQLException;

/**
 * @author Sebastian Jankovic
 *
 * Class for character healing.
 * healCharacter() - function check if character is alive currently if yes it heals him to full HP
 *                  if no function heals him and updates his status to alive
 */

public class Heal {
    private Character character;
    private boolean alive;

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void healCharacter() throws SQLException {
        if(alive){
            character.setCurrent_hp(character.getMax_hp());
            character.update();
        } else {
            character.setCurrent_hp(character.getMax_hp());
            character.setIs_alive(true);
            character.update();
        }
    }

    public void healWithSpell(double number){

    }
}
