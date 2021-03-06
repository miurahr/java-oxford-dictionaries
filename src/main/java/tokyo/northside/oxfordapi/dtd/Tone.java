package tokyo.northside.oxfordapi.dtd;

public class Tone {
    private String type;
    private String value;

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Tone{" + "type='" + type + '\'' + ", value='" + value + '\'' + '}';
    }
}
