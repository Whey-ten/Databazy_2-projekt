package application.rdg;

import application.DbContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 * @author Sebastian Jankovic
 *
 * Class for credits in credits_history that allows us to work with them.
 * It's able to insert new credits information into databse.
 */

public class Credits {
    private Integer id;
    private Integer id_player;
    private String type;
    private Integer amount;
    private Date date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_player() {
        return id_player;
    }

    public void setId_player(Integer id_player) {
        this.id_player = id_player;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void insert() throws SQLException{
        try(PreparedStatement s = DbContext.getConnection().prepareStatement("INSERT INTO credits_history(id_player, type, amount, date) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS)){
            s.setInt(1, id_player);
            s.setString(2, type);
            s.setInt(3, amount);
            s.setDate(4, (java.sql.Date) date);

            s.executeUpdate();

            try(ResultSet r = s.getGeneratedKeys()){
                r.next();
                id = r.getInt(1);
            }
        }
    }
}
