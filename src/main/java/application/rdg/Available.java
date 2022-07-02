package application.rdg;

import application.DbContext;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Sebastian Jankovic
 *
 * Class Available, in which new instance is created and with it you work with table in database
 */

public class Available {
    private Integer skill_id;
    private Integer class_id;

    public Integer getSkill_id() {
        return skill_id;
    }

    public void setSkill_id(Integer skill_id) {
        this.skill_id = skill_id;
    }

    public Integer getClass_id() {
        return class_id;
    }

    public void setClass_id(Integer class_id) {
        this.class_id = class_id;
    }

    public void insert() throws SQLException{
        try(PreparedStatement s = DbContext.getConnection().prepareStatement("INSERT INTO available (skill_id, class_id) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS)){
            s.setInt(1, skill_id);
            s.setInt(2, class_id);

            s.executeUpdate();
        }
    }

    public void delete() throws SQLException{
        try(PreparedStatement s = DbContext.getConnection().prepareStatement("DELETE FROM available WHERE skill_id = ? AND class_id = ?")){
            s.setInt(1, skill_id);
            s.setInt(2, class_id);

            s.executeUpdate();
        }
    }

}
