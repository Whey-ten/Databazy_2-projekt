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
 * Support class for Character, this class searches database and return instances.
 * It's able to return all instances in databse table or return one specific.
 */

public class CharacterFinder {
    private static final CharacterFinder INSTANCE = new CharacterFinder();

    public static CharacterFinder getInstance(){return INSTANCE;}
    private CharacterFinder() {}

    public Character findById(int id) throws SQLException{
        try(PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM characters WHERE id=?")){
            s.setInt(1, id);
            try(ResultSet r = s.executeQuery()){
                if(r.next()){
                    Character c = new Character();

                    c.setId(r.getInt("id"));
                    c.setPlayer_id(r.getInt("player_id"));
                    c.setName(r.getString("name"));
                    c.setSex(r.getString("sex"));
                    c.setRace_id(r.getInt("race_id"));
                    c.setClass_id(r.getInt("class_id"));
                    c.setExp(r.getInt("c_exp"));
                    c.setLevel(r.getInt("level"));
                    c.setMax_hp(r.getDouble("max_hp"));
                    c.setCurrent_hp(r.getDouble("current_hp"));
                    c.setPower(r.getDouble("power"));
                    c.setDefense(r.getDouble("defense"));
                    c.setIs_alive(r.getBoolean("is_alive"));
                    c.setHead(r.getInt("head"));
                    c.setBody(r.getInt("body"));
                    c.setHair(r.getInt("hair"));
                    c.setShirt(r.getInt("shirt"));
                    c.setPants(r.getInt("pants"));
                    c.setEquipment(r.getInt("equipment"));

                    if(r.next()){
                        throw new RuntimeException("Pohyb na dalsi");
                    }
                    return c;
                } else {
                    return null;
                }
            }
        }
    }

    public List<Character> findAll() throws SQLException{
        try(PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM characters LIMIT 2000")){
            try(ResultSet r = s.executeQuery()){
                List<Character> elements = new ArrayList<>();

                while(r.next()){
                    Character c = new Character();
                    c.setId(r.getInt("id"));
                    c.setPlayer_id(r.getInt("player_id"));
                    c.setName(r.getString("name"));
                    c.setSex(r.getString("sex"));
                    c.setRace_id(r.getInt("race_id"));
                    c.setClass_id(r.getInt("class_id"));
                    c.setExp(r.getInt("c_exp"));
                    c.setLevel(r.getInt("level"));
                    c.setMax_hp(r.getDouble("max_hp"));
                    c.setCurrent_hp(r.getDouble("current_hp"));
                    c.setPower(r.getDouble("power"));
                    c.setDefense(r.getDouble("defense"));
                    c.setIs_alive(r.getBoolean("is_alive"));
                    c.setHead(r.getInt("head"));
                    c.setBody(r.getInt("body"));
                    c.setHair(r.getInt("hair"));
                    c.setShirt(r.getInt("shirt"));
                    c.setPants(r.getInt("pants"));
                    c.setEquipment(r.getInt("equipment"));

                    elements.add(c);
                }
                return elements;
            }
        }
    }
}
