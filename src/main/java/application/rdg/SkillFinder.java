package application.rdg;

import application.DbContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Sebastian Jankovic
 *
 * Support class for Skill, this class searches database and return instances.
 * It's able to return one specific instance.
 */

public class SkillFinder {
    private static final SkillFinder INSTANCE = new SkillFinder();
    public static SkillFinder getInstance(){return INSTANCE;}
    private SkillFinder(){}

    public Skill findById(int id) throws SQLException{
        try(PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM skills WHERE id = ?")){
            s.setInt(1, id);
            try(ResultSet r = s.executeQuery()){
                if(r.next()){
                    Skill skill = new Skill();

                    skill.setId(r.getInt("id"));
                    skill.setName(r.getString("name"));
                    skill.setType(r.getString("type"));
                    skill.setEffectivity(r.getDouble("effectivity"));

                    if(r.next()){
                        throw new RuntimeException("Pohyb na dalsi returnlo");
                    }
                    return skill;
                } else {
                    return null;
                }
            }
        }
    }
}
