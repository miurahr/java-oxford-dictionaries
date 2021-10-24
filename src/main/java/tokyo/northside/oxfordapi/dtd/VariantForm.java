package tokyo.northside.oxfordapi.dtd;

import java.util.List;

public class VariantForm {
    private List<Domain> domains;
    private List<Region> regions;
    private List<Register> registers;
    private List<Pronunciation> pronunciations;
    private List<Note> notes;
    private String text;

    public List<Domain> getDomains() {
        return domains;
    }

    public void setDomains(List<Domain> domains) {
        this.domains = domains;
    }

    public List<Region> getRegions() {
        return regions;
    }

    public void setRegions(List<Region> regions) {
        this.regions = regions;
    }

    public List<Register> getRegisters() {
        return registers;
    }

    public void setRegisters(List<Register> registers) {
        this.registers = registers;
    }

    public List<Pronunciation> getPronunciations() {
        return pronunciations;
    }

    public void setPronunciations(final List<Pronunciation> pronunciations) {
        this.pronunciations = pronunciations;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "VariantForm{" + "pronunciations=" + pronunciations + ", text='" + text + '\'' + '}';
    }
}
