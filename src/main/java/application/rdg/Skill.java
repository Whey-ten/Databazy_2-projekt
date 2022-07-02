package application.rdg;

import application.DbContext;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Sebastian Jankovic
 *
 * Class for skills that allows us to work with them.
 * It's able to  update already existing one.
 */

public class Skill {
    private Integer id;
    private String name;
    private String type;
    private Double effectivity;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getEffectivity() {
        return effectivity;
    }

    public void setEffectivity(Double effectivity) {
        this.effectivity = effectivity;
    }

    public void update() throws SQLException {
        if(id == null){
            throw new IllegalStateException("id is wrong");
        }
        try(PreparedStatement s = DbContext.getConnection().prepareStatement("UPDATE skills SET name = ?, type = ?, effectivity = ? WHERE id = ?")){
            s.setString(1, name);
            s.setString(2, type);
            s.setDouble(3, effectivity);
            s.setInt(4, id);

            s.executeUpdate();
        }
    }
}
