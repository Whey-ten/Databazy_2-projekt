package application.rdg;

import application.DbContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Sebastian Jankovic
 *
 * Class for players that allows us to work with them.
 * It's able to insert new player into databse, update already existing one or delete existing one.
 */

public class Player {
    private Integer id;
    private String username;
    private String first_name;
    private String last_name;
    private String email;
    private Integer credits;

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername(){
        return username;
    }

    public void setFirst_name(String first_name){
        this.first_name = first_name;
    }

    public String getFirst_name(){
        return first_name;
    }

    public void setLast_name(String last_name){
        this.last_name = last_name;
    }

    public String getLast_name(){
        return last_name;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return email;
    }

    public void setCredits(Integer credits){
        this.credits = credits;
    }

    public Integer getCredits(){
        return credits;
    }

    public void insert() throws SQLException{
        try(PreparedStatement s = DbContext.getConnection().prepareStatement("INSERT INTO players (username, first_name, last_name, email, credits) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)){
            s.setString(1, username);
            s.setString(2, first_name);
            s.setString(3, last_name);
            s.setString(4, email);
            s.setInt(5, credits);

            s.executeUpdate();

            try (ResultSet r = s.getGeneratedKeys()){
                r.next();
                id = r.getInt(1);
            }
        }
    }

    public void update() throws SQLException{
        if(id == null){
            throw new IllegalStateException("id is wrong");
        }
        try(PreparedStatement s = DbContext.getConnection().prepareStatement("UPDATE players SET username = ?, first_name = ?, last_name = ?, email = ?, credits = ? WHERE id = ?")) {
            s.setString(1, username);
            s.setString(2, first_name);
            s.setString(3, last_name);
            s.setString(4, email);
            s.setInt(5, credits);
            s.setInt(6, id);

            s.executeUpdate();
        }
    }

    public void delete() throws SQLException{
        if(id == null){
            throw new IllegalStateException("id is wrong");
        }
        try(PreparedStatement s = DbContext.getConnection().prepareStatement("DELETE FROM players WHERE id = ?")){
            s.setInt(1, id);

            s.executeUpdate();
        }
    }
}
