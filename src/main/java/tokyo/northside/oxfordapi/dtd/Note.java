package tokyo.northside.oxfordapi.dtd;

public class Note {
    private String text;
    private String type;

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
        return "Note{" + "text='" + text + '\'' + ", type='" + type + '\'' + '}';
    }
}
