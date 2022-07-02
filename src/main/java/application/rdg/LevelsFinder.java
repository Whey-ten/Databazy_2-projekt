package application.rdg;

import application.DbContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Sebastian Jankovic
 *
 * Support class for Levels, this class searches database and return instances.
 * It's able to return one specific level from database table.
 */

public class LevelsFinder {
    private static final LevelsFinder INSTANCE = new LevelsFinder();
    public static LevelsFinder getInstance(){ return INSTANCE;}
    private LevelsFinder(){}

    public Levels findById(int id) throws SQLException{
        try(PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM levels WHERE id = ?")){
            s.setInt(1, id);
            try(ResultSet r = s.executeQuery()) {
                if(r.next()){
                    Levels l = new Levels();

                    l.setId(r.getInt("id"));
                    l.setExp(r.getInt("exp_to_next_lvl"));
                    l.setU_hp(r.getDouble("upgrade_hp"));
                    l.setU_power(r.getDouble("upgrade_power"));
                    l.setU_defense(r.getDouble("upgrade_defense"));

                    if(r.next()){
                        throw new RuntimeException("Pohyb na dalsi returnlo");
                    }
                    return l;
                } else {
                    return null;
                }
            }
        }
    }
}
