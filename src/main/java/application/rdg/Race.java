package application.rdg;

import application.DbContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Sebastian Jankovic
 *
 * Class for characters that allows us to work with them.
 * It's able to insert new race into databse or delete existing one.
 */

public class Race {
    private Integer id;
    private String name;
    private String lore;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLore() {
        return lore;
    }

    public void setLore(String lore) {
        this.lore = lore;
    }

    public void insert() throws SQLException{
        try(PreparedStatement s = DbContext.getConnection().prepareStatement("INSERT INTO races (name,lore) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS)){
            s.setString(1, name);
            s.setString(2, lore);

            s.executeUpdate();

            try(ResultSet r = s.getGeneratedKeys()){
                r.next();
                id = r.getInt(1);
            }
        }
    }

    public void delete() throws SQLException{
        if(id == null){
            throw new IllegalStateException("id is wrong");
        }
        try(PreparedStatement s = DbContext.getConnection().prepareStatement("DELETE FROM races WHERE id =?")){
            s.setInt(1, id);

            s.executeUpdate();
        }
    }


}
