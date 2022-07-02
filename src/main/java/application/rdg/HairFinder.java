package application.rdg;

import application.DbContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Sebastian Jankovic
 *
 * Support class for Hair, this class searches database and return instances.
 * It's able to return one specific or one random.
 */

public class HairFinder {
    private static final HairFinder INSTANCE = new HairFinder();
    public static HairFinder getInstance() {return INSTANCE;}
    private HairFinder(){}

    public Hair findById(int id) throws SQLException {
        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM hairs WHERE id = ?")){
            s.setInt(1, id);
            try(ResultSet r = s.executeQuery()){
                if (r.next()){
                    Hair h = new Hair();

                    h.setId(r.getInt("id"));
                    h.setHair_style(r.getString("hair_style"));

                    if(r.next()){
                        throw new RuntimeException("Pohyb na dalsi returnlo");
                    }
                    return h;
                } else {
                    return null;
                }
            }
        }
    }

    public Hair random() throws SQLException{
        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM hairs ORDER BY random() LIMIT 1")){
            try(ResultSet r = s.executeQuery()){
                if (r.next()){
                    Hair hair = new Hair();

                    hair.setId(r.getInt("id"));
                    hair.setHair_style(r.getString("hair_style"));

                    return hair;
                } else {
                    return null;
                }
            }
        }
    }
}
