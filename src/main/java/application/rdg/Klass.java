package application.rdg;

import application.DbContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Sebastian Jankovic
 *
 * Class for classes that allows us to work with them.
 * It's able to insert new class into databse or delete existing one.
 */

public class Klass {
    private Integer id;
    private String name;
    private String info;
    private Double class_hp;
    private Double class_power;
    private Double class_defense;

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

    public Double getClass_hp() {
        return class_hp;
    }

    public void setClass_hp(Double class_hp) {
        this.class_hp = class_hp;
    }

    public Double getClass_power() {
        return class_power;
    }

    public void setClass_power(Double class_power) {
        this.class_power = class_power;
    }

    public Double getClass_defense() {
        return class_defense;
    }

    public void setClass_defense(Double class_defense) {
        this.class_defense = class_defense;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void insert() throws SQLException {
        try(PreparedStatement s = DbContext.getConnection().prepareStatement("INSERT INTO classes (name, info, class_hp, class_power, class_defense) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)){
            s.setString(1, name);
            s.setString(2, info);
            s.setDouble(3, class_hp);
            s.setDouble(4, class_power);
            s.setDouble(5, class_defense);

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
        try(PreparedStatement s = DbContext.getConnection().prepareStatement("DELETE FROM classes WHERE id =?")){
            s.setInt(1, id);

            s.executeUpdate();
        }
    }

}
