package ts;

import application.DbContext;
import application.rdg.*;
import application.rdg.Character;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

/**
 * @author Sebastian Jankovic
 */
public class ImageChanger {
    private Integer head;
    private Integer body;
    private Integer hair;
    private Integer shirt;
    private Integer pants;
    private Integer char_id;
    private Integer player_id;

    public Integer getChar_id() {
        return char_id;
    }

    public void setChar_id(Integer char_id) {
        this.char_id = char_id;
    }

    public Integer getHead() {
        return head;
    }

    public void setHead(Integer head) {
        this.head = head;
    }

    public Integer getBody() {
        return body;
    }

    public void setBody(Integer body) {
        this.body = body;
    }

    public Integer getHair() {
        return hair;
    }

    public void setHair(Integer hair) {
        this.hair = hair;
    }

    public Integer getShirt() {
        return shirt;
    }

    public void setShirt(Integer shirt) {
        this.shirt = shirt;
    }

    public Integer getPants() {
        return pants;
    }

    public void setPants(Integer pants) {
        this.pants = pants;
    }

    public Integer getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(Integer player_id) {
        this.player_id = player_id;
    }

    /**
     * Function sets new instances for characters visual and than updates character. After that updates new credit balance for player and inserts log about it into database.
     */
    public void update() throws SQLException{
        Player player = PlayerFinder.getInstance().findById(player_id);
        Character character = CharacterFinder.getInstance().findById(char_id);
        Credits credits = new Credits();

        character.setHead(head);
        character.setBody(body);
        character.setHair(hair);
        character.setShirt(shirt);
        character.setPants(pants);

        player.setCredits(player.getCredits() - 100);

        credits.setId_player(player_id);
        credits.setType("-");
        credits.setAmount(100);
        Date date = new Date();
        java.sql.Date sqlDate = java.sql.Date.valueOf(java.time.LocalDate.now());
        credits.setDate(sqlDate);

        player.update();
        character.update();
        credits.insert();
    }

    /**
     * @throws Exception1 is thrown when one of tests is deemed wrong and instance is unusable or doesnt meet given conditions.
     * Function locks database and calls "update".
     */
    public void update_test() throws SQLException, Exception1 {
        Connection c = DbContext.getConnection();
        c.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
        int counter = 15;
        while (0 < counter-- ) {
            c.setAutoCommit(false);
            try {
                Player player = PlayerFinder.getInstance().findById(player_id);
                if (player == null) {
                    throw new Exception1("There is no player with this ID");
                }
                if (player.getCredits() < 100) {
                    throw new Exception1("Insufficient credit's amount on account");
                }
                Character character = CharacterFinder.getInstance().findById(char_id);
                if (character == null) {
                    throw new Exception1("There is no character with this ID");
                }
                if (!character.getPlayer_id().equals(player_id)) {
                    throw new Exception1(player_id + " idk " + character.getPlayer_id());
                }
                update();
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
