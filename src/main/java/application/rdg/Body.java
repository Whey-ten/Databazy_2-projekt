package application.rdg;

/**
 * @author Sebastian Jankovic
 *
 * Class for body that allows us to create instance of body.
 */

public class Body {
    private Integer id;
    private String body_type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBody_type() {
        return body_type;
    }

    public void setBody_type(String body_type) {
        this.body_type = body_type;
    }
}
