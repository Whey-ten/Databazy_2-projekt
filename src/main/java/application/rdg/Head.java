package application.rdg;

/**
 * @author Sebastian Jankovic
 *
 * Class for heads that allows us to create instance of one.
 */

public class Head {
    private Integer id;
    private String head_type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHead_type() {
        return head_type;
    }

    public void setHead_type(String head_type) {
        this.head_type = head_type;
    }
}
