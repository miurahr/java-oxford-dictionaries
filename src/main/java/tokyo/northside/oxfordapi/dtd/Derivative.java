package tokyo.northside.oxfordapi.dtd;

public class Derivative {
    private String id;
    private String text;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Derivative{" + "id='" + id + '\'' + ", text='" + text + '\'' + '}';
    }
}
