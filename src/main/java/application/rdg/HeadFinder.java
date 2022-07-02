package application.rdg;

import application.DbContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Sebastian Jankovic
 *
 * Support class for Head, this class searches database and return instances.
 * It's able to return one specific or one random.
 */

public class HeadFinder {
    private static final HeadFinder INSTANCE = new HeadFinder();
    public static HeadFinder getInstance() {return INSTANCE;}
    private HeadFinder(){}

    public Head findById(int id) throws SQLException{
        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM heads WHERE id = ?")){
            s.setInt(1, id);
            try(ResultSet r = s.executeQuery()){
                if (r.next()){
                    Head h = new Head();

                    h.setId(r.getInt("id"));
                    h.setHead_type(r.getString("head_type"));

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

    public Head random() throws SQLException{
        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM heads ORDER BY random() LIMIT 1")){
            try(ResultSet r = s.executeQuery()){
                if (r.next()){
                    Head head = new Head();

                    head.setId(r.getInt("id"));
                    head.setHead_type(r.getString("head_type"));

                    return head;
                } else {
                    return null;
                }
            }
        }
    }
}
