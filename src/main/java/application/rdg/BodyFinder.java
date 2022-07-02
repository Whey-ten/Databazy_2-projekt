package application.rdg;

import application.DbContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Sebastian Jankovic
 *
 * Support class for Body, this class searches databse and returns instances
 * It's able to retrun specific instance or random one from databse
 */

public class BodyFinder {
    private static final BodyFinder INSTANCE = new BodyFinder();
    public static BodyFinder getInstance() {return INSTANCE;}
    private BodyFinder(){}

    public Body findById(int id) throws SQLException {
        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM bodies WHERE id = ?")){
            s.setInt(1, id);
            try(ResultSet r = s.executeQuery()){
                if (r.next()){
                    Body b = new Body();

                    b.setId(r.getInt("id"));
                    b.setBody_type(r.getString("body_type"));

                    if(r.next()){
                        throw new RuntimeException("Pohyb na dalsi returnlo");
                    }
                    return b;
                } else {
                    return null;
                }
            }
        }
    }

    public Body random() throws SQLException{
        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM bodies ORDER BY random() LIMIT 1")){
            try(ResultSet r = s.executeQuery()){
                if (r.next()){
                    Body body = new Body();

                    body.setId(r.getInt("id"));
                    body.setBody_type(r.getString("body_type"));

                    return body;
                } else {
                    return null;
                }
            }
        }
    }
}
