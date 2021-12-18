package tokyo.northside.oxfordapi;

/**
 * POJO for store one dictionary entry.
 *
 * @author Alex Buloichik
 * @author Hiroshi Miura
 */
public class DictionaryEntry implements Comparable<DictionaryEntry> {
    private final String query;
    private final String word;
    private final String article;

    public DictionaryEntry(final String word, final String article) {
        this.query = word.toLowerCase();
        this.word = word;
        this.article = article;
    }

    public DictionaryEntry(final String query, final String word, final String article) {
        this.query = query;
        this.word = word;
        this.article = article;
    }

    public String getQuery() {
        return query;
    }

    public String getWord() {
        return word;
    }

    public String getArticle() {
        return article;
    }

    @Override
    public int compareTo(DictionaryEntry o) {
        if (query.equals(o.query)) {
            return word.compareTo(o.word);
        } else {
            return query.compareTo(o.query);
        }
    }
}
