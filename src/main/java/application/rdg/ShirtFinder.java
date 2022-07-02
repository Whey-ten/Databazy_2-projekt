package application.rdg;

import application.DbContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Sebastian Jankovic
 *
 * Support class for Shirt, this class searches database and return instances.
 * It's able to return one specific or one random.
 */

public class ShirtFinder {
    private static final ShirtFinder INSTANCE = new ShirtFinder();
    public static ShirtFinder getInstance() {return INSTANCE;}
    private ShirtFinder(){}

    public Shirt findById(int id) throws SQLException {
        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM shirts WHERE id = ?")){
            s.setInt(1, id);
            try(ResultSet r = s.executeQuery()){
                if (r.next()){
                    Shirt shirt = new Shirt();

                    shirt.setId(r.getInt("id"));
                    shirt.setShirt_style(r.getString("shirt_style"));

                    if(r.next()){
                        throw new RuntimeException("Pohyb na dalsi returnlo");
                    }
                    return shirt;
                } else {
                    return null;
                }
            }
        }
    }

    public Shirt random() throws SQLException{
        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM shirts ORDER BY random() LIMIT 1")){
            try(ResultSet r = s.executeQuery()){
                if (r.next()){
                    Shirt shirt = new Shirt();

                    shirt.setId(r.getInt("id"));
                    shirt.setShirt_style(r.getString("shirt_style"));

                    return shirt;
                } else {
                    return null;
                }
            }
        }
    }
}
