package tokyo.northside.oxfordapi.dtd;

import java.util.List;

public class SynonymAntonym {
    private String id;
    private List<Domain> domains;
    private String language;
    private List<Region> regions;
    private List<Register> registers;
    private String text;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(final String language) {
        this.language = language;
    }

    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public List<Domain> getDomains() {
        return domains;
    }

    public void setDomains(final List<Domain> domains) {
        this.domains = domains;
    }

    public List<Region> getRegions() {
        return regions;
    }

    public void setRegions(final List<Region> regions) {
        this.regions = regions;
    }

    public List<Register> getRegisters() {
        return registers;
    }

    public void setRegisters(final List<Register> registers) {
        this.registers = registers;
    }

    @Override
    public String toString() {
        return "SynonymAntonym{" +
                "id='" + id + '\'' +
                ", domains=" + domains +
                ", language='" + language + '\'' +
                ", regions=" + regions +
                ", registers=" + registers +
                ", text='" + text + '\'' +
                '}';
    }
}
