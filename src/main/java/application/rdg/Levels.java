package application.rdg;

/**
 * @author Sebastian Jankovic
 *
 * Class for levels that allows us to create instance of one.
 */

public class Levels {
    private Integer id;
    private Integer exp;
    private Double u_hp;
    private Double u_power;
    private Double u_defense;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }

    public Double getU_hp() {
        return u_hp;
    }

    public void setU_hp(Double u_hp) {
        this.u_hp = u_hp;
    }

    public Double getU_power() {
        return u_power;
    }

    public void setU_power(Double u_power) {
        this.u_power = u_power;
    }

    public Double getU_defense() {
        return u_defense;
    }

    public void setU_defense(Double u_defense) {
        this.u_defense = u_defense;
    }
}
