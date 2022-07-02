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
 * Support class for Credits, this class searches database and return instances.
 * It's able to return all instances in databse table for specific player.
 */

public class CreditsFinder {
    private static final CreditsFinder INSTANCE = new CreditsFinder();
    public static CreditsFinder getInstance(){return INSTANCE;}
    private CreditsFinder(){}

    public List<Credits> findAllById(int id_player) throws SQLException{
        try(PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM credits_history WHERE id_player=? ")){
            s.setInt(1, id_player);
            try(ResultSet r = s.executeQuery()){
                List<Credits> elements = new ArrayList<>();
                while (r.next()){
                    Credits c = new Credits();

                    c.setId(r.getInt("id"));
                    c.setId_player(r.getInt("id_player"));
                    c.setType(r.getString("type"));
                    c.setAmount(r.getInt("amount"));
                    c.setDate(r.getDate("date"));

                    elements.add(c);
                }
                return elements;
            }
        }
    }
}
