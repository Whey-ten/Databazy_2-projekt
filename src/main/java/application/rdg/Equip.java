package application.rdg;

import application.DbContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Sebastian Jankovic
 *
 * Class for equipment that allows us to work with them.
 * It's able to insert new equipment into databse, update already existing one or delete existing one.
 */

public class Equip {
    private Integer id;
    private Integer weapon; //item_id
    private Integer armor; // item_id

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWeapon() {
        return weapon;
    }

    public void setWeapon(Integer weapon) {
        this.weapon = weapon;
    }

    public Integer getArmor() {
        return armor;
    }

    public void setArmor(Integer armor) {
        this.armor = armor;
    }

    public void insert() throws SQLException{
        try(PreparedStatement s = DbContext.getConnection().prepareStatement("INSERT INTO equip (weapon, armor) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS)){
            s.setInt(1, weapon);
            s.setInt(2, armor);

            s.executeUpdate();

            try(ResultSet r = s.getGeneratedKeys()){
                r.next();
                id = r.getInt(1);
            }
        }
    }

    public void update() throws SQLException{
        if(id == null){
            throw new IllegalStateException("id is wrong");
        }
        try(PreparedStatement s = DbContext.getConnection().prepareStatement("UPDATE equip SET weapon = ?, armor = ? WHERE id=?")){
            s.setInt(1, weapon);
            s.setInt(2, armor);
            s.setInt(3, id);

            s.executeUpdate();
        }
    }

    public void delete() throws SQLException{
        if(id == null){
            throw new IllegalStateException("id is wrong");
        }
        try(PreparedStatement s = DbContext.getConnection().prepareStatement("DELETE FROM equip WHERE id=?")){
            s.setInt(1, id);

            s.executeUpdate();
        }
    }
}
