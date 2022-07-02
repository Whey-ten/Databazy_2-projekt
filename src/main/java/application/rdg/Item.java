package application.rdg;

/**
 * @author Sebastian Jankovic
 *
 * Class for iteams that allows us to create instance of one.
 */

public class Item {
    private Integer id;
    private String name;
    private String rarity;
    private Integer hp_stat;
    private Double power_stat;
    private Double defense_stat;
    private String type;

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

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public Integer getHp_stat() {
        return hp_stat;
    }

    public void setHp_stat(Integer hp_stat) {
        this.hp_stat = hp_stat;
    }

    public Double getPower_stat() {
        return power_stat;
    }

    public void setPower_stat(Double power_stat) {
        this.power_stat = power_stat;
    }

    public Double getDefense_stat() {
        return defense_stat;
    }

    public void setDefense_stat(Double defense_stat) {
        this.defense_stat = defense_stat;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
