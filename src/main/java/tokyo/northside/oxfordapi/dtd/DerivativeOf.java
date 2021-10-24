package tokyo.northside.oxfordapi.dtd;

import java.util.List;

public class DerivativeOf {
    private List<Domain> domains;
    private String id;
    private String language;
    private List<Region> regions;
    private String text;

    public List<Domain> getDomains() {
        return domains;
    }

    public void setDomains(final List<Domain> domains) {
        this.domains = domains;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(final String language) {
        this.language = language;
    }

    public List<Region> getRegions() {
        return regions;
    }

    public void setRegions(final List<Region> regions) {
        this.regions = regions;
    }

    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "DerivativeOf{" +
                "domains=" + domains +
                ", id='" + id + '\'' +
                ", language='" + language + '\'' +
                ", regions=" + regions +
                ", text='" + text + '\'' +
                '}';
    }
}
