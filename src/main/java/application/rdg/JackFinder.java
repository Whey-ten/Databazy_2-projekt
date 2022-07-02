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
 * Support class for Jack, this class searches database and return instances.
 * findAll() - this function searches databse and returns multiple instances which are distinct on Month and Week
 *              than it groups them and orders them by count
 */

public class JackFinder {
    private static final JackFinder INSTANCE = new JackFinder();
    public static JackFinder getInstance(){return INSTANCE;}
    private JackFinder(){}

    public List<Jack> findAll() throws SQLException {
        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT DISTINCT ON (date_part('month', time_of_battle), date_part('week', time_of_battle)) date_part('month', time_of_battle) as month, date_part('week', time_of_battle) as week, winner as char_id, count(*) as kills, count(m) as male_k, count(f) as female_k FROM battle_log LEFT JOIN characters m on battle_log.looser = m.id AND m.sex = 'male' LEFT JOIN characters f on battle_log.looser = f.id AND f.sex = 'female' GROUP BY GROUPING SETS ((month, week, winner)) ORDER BY month, week, count(*) DESC")) {
            try (ResultSet r = s.executeQuery()) {
                List<Jack> elements = new ArrayList<>();

                while (r.next()) {
                    Jack j = new Jack();
                    j.setMonth(r.getInt("month"));
                    j.setWeek(r.getInt("week"));
                    j.setChar_id(r.getInt("char_id"));
                    j.setKills(r.getInt("kills"));
                    j.setMales(r.getInt("male_k"));
                    j.setFemales(r.getInt("female_k"));

                    elements.add(j);
                }
                return elements;
            }
        }
    }
}
/*date_part('week', time_of_battle)
* date_part('week', time_of_battle) as week*/