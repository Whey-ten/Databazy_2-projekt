package application.rdg;

import application.DbContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Sebastian Jankovic
 *
 * Support class for PossibleClass, this class searches database and return instances.
 * It's able to return one specific instance.
 */

public class PossibleClassFinder {
    private static final PossibleClassFinder INSTANCE = new PossibleClassFinder();
    public static PossibleClassFinder getInstance(){return INSTANCE;}
    private PossibleClassFinder(){}

    public PossibleClass find(int id_race, int id_class) throws SQLException{
        try(PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM possible_classes WHERE race_id = ? AND class_id = ?")){
            s.setInt(1, id_race);
            s.setInt(2, id_class);
            try(ResultSet r = s.executeQuery()) {
                if (r.next()) {
                    PossibleClass p = new PossibleClass();

                    p.setRace_id(r.getInt("race_id"));
                    p.setClass_id(r.getInt("class_id"));

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
}
