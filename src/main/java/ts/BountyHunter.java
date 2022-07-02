package ts;

import application.DbContext;
import application.rdg.*;
import application.rdg.Character;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class BountyHunter {
    private int month;
    private List<Integer> h_hunters = new ArrayList<>();

    private Integer best_hunter;
    private Integer h_hunter_1;
    private Integer h_hunter_2;
    private Integer h_hunter_3;

    private Character character_best_hunter;
    private Character character_h_hunter_1;
    private Character character_h_hunter_2;
    private Character character_h_hunter_3;

    private Player player_best_hunter;
    private Player player_h_hunter_1;
    private Player player_h_hunter_2;
    private Player player_h_hunter_3;


    /**
     * Function finds players of best hunters characters, rewards them with credits and inserts information about it into database
     */
    public void rewards(List<Player> players) throws SQLException {
        int money = 600;
        for(Player p : players){
            Credits c = new Credits();
            int credits = p.getCredits() + money;
            p.setCredits(credits);
            p.update();

            c.setId_player(p.getId());
            c.setType("+");
            c.setAmount(money);
            java.sql.Date sqlDate = java.sql.Date.valueOf(java.time.LocalDate.now());
            Timestamp ts = new Timestamp(sqlDate.getTime());
            c.setDate(sqlDate);
            c.insert();

            money -= 150;
        }
    }

    /**
     * Function loads all data needed to proceed, if no one killed best hunter they will be null
     */
    public void load() throws SQLException {
        List<Player> players = new ArrayList<>();
        character_best_hunter = CharacterFinder.getInstance().findById(best_hunter);
        player_best_hunter = PlayerFinder.getInstance().findById(character_best_hunter.getPlayer_id());
        players.add(player_best_hunter);
        if(h_hunters.size() == 3){
            h_hunter_1 = h_hunters.get(0);
            character_h_hunter_1 = CharacterFinder.getInstance().findById(h_hunter_1);
            player_h_hunter_1 = PlayerFinder.getInstance().findById(character_h_hunter_1.getPlayer_id());
            players.add(player_h_hunter_1);

            h_hunter_2 = h_hunters.get(1);
            character_h_hunter_2 = CharacterFinder.getInstance().findById(h_hunter_2);
            player_h_hunter_2 = PlayerFinder.getInstance().findById(character_h_hunter_2.getPlayer_id());
            players.add(player_h_hunter_2);

            h_hunter_3 = h_hunters.get(2);
            character_h_hunter_3 = CharacterFinder.getInstance().findById(h_hunter_3);
            player_h_hunter_3 = PlayerFinder.getInstance().findById(character_h_hunter_3.getPlayer_id());
            players.add(player_h_hunter_3);

        } else if(h_hunters.size() == 2){
            h_hunter_1 = h_hunters.get(0);
            character_h_hunter_1 = CharacterFinder.getInstance().findById(h_hunter_1);
            player_h_hunter_1 = PlayerFinder.getInstance().findById(character_h_hunter_1.getPlayer_id());
            players.add(player_h_hunter_1);

            h_hunter_2 = h_hunters.get(1);
            character_h_hunter_2 = CharacterFinder.getInstance().findById(h_hunter_2);
            player_h_hunter_2 = PlayerFinder.getInstance().findById(character_h_hunter_2.getPlayer_id());
            players.add(player_h_hunter_2);
        } else if (h_hunters.size() == 1){
            h_hunter_1 = h_hunters.get(0);
            character_h_hunter_1 = CharacterFinder.getInstance().findById(h_hunter_1);
            player_h_hunter_1 = PlayerFinder.getInstance().findById(character_h_hunter_1.getPlayer_id());
            players.add(player_h_hunter_1);
        }

        rewards(players);
    }

    /**
     * Function locks database and checks if instances are correct
     */
    public void resolve() throws SQLException, Exception1 {
        Connection c = DbContext.getConnection();
        c.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        int counter = 15;
        while (0 < counter-- ) {
            c.setAutoCommit(false);
            try {
                if (month < 1 || month > 12) {
                    throw new Exception1("There is no such month in our universe");
                }
                best_hunter = BountyHunterFinder.getInstance().bestHunter(month);
                if (best_hunter == null) {
                    throw new Exception1("There we no fights this month.");
                }
                h_hunters = BountyHunterFinder.getInstance().bestHunterOfHunter(month, best_hunter);
                load();
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

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public List<Integer> getH_hunters() {
        return h_hunters;
    }

    public void setH_hunters(List<Integer> h_hunters) {
        this.h_hunters = h_hunters;
    }

    public Integer getBest_hunter() {
        return best_hunter;
    }

    public void setBest_hunter(Integer best_hunter) {
        this.best_hunter = best_hunter;
    }

    public Integer getH_hunter_1() {
        return h_hunter_1;
    }

    public void setH_hunter_1(Integer h_hunter_1) {
        this.h_hunter_1 = h_hunter_1;
    }

    public Integer getH_hunter_2() {
        return h_hunter_2;
    }

    public void setH_hunter_2(Integer h_hunter_2) {
        this.h_hunter_2 = h_hunter_2;
    }

    public Integer getH_hunter_3() {
        return h_hunter_3;
    }

    public void setH_hunter_3(Integer h_hunter_3) {
        this.h_hunter_3 = h_hunter_3;
    }

    public Character getCharacter_best_hunter() {
        return character_best_hunter;
    }

    public void setCharacter_best_hunter(Character character_best_hunter) {
        this.character_best_hunter = character_best_hunter;
    }

    public Character getCharacter_h_hunter_1() {
        return character_h_hunter_1;
    }

    public void setCharacter_h_hunter_1(Character character_h_hunter_1) {
        this.character_h_hunter_1 = character_h_hunter_1;
    }

    public Character getCharacter_h_hunter_2() {
        return character_h_hunter_2;
    }

    public void setCharacter_h_hunter_2(Character character_h_hunter_2) {
        this.character_h_hunter_2 = character_h_hunter_2;
    }

    public Character getCharacter_h_hunter_3() {
        return character_h_hunter_3;
    }

    public void setCharacter_h_hunter_3(Character character_h_hunter_3) {
        this.character_h_hunter_3 = character_h_hunter_3;
    }

    public Player getPlayer_best_hunter() {
        return player_best_hunter;
    }

    public void setPlayer_best_hunter(Player player_best_hunter) {
        this.player_best_hunter = player_best_hunter;
    }

    public Player getPlayer_h_hunter_1() {
        return player_h_hunter_1;
    }

    public void setPlayer_h_hunter_1(Player player_h_hunter_1) {
        this.player_h_hunter_1 = player_h_hunter_1;
    }

    public Player getPlayer_h_hunter_2() {
        return player_h_hunter_2;
    }

    public void setPlayer_h_hunter_2(Player player_h_hunter_2) {
        this.player_h_hunter_2 = player_h_hunter_2;
    }

    public Player getPlayer_h_hunter_3() {
        return player_h_hunter_3;
    }

    public void setPlayer_h_hunter_3(Player player_h_hunter_3) {
        this.player_h_hunter_3 = player_h_hunter_3;
    }
}
