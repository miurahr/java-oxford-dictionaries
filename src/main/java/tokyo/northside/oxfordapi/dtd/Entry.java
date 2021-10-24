package tokyo.northside.oxfordapi.dtd;


import java.util.List;

public class Entry {
    private List<String> etymologies;
    private String homographNumber;
    private List<Pronunciation> pronunciations;
    private List<Sense> senses;
    private List<GrammaticalFeature> grammaticalFeatures;
    private List<Inflection> inflections;
    private List<CrossReference> crossReferences;
    private List<String> crossReferenceMarkers;
    private List<Note> notes;
    private List<VariantForm> variantForms;

    public List<String> getEtymologies() {
        return etymologies;
    }

    public void setEtymologies(final List<String> etymologies) {
        this.etymologies = etymologies;
    }

    public String getHomographNumber() {
        return homographNumber;
    }

    public void setHomographNumber(final String homographNumber) {
        this.homographNumber = homographNumber;
    }

    public List<Pronunciation> getPronunciations() {
        return pronunciations;
    }

    public void setPronunciations(final List<Pronunciation> pronunciations) {
        this.pronunciations = pronunciations;
    }

    public List<Sense> getSenses() {
        return senses;
    }

    public void setSenses(final List<Sense> senses) {
        this.senses = senses;
    }

    public List<GrammaticalFeature> getGrammaticalFeatures() {
        return grammaticalFeatures;
    }

    public void setGrammaticalFeatures(final List<GrammaticalFeature> grammaticalFeatures) {
        this.grammaticalFeatures = grammaticalFeatures;
    }

    public List<Inflection> getInflections() {
        return inflections;
    }

    public void setInflections(final List<Inflection> inflections) {
        this.inflections = inflections;
    }

    public List<CrossReference> getCrossReferences() {
        return crossReferences;
    }

    public void setCrossReferences(final List<CrossReference> crossReferences) {
        this.crossReferences = crossReferences;
    }

    public List<String> getCrossReferenceMarkers() {
        return crossReferenceMarkers;
    }

    public void setCrossReferenceMarkers(final List<String> crossReferenceMarkers) {
        this.crossReferenceMarkers = crossReferenceMarkers;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(final List<Note> notes) {
        this.notes = notes;
    }

    public List<VariantForm> getVariantForms() {
        return variantForms;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "etymologies=" + etymologies +
                ", homographNumber='" + homographNumber + '\'' +
                ", pronunciations=" + pronunciations +
                ", senses=" + senses +
                ", grammaticalFeatures=" + grammaticalFeatures +
                ", inflections=" + inflections +
                ", crossReferences=" + crossReferences +
                ", crossReferenceMarkers=" + crossReferenceMarkers +
                ", notes=" + notes +
                ", variantForms=" + variantForms +
                '}';
    }

    public void setVariantForms(final List<VariantForm> variantForms) {
        this.variantForms = variantForms;
    }
}
