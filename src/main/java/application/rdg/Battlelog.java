package application.rdg;

import application.DbContext;

import java.sql.*;

/**
 * @author Sebastian Jankovic
 *
 * Class for battle_log instances that allows us to work with them.
 * It's able to insert new instance into database.
 */

public class Battlelog {
    private Integer id;
    private Integer winner_id;
    private Integer looser_id;
    private Timestamp time_of_battle;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWinner_id() {
        return winner_id;
    }

    public void setWinner_id(Integer winner_id) {
        this.winner_id = winner_id;
    }

    public Integer getLooser_id() {
        return looser_id;
    }

    public void setLooser_id(Integer looser_id) {
        this.looser_id = looser_id;
    }

    public Timestamp getTime_of_battle() {
        return time_of_battle;
    }

    public void setTime_of_battle(Timestamp time_of_battle) {
        this.time_of_battle = time_of_battle;
    }

    public void insert() throws SQLException{
        try(PreparedStatement s = DbContext.getConnection().prepareStatement("INSERT INTO battle_log (winner, looser, time_of_battle) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS)){
            s.setInt(1, winner_id);
            s.setInt(2, looser_id);
            s.setTimestamp(3, time_of_battle);

            s.executeUpdate();

            try (ResultSet r = s.getGeneratedKeys()){
                r.next();
                id = r.getInt(1);
            }
        }
    }
}
