package application.rdg;

import application.DbContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Sebastian Jankovic
 *
 * Support class for Available, this class searches databse and returns instances
 * It's able to return all instances in databse or one specific
 */
public class AvailableFinder {
    private static final AvailableFinder INSTANCE = new AvailableFinder();
    public static AvailableFinder getInstance(){ return INSTANCE;}
    private AvailableFinder(){}

    public List<Available> findAvailable(int id) throws SQLException {
        try(PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM available WHERE class_id = ?")){
            s.setInt(1, id);
            try(ResultSet r = s.executeQuery()){
                List<Available> elements = new ArrayList<>();
                while (r.next()){
                    Available a = new Available();
                    a.setSkill_id(r.getInt("skill_id"));
                    a.setClass_id(r.getInt("class_id"));

                    elements.add(a);
                }
                return elements;
            }
        }
    }

    public Available find(int id_skill, int id_class) throws SQLException {
        try(PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM available WHERE skill_id = ? AND class_id = ?")){
            s.setInt(1, id_skill);
            s.setInt(2, id_class);
            try(ResultSet r = s.executeQuery()){
                if(r.next()){
                    Available a = new Available();
                    a.setSkill_id(r.getInt("skill_id"));
                    a.setClass_id(r.getInt("class_id"));

                    if(r.next()){
                        throw new RuntimeException("Pohyb na dalsi returnlo");
                    }
                    return a;
                } else {
                    return null;
                }
            }
        }
    }
}
