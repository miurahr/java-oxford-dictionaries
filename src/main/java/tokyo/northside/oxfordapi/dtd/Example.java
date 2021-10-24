package tokyo.northside.oxfordapi.dtd;


import java.util.List;

public class Example {
    private List<Register> registers;
    private String text;
    private List<Note> notes;
    private List<String> senseIds;
    private List<Translation> translations;
    private List<Region> regions;
    private List<Domain> domains;

    public List<Register> getRegisters() {
        return registers;
    }

    public void setRegisters(final List<Register> registers) {
        this.registers = registers;
    }

    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(final List<Note> notes) {
        this.notes = notes;
    }

    public List<String> getSenseIds() {
        return senseIds;
    }

    public void setSenseIds(final List<String> senseIds) {
        this.senseIds = senseIds;
    }

    public List<Translation> getTranslations() {
        return translations;
    }

    public void setTranslations(final List<Translation> translations) {
        this.translations = translations;
    }

    public List<Region> getRegions() {
        return regions;
    }

    public void setRegions(final List<Region> regions) {
        this.regions = regions;
    }

    public List<Domain> getDomains() {
        return domains;
    }

    public void setDomains(final List<Domain> domains) {
        this.domains = domains;
    }

    @Override
    public String toString() {
        return "Example{" + "registers=" + registers + ", text='" + text + '\'' + ", notes=" + notes +
                ", senseIds=" + senseIds + ", translations=" + translations + ", domains=" + domains +  '}';
    }
}
