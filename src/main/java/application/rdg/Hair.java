package application.rdg;

/**
 * @author Sebastian Jankovic
 *
 * Class for hairs that allows us to create instance of one.
 */

public class Hair {
    private Integer id;
    private String hair_style;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHair_style() {
        return hair_style;
    }

    public void setHair_style(String hair_style) {
        this.hair_style = hair_style;
    }
}
