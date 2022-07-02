package application.rdg;

import application.DbContext;
import ts.Exception1;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Sebastian Jankovic
 *
 * Support class for Pants, this class searches database and return instances.
 * It's able to return one specific or one random.
 */

public class PantsFinder {
    private static final PantsFinder INSTANCE = new PantsFinder();
    public static PantsFinder getInstance() {return INSTANCE;}
    private PantsFinder(){}

    public Pants findById(int id) throws SQLException {
        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM pants WHERE id = ?")){
            s.setInt(1, id);
            try(ResultSet r = s.executeQuery()){
                if (r.next()){
                    Pants p = new Pants();

                    p.setId(r.getInt("id"));
                    p.setPants_style(r.getString("pants_style"));

                    if(r.next()){
                        throw new RuntimeException("Pohyb na dalsi returnlo");
                    }
                    return p;
                } else {
                    return null;
                }
            }
        }
    }
    public Pants random() throws SQLException{
        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM pants ORDER BY random() LIMIT 1")){
            try(ResultSet r = s.executeQuery()){
                if (r.next()){
                    Pants pants = new Pants();

                    pants.setId(r.getInt("id"));
                    pants.setPants_style(r.getString("pants_style"));

                    return pants;
                } else {
                    return null;
                }
            }
        }
    }

}
