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
 * Support class for Head, this class searches database and return instances.
 * It's able to return one specific iteam,one random weapon/armor or all weapons/armors.
 */

public class ItemFinder {
    private static final ItemFinder INSTANCE = new ItemFinder();

    public static ItemFinder getInstance(){
        return INSTANCE;
    }
    private ItemFinder(){}

    public Item findById(int id) throws SQLException {
        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM items WHERE id = ?")){
            s.setInt(1, id);
            try(ResultSet r = s.executeQuery()) {
                if (r.next()){
                    Item i = new Item();

                    i.setId(r.getInt("id"));
                    i.setName(r.getString("name"));
                    i.setRarity(r.getString("rarity"));
                    i.setHp_stat(r.getInt("hp_stat"));
                    i.setPower_stat(r.getDouble("power_stat"));
                    i.setDefense_stat(r.getDouble("defense_stat"));
                    i.setType(r.getString("type"));

                    if(r.next()){
                        throw new RuntimeException("Pohyb na dalsi returnlo");
                    }
                    return i;
                } else {
                    return null;
                }
            }
        }
    }

    public Item randomWeapon() throws SQLException {
        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM items WHERE type = 'weapon' ORDER BY random() LIMIT 1")){
            try(ResultSet r = s.executeQuery()){
                if (r.next()){
                    Item i = new Item();

                    i.setId(r.getInt("id"));
                    i.setName(r.getString("name"));
                    i.setRarity(r.getString("rarity"));
                    i.setHp_stat(r.getInt("hp_stat"));
                    i.setPower_stat(r.getDouble("power_stat"));
                    i.setDefense_stat(r.getDouble("defense_stat"));
                    i.setType(r.getString("type"));

                    return i;
                } else {
                    return null;
                }

            }
        }
    }

    public Item randomArmor() throws SQLException {
        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM items WHERE type = 'armor' ORDER BY random() LIMIT 1")){
            try(ResultSet r = s.executeQuery()){
                if (r.next()){
                    Item i = new Item();

                    i.setId(r.getInt("id"));
                    i.setName(r.getString("name"));
                    i.setRarity(r.getString("rarity"));
                    i.setHp_stat(r.getInt("hp_stat"));
                    i.setPower_stat(r.getDouble("power_stat"));
                    i.setDefense_stat(r.getDouble("defense_stat"));
                    i.setType(r.getString("type"));

                    return i;
                } else {
                    return null;
                }
            }
        }
    }

    public List<Item> findAllWeapons() throws SQLException {
        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM items WHERE type = 'weapon' ")) {
            try (ResultSet r = s.executeQuery()) {
                List<Item> elements = new ArrayList<>();

                while (r.next()) {
                    Item i = new Item();

                    i.setId(r.getInt("id"));
                    i.setName(r.getString("name"));
                    i.setRarity(r.getString("rarity"));
                    i.setHp_stat(r.getInt("hp_stat"));
                    i.setPower_stat(r.getDouble("power_stat"));
                    i.setDefense_stat(r.getDouble("defense_stat"));
                    i.setType(r.getString("type"));

                    elements.add(i);
                }
                return elements;
            }
        }
    }

    public List<Item> findAllArmors() throws SQLException {
        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM items WHERE type = 'armor' ")) {
            try (ResultSet r = s.executeQuery()) {
                List<Item> elements = new ArrayList<>();

                while (r.next()) {
                    Item i = new Item();

                    i.setId(r.getInt("id"));
                    i.setName(r.getString("name"));
                    i.setRarity(r.getString("rarity"));
                    i.setHp_stat(r.getInt("hp_stat"));
                    i.setPower_stat(r.getDouble("power_stat"));
                    i.setDefense_stat(r.getDouble("defense_stat"));
                    i.setType(r.getString("type"));

                    elements.add(i);
                }
                return elements;
            }
        }
    }
}
