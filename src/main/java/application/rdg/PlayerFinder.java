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
 * Support class for Player, this class searches database and return instances.
 * It's able to return all instances in databse table or return one specific.
 */

public class PlayerFinder {
    private static final PlayerFinder INSTANCE = new PlayerFinder();

    public static PlayerFinder getInstance(){
        return INSTANCE;
    }
    private PlayerFinder(){}

    public Player findById(int id) throws SQLException{
        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM players WHERE id = ?")){
            s.setInt(1, id);
            try(ResultSet r = s.executeQuery()) {
                if (r.next()){
                    Player p = new Player();

                    p.setId(r.getInt("id"));
                    p.setUsername(r.getString("username"));
                    p.setFirst_name(r.getString("first_name"));
                    p.setLast_name(r.getString("last_name"));
                    p.setEmail(r.getString("email"));
                    p.setCredits(r.getInt("credits"));

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

    public List<Player> findAll() throws SQLException {
        try(PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM players LIMIT 2000")){
            try(ResultSet r = s.executeQuery()){

                List<Player> elements = new ArrayList<>();

                while(r.next()){
                    Player p = new Player();

                    p.setId(r.getInt("id"));
                    p.setUsername(r.getString("username"));
                    p.setFirst_name(r.getString("first_name"));
                    p.setLast_name(r.getString("last_name"));
                    p.setEmail(r.getString("email"));
                    p.setCredits(r.getInt("credits"));

                    elements.add(p);
                }
                return elements;
            }
        }
    }
}
