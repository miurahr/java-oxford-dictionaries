package tokyo.northside.oxfordapi.dtd;

import java.util.List;

public class LexicalEntry {
    private List<Compound> compounds;
    private List<Entry> entries;
    private String language;
    private String text;
    private LexicalCategory lexicalCategory;
    private List<Derivative> derivatives;
    private List<DerivativeOf> derivativeOf;
    private List<Phrase> phrases;
    private List<PhrasalVerb> phrasalVerbs;
    private List<GrammaticalFeature> grammaticalFeatures;
    private List<VariantForm> variantForms;
    private List<Note> notes;
    private List<Pronunciation> pronunciations;
    private String root;

    public List<Compound> getCompounds() {
        return compounds;
    }

    public void setCompounds(final List<Compound> compounds) {
        this.compounds = compounds;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(final List<Entry> entries) {
        this.entries = entries;
    }

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

    public LexicalCategory getLexicalCategory() {
        return lexicalCategory;
    }

    public void setLexicalCategory(final LexicalCategory lexicalCategory) {
        this.lexicalCategory = lexicalCategory;
    }

    public List<Phrase> getPhrases() {
        return phrases;
    }

    public void setPhrases(final List<Phrase> phrases) {
        this.phrases = phrases;
    }

    public List<Derivative> getDerivatives() {
        return derivatives;
    }

    public void setDerivatives(final List<Derivative> derivatives) {
        this.derivatives = derivatives;
    }

    public List<PhrasalVerb> getPhrasalVerbs() {
        return phrasalVerbs;
    }

    public void setPhrasalVerbs(final List<PhrasalVerb> phrasalVerbs) {
        this.phrasalVerbs = phrasalVerbs;
    }

    public List<DerivativeOf> getDerivativeOf() {
        return derivativeOf;
    }

    public void setDerivativeOf(final List<DerivativeOf> derivativeOf) {
        this.derivativeOf = derivativeOf;
    }

    public List<GrammaticalFeature> getGrammaticalFeatures() {
        return grammaticalFeatures;
    }

    public void setGrammaticalFeatures(final List<GrammaticalFeature> grammaticalFeatures) {
        this.grammaticalFeatures = grammaticalFeatures;
    }

    public List<VariantForm> getVariantForms() {
        return variantForms;
    }

    public void setVariantForms(final List<VariantForm> variantForms) {
        this.variantForms = variantForms;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(final List<Note> notes) {
        this.notes = notes;
    }

    public List<Pronunciation> getPronunciations() {
        return pronunciations;
    }

    public void setPronunciations(final List<Pronunciation> pronunciations) {
        this.pronunciations = pronunciations;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(final String root) {
        this.root = root;
    }

    @Override
    public String toString() {
        return "LexicalEntry{" +
                "compounds=" + compounds +
                ", entries=" + entries +
                ", language='" + language + '\'' +
                ", text='" + text + '\'' +
                ", lexicalCategory=" + lexicalCategory +
                ", derivatives=" + derivatives +
                ", derivativeOf=" + derivativeOf +
                ", phrases=" + phrases +
                ", phrasalVerbs=" + phrasalVerbs +
                ", grammaticalFeatures=" + grammaticalFeatures +
                ", variantForms=" + variantForms +
                ", notes=" + notes +
                ", pronunciations=" + pronunciations +
                ", root='" + root + '\'' +
                '}';
    }
}
