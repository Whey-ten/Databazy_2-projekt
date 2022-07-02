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
 * It's able to insert new character into databse, update already existing one or delete existing one.
 */

public class Character {
    private Integer id;
    private Integer player_id;
    private String name;
    private String sex;
    private Integer race_id;
    private Integer class_id;
    private Integer c_exp;
    private Integer level;
    private Double max_hp;
    private Double current_hp;
    private Double power;
    private Double defense;
    private boolean is_alive;
    private Integer head;
    private Integer body;
    private Integer hair;
    private Integer shirt;
    private Integer pants;
    private Integer equipment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(Integer player_id) {
        this.player_id = player_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getRace_id() {
        return race_id;
    }

    public void setRace_id(Integer race_id) {
        this.race_id = race_id;
    }

    public Integer getClass_id() {
        return class_id;
    }

    public void setClass_id(Integer class_id) {
        this.class_id = class_id;
    }

    public Integer getExp() {
        return c_exp;
    }

    public void setExp(Integer exp) {
        this.c_exp = exp;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Double getMax_hp() {
        return max_hp;
    }

    public void setMax_hp(Double max_hp) {
        this.max_hp = max_hp;
    }

    public Double getCurrent_hp() {
        return current_hp;
    }

    public void setCurrent_hp(Double current_hp) {
        this.current_hp = current_hp;
    }

    public Double getPower() {
        return power;
    }

    public void setPower(Double power) {
        this.power = power;
    }

    public Double getDefense() {
        return defense;
    }

    public void setDefense(Double defense) {
        this.defense = defense;
    }

    public boolean isIs_alive() {
        return is_alive;
    }

    public void setIs_alive(boolean is_alive) {
        this.is_alive = is_alive;
    }

    public Integer getHead() {
        return head;
    }

    public void setHead(Integer head) {
        this.head = head;
    }

    public Integer getBody() {
        return body;
    }

    public void setBody(Integer body) {
        this.body = body;
    }

    public Integer getHair() {
        return hair;
    }

    public void setHair(Integer hair) {
        this.hair = hair;
    }

    public Integer getShirt() {
        return shirt;
    }

    public void setShirt(Integer shirt) {
        this.shirt = shirt;
    }

    public Integer getPants() {
        return pants;
    }

    public void setPants(Integer pants) {
        this.pants = pants;
    }

    public Integer getEquipment() {
        return equipment;
    }

    public void setEquipment(Integer equipment) {
        this.equipment = equipment;
    }

    public void insert() throws SQLException{
        try(PreparedStatement s = DbContext.getConnection().prepareStatement("INSERT INTO characters (player_id, name, sex, race_id, class_id, c_exp, level, max_hp, current_hp, power, defense, is_alive, head, body, hair, shirt, pants, equipment) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)){
            s.setInt(1, player_id);
            s.setString(2, name);
            s.setString(3, sex);
            s.setInt(4, race_id);
            s.setInt(5, class_id);
            s.setInt(6, c_exp);
            s.setInt(7, level);
            s.setDouble(8, max_hp);
            s.setDouble(9, current_hp);
            s.setDouble(10, power);
            s.setDouble(11, defense);
            s.setBoolean(12, is_alive);
            s.setInt(13, head);
            s.setInt(14, body);
            s.setInt(15, hair);
            s.setInt(16, shirt);
            s.setInt(17, pants);
            s.setInt(18, equipment);

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
        try(PreparedStatement s = DbContext.getConnection().prepareStatement("UPDATE characters SET player_id = ?, name = ?, sex=?,race_id=?,class_id=?,c_exp=?,level=?,max_hp=?,current_hp=?,power=?,defense=?,is_alive=?,head=?,body=?,hair=?,shirt=?,pants=?,equipment=? WHERE id=?")){
            s.setInt(1, player_id);
            s.setString(2, name);
            s.setString(3, sex);
            s.setInt(4, race_id);
            s.setInt(5, class_id);
            s.setInt(6, c_exp);
            s.setInt(7, level);
            s.setDouble(8, max_hp);
            s.setDouble(9, current_hp);
            s.setDouble(10, power);
            s.setDouble(11, defense);
            s.setBoolean(12, is_alive);
            s.setInt(13, head);
            s.setInt(14, body);
            s.setInt(15, hair);
            s.setInt(16, shirt);
            s.setInt(17, pants);
            s.setInt(18, equipment);
            s.setInt(19, id);

            s.executeUpdate();

        }
    }
    public void delete() throws SQLException{
        if(id == null){
            throw new IllegalStateException("id is wrong");
        }
        try(PreparedStatement s = DbContext.getConnection().prepareStatement("DELETE FROM characters WHERE id = ?")){
            s.setInt(1, id);

            s.executeUpdate();
        }
    }

}
