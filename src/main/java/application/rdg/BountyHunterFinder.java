package application.rdg;

import application.DbContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BountyHunterFinder {
    private static final BountyHunterFinder INSTANCE = new BountyHunterFinder();
    public static BountyHunterFinder getInstance(){ return INSTANCE;}
    private BountyHunterFinder(){}

    public Integer bestHunter(int month) throws SQLException {
        try(PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT winner FROM battle_log WHERE date_part('month', time_of_battle) = ? GROUP BY winner ORDER BY count(*) DESC LIMIT 1")){
            s.setInt(1, month);
            try(ResultSet r = s.executeQuery()){
                while (r.next()){
                    return r.getInt(1);
                }
            }
        }
        return null;
    }

    public List<Integer> bestHunterOfHunter(int month, int hunter_id) throws SQLException {
        try(PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT winner FROM battle_log WHERE date_part('month', time_of_battle) = ? AND looser = ? GROUP BY winner ORDER BY count(*) DESC LIMIT 3")){
            s.setInt(1, month);
            s.setInt(2, hunter_id);
            try(ResultSet r = s.executeQuery()){
                List<Integer> elements = new ArrayList<>();
                while (r.next()){
                    elements.add(r.getInt(1));
                }
                return elements;
            }
        }
    }
}
