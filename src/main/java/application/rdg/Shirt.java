package application.rdg;

/**
 * @author Sebastian Jankovic
 *
 * Class for shirts that allows us to create instance of one.
 */

public class Shirt {
    private Integer id;
    private String shirt_style;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShirt_style() {
        return shirt_style;
    }

    public void setShirt_style(String shirt_style) {
        this.shirt_style = shirt_style;
    }
}
