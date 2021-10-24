package tokyo.northside.oxfordapi.dtd;

import java.util.List;

public class Sense {
    private List<String> definitions;
    private List<SynonymAntonym> antonyms;
    private List<DomainClass> domainClasses;
    private List<Example> examples;
    private String id;
    private List<SemanticClass> semanticClasses;
    private List<String> shortDefinitions;
    private List<Subsense> subsenses;
    private List<SynonymAntonym> synonyms;
    private List<ThesaurusLink> thesaurusLinks;
    private List<Translation> translations;
    private List<Register> registers;
    private List<Region> regions;
    private List<String> crossReferenceMarkers;
    private List<CrossReference> crossReferences;
    private List<Inflection> inflections;
    private List<Construction> constructions;
    private List<Domain> domains;
    private List<Note> notes;
    private List<VariantForm> variantForms;

    public List<String> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(final List<String> definitions) {
        this.definitions = definitions;
    }

    public List<SynonymAntonym> getAntonyms() {
        return antonyms;
    }

    public void setAntonyms(final List<SynonymAntonym> antonyms) {
        this.antonyms = antonyms;
    }

    public List<DomainClass> getDomainClasses() {
        return domainClasses;
    }

    public void setDomainClasses(final List<DomainClass> domainClasses) {
        this.domainClasses = domainClasses;
    }

    public List<Example> getExamples() {
        return examples;
    }

    public void setExamples(final List<Example> examples) {
        this.examples = examples;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public List<SemanticClass> getSemanticClasses() {
        return semanticClasses;
    }

    public void setSemanticClasses(final List<SemanticClass> semanticClasses) {
        this.semanticClasses = semanticClasses;
    }

    public List<String> getShortDefinitions() {
        return shortDefinitions;
    }

    public void setShortDefinitions(final List<String> shortDefinitions) {
        this.shortDefinitions = shortDefinitions;
    }

    public List<Subsense> getSubsenses() {
        return subsenses;
    }

    public void setSubsenses(final List<Subsense> subsenses) {
        this.subsenses = subsenses;
    }

    public List<SynonymAntonym> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(final List<SynonymAntonym> synonyms) {
        this.synonyms = synonyms;
    }

    public List<ThesaurusLink> getThesaurusLinks() {
        return thesaurusLinks;
    }

    public void setThesaurusLinks(final List<ThesaurusLink> thesaurusLinks) {
        this.thesaurusLinks = thesaurusLinks;
    }

    public List<Register> getRegisters() {
        return registers;
    }

    public void setRegisters(final List<Register> registers) {
        this.registers = registers;
    }

    public List<Region> getRegions() {
        return regions;
    }

    public void setRegions(final List<Region> regions) {
        this.regions = regions;
    }

    public List<String> getCrossReferenceMarkers() {
        return crossReferenceMarkers;
    }

    public void setCrossReferenceMarkers(final List<String> crossReferenceMarkers) {
        this.crossReferenceMarkers = crossReferenceMarkers;
    }

    public List<CrossReference> getCrossReferences() {
        return crossReferences;
    }

    public void setCrossReferences(final List<CrossReference> crossReferences) {
        this.crossReferences = crossReferences;
    }

    public List<Inflection> getInflections() {
        return inflections;
    }

    public void setInflections(final List<Inflection> inflections) {
        this.inflections = inflections;
    }

    public List<Construction> getConstructions() {
        return constructions;
    }

    public void setConstructions(final List<Construction> constructions) {
        this.constructions = constructions;
    }

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

    public List<Translation> getTranslations() {
        return translations;
    }

    public void setTranslations(final List<Translation> translations) {
        this.translations = translations;
    }

    public List<VariantForm> getVariantForms() {
        return variantForms;
    }

    public void setVariantForms(final List<VariantForm> variantForms) {
        this.variantForms = variantForms;
    }

    @Override
    public String toString() {
        return "Sense{" +
                "definitions=" + definitions +
                ", domainClasses=" + domainClasses +
                ", examples=" + examples +
                ", id='" + id + '\'' +
                ", semanticClasses=" + semanticClasses +
                ", shortDefinitions=" + shortDefinitions +
                ", subsenses=" + subsenses +
                ", synonyms=" + synonyms +
                ", thesaurusLinks=" + thesaurusLinks +
                ", registers=" + registers +
                ", regions=" + regions +
                ", crossReferenceMarkers=" + crossReferenceMarkers +
                ", crossReferences=" + crossReferences +
                ", inflections=" + inflections +
                ", constructions=" + constructions +
                ", domains=" + domains +
                ", notes=" + notes +
                ", translations=" + translations +
                ", variantMorms= " + variantForms +
                '}';
    }
}
