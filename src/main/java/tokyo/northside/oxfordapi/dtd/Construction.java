package tokyo.northside.oxfordapi.dtd;

import java.util.List;

public class Construction {
    private List<Domain> domains;
    private List<Note> notes;
    private List<Region> regions;
    private List<Register> registers;
    private List<Translation> translations;
    private String text;

    public List<Domain> getDomains() {
        return domains;
    }

    public void setDomains(final List<Domain> domains) {
        this.domains = domains;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(final List<Note> notes) {
        this.notes = notes;
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

    public List<Translation> getTranslations() {
        return translations;
    }

    public void setTranslations(final List<Translation> translations) {
        this.translations = translations;
    }

    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Construction{" +
                "domains=" + domains +
                ", notes=" + notes +
                ", regions=" + regions +
                ", registers=" + registers +
                ", translations=" + translations +
                ", text='" + text + '\'' +
                '}';
    }
}
