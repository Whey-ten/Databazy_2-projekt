package application.rdg;

import application.DbContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Sebastian Jankovic
 *
 * Support class for Equip, this class searches database and return instances.
 * It's able return one specific.
 */

public class EquipFinder {
    private static final EquipFinder INSTANCE = new EquipFinder();
    public static EquipFinder getInstance(){return INSTANCE;}
    private EquipFinder(){}

    public Equip findById(int id) throws SQLException{
        try(PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM equip WHERE id =?")){
            s.setInt(1, id);
            try(ResultSet r = s.executeQuery()){
                if(r.next()){
                    Equip e = new Equip();

                    e.setId(r.getInt("id"));
                    e.setWeapon(r.getInt("weapon"));
                    e.setArmor(r.getInt("armor"));

                    if(r.next()){
                        throw new RuntimeException("Pohyb na dalsi return");
                    }
                    return e;
                } else {
                    return null;
                }
            }
        }
    }
}
