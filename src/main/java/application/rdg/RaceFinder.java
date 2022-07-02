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
 * Support class for Race, this class searches database and return instances.
 * It's able to return all instances in databse table or return one specific.
 */

public class RaceFinder {
    private static final RaceFinder INSTANCE = new RaceFinder();

    public static RaceFinder getInstance(){
        return INSTANCE;
    }
    private RaceFinder(){}

    public Race findById(int id) throws SQLException{
        try(PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM races WHERE id = ?")){
            s.setInt(1, id);
            try(ResultSet r = s.executeQuery()){
                if(r.next()){
                    Race race = new Race();

                    race.setId(r.getInt("id"));
                    race.setName(r.getString("name"));
                    race.setLore(r.getString("lore"));

                    if(r.next()){
                        throw new RuntimeException("Pohyb na dalsi returnlo");
                    }
                    return race;
                } else {
                    return null;
                }
            }
        }
    }

    public List<Race> findAll() throws SQLException{
        try(PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM races")){
            try(ResultSet r = s.executeQuery()){
                List<Race> elements = new ArrayList<>();
                while (r.next()){
                    Race race = new Race();

                    race.setId(r.getInt("id"));
                    race.setName(r.getString("name"));
                    race.setLore(r.getString("lore"));

                    elements.add(race);
                }
                return elements;
            }
        }
    }
}
