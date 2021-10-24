package tokyo.northside.oxfordapi.dtd;

import java.util.List;

public class Translation {
    private String language;
    private String text;
    private String type;
    private List<Collocation> collocations;
    private List<Domain> domains;
    private List<GrammaticalFeature> grammaticalFeatures;
    private List<Note> notes;
    private List<Region> regions;
    private List<Register> registers;
    private List<ToneGroup> toneGroups;

    public List<Collocation> getCollocations() {
        return collocations;
    }

    public void setCollocations(final List<Collocation> collocations) {
        this.collocations = collocations;
    }

    public List<Domain> getDomains() {
        return domains;
    }

    public void setDomains(final List<Domain> domains) {
        this.domains = domains;
    }

    public List<GrammaticalFeature> getGrammaticalFeatures() {
        return grammaticalFeatures;
    }

    public void setGrammaticalFeatures(final List<GrammaticalFeature> grammaticalFeatures) {
        this.grammaticalFeatures = grammaticalFeatures;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(final String language) {
        this.language = language;
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

    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }

    public List<ToneGroup> getToneGroups() {
        return toneGroups;
    }

    public void setToneGroups(final List<ToneGroup> toneGroups) {
        this.toneGroups = toneGroups;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Translation{" +
                "collocations=" + collocations +
                ", domains=" + domains +
                ", grammaticalFeatures=" + grammaticalFeatures +
                ", language='" + language + '\'' +
                ", notes=" + notes +
                ", regions=" + regions +
                ", registers=" + registers +
                ", text='" + text + '\'' +
                ", toneGroups=" + toneGroups +
                ", type='" + type + '\'' +
                '}';
    }
}
