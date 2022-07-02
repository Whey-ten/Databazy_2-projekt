package application.rdg;

import application.DbContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Function finds and returns data for ExtentStatistic
 */
public class ExtentStatisticFinder {
    private static final ExtentStatisticFinder INSTANCE = new ExtentStatisticFinder();
    public static ExtentStatisticFinder getInstance(){ return INSTANCE;}
    private ExtentStatisticFinder(){}

    public List<ExtentStatistic> findTop() throws SQLException {
        try(PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT  p.id as player_id,\n" +
                "       count(*) as number_of_kills,\n" +
                "       date_part('week', bl.time_of_battle) as week\n" +
                "FROM players as p JOIN characters as c ON p.id = c.player_id\n" +
                "JOIN battle_log as bl ON c.id = bl.winner\n" +
                "WHERE date_part('week', bl.time_of_battle) = date_part('week', now())\n" +
                "GROUP BY (p.id, week)\n" +
                "ORDER BY number_of_kills DESC\n" +
                "LIMIT (SELECT count(*) as numebr FROM players)/ 10;")){
            try(ResultSet r = s.executeQuery()){
                List<ExtentStatistic> elements = new ArrayList<>();
                while (r.next()){
                    ExtentStatistic extentStatistic = new ExtentStatistic();
                    extentStatistic.setPlayer_id(r.getInt("player_id"));
                    extentStatistic.setNumber_of_kills(r.getInt("number_of_kills"));
                    extentStatistic.setWeek(r.getInt("week"));

                    elements.add(extentStatistic);
                }
                return elements;
            }
        }
    }

    public List<ExtentStatistic> findOthers() throws SQLException{
        try(PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT  pl.id as player_id,\n" +
                "        count(*) as number_of_kills,\n" +
                "        date_part('week', bl.time_of_battle) as week\n" +
                "FROM players as pl JOIN characters as c ON pl.id = c.player_id\n" +
                "                   JOIN battle_log as bl ON c.id = bl.winner\n" +
                "WHERE date_part('week', bl.time_of_battle) = date_part('week', now())\n" +
                "GROUP BY (pl.id, week)\n" +
                "ORDER BY number_of_kills DESC\n" +
                "OFFSET (SELECT count(*) as numebr FROM players)/ 10;")){
            try(ResultSet r = s.executeQuery()){
                List<ExtentStatistic> elements = new ArrayList<>();
                while (r.next()){
                    ExtentStatistic extentStatistic = new ExtentStatistic();
                    extentStatistic.setPlayer_id(r.getInt("player_id"));
                    extentStatistic.setNumber_of_kills(r.getInt("number_of_kills"));
                    extentStatistic.setWeek(r.getInt("week"));

                    elements.add(extentStatistic);
                }
                return elements;
            }
        }
    }
}
