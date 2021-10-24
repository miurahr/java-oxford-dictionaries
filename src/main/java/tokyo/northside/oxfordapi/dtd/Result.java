package tokyo.northside.oxfordapi.dtd;

import java.util.List;

public class Result {
    private String id;
    private String language;
    private List<LexicalEntry> lexicalEntries;
    private String type;
    private String word;
    private List<Pronunciation> pronunciations;

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

    public List<LexicalEntry> getLexicalEntries() {
        return lexicalEntries;
    }

    public void setLexicalEntries(final List<LexicalEntry> lexicalEntries) {
        this.lexicalEntries = lexicalEntries;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getWord() {
        return word;
    }

    public void setWord(final String word) {
        this.word = word;
    }

    public List<Pronunciation> getPronunciations() {
        return pronunciations;
    }

    public void setPronunciations(final List<Pronunciation> pronunciations) {
        this.pronunciations = pronunciations;
    }

    @Override
    public String toString() {
        return "Result{" +
                "id='" + id + '\'' +
                ", language='" + language + '\'' +
                ", lexicalEntries=" + lexicalEntries +
                ", type='" + type + '\'' +
                ", word='" + word + '\'' +
                ", pronunciations=" + pronunciations +
                '}';
    }

}
