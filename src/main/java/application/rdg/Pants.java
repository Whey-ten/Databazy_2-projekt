package application.rdg;

/**
 * @author Sebastian Jankovic
 *
 * Class for pants that allows us to create instance of one.
 */

public class Pants {
    private Integer id;
    private String pants_style;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPants_style() {
        return pants_style;
    }

    public void setPants_style(String pants_style) {
        this.pants_style = pants_style;
    }
}
