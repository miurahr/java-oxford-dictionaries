package tokyo.northside.oxfordapi.dtd;

public class GrammaticalFeature {
    private String id;
    private String text;
    private String type;

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

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "GrammaticalFeature{" + "id='" + id + '\'' + ", text='" + text + '\'' + ", type='" + type + '\'' + '}';
    }
}
