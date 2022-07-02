package application.rdg;

import application.DbContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Sebastian Jankovic
 *
 * Class for data in table possible_classes that allows us to create instance of one.
 * It's able to insert new instance into databse or delete existing one.
 */

public class PossibleClass {
    private Integer race_id;
    private Integer class_id;

    public Integer getRace_id() {
        return race_id;
    }

    public void setRace_id(Integer race_id) {
        this.race_id = race_id;
    }

    public Integer getClass_id() {
        return class_id;
    }

    public void setClass_id(Integer class_id) {
        this.class_id = class_id;
    }

    public void insert() throws SQLException {
        try(PreparedStatement s = DbContext.getConnection().prepareStatement("INSERT INTO possible_classes (race_id, class_id) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS)){
            s.setInt(1, race_id);
            s.setInt(2, class_id);

            s.executeUpdate();

            try(ResultSet r = s.getGeneratedKeys()){
                r.next();
            }
        }
    }

    public void delete() throws SQLException{
        try(PreparedStatement s = DbContext.getConnection().prepareStatement("DELETE FROM possible_classes WHERE race_id = ? AND class_id = ?")){
            s.setInt(1, race_id);
            s.setInt(2, class_id);

            s.executeUpdate();
        }
    }
}
