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
 * Support class for Klass, this class searches database and return instances.
 * It's able to return all instances in databse table or return one specific.
 */

public class KlassFinder {
    private static final KlassFinder INSTANCE = new KlassFinder();
    public static KlassFinder getInstance(){return INSTANCE;}
    private KlassFinder(){}

    public Klass findById(int id) throws SQLException{
        try(PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM classes WHERE id = ?")){
            s.setInt(1, id);
            try(ResultSet r = s.executeQuery()){
                if(r.next()){
                    Klass k = new Klass();
                    k.setId(r.getInt("id"));
                    k.setName(r.getString("name"));
                    k.setInfo(r.getString("info"));
                    k.setClass_hp(r.getDouble("class_hp"));
                    k.setClass_power(r.getDouble("class_power"));
                    k.setClass_defense(r.getDouble("class_defense"));

                    if(r.next()){
                        throw new RuntimeException("Pohyb na dalsi ret");
                    }
                    return k;
                } else {
                    return null;
                }
            }
        }
    }

    public List<Klass> findAll() throws SQLException {
        try(PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM classes LIMIT 2000")){
            try(ResultSet r = s.executeQuery()) {
                List<Klass> elements = new ArrayList<>();
                while (r.next()) {
                    Klass k = new Klass();
                    k.setId(r.getInt("id"));
                    k.setName(r.getString("name"));
                    k.setInfo(r.getString("info"));
                    k.setClass_hp(r.getDouble("class_hp"));
                    k.setClass_power(r.getDouble("class_power"));
                    k.setClass_defense(r.getDouble("class_defense"));

                    elements.add(k);
                }
                return elements;
            }
        }
    }


}
