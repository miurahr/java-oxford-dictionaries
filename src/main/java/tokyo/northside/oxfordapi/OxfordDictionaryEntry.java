package tokyo.northside.oxfordapi;

/**
 * POJO for store one dictionary entry.
 *
 * @author Alex Buloichik
 * @author Hiroshi Miura
 */
public class OxfordDictionaryEntry implements Comparable<OxfordDictionaryEntry> {
    private final String query;
    private final String word;
    private final String article;

    public OxfordDictionaryEntry(final String word, final String article) {
        this.query = word.toLowerCase();
        this.word = word;
        this.article = article;
    }

    public OxfordDictionaryEntry(final String query, final String word, final String article) {
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
    public int compareTo(final OxfordDictionaryEntry o) {
        if (query.equals(o.query)) {
            return word.compareTo(o.word);
        } else {
            return query.compareTo(o.query);
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final OxfordDictionaryEntry that = (OxfordDictionaryEntry) o;

        if (query != null) {
            if (!query.equals(that.query)) {
                return false;
            }
        } else {
            if (that.query != null) {
                return false;
            }
        }
        if (word != null) {
            if (!word.equals(that.word)) {
                return false;
            }
        } else {
            if (that.word != null) {
                return false;
            }
        }
        if (article != null) {
            return article.equals(that.article);
        }
        return that.article == null;
    }

    @Override
    public int hashCode() {
        int result;
        if (query != null) {
            result = query.hashCode();
        } else {
            result = 0;
        }
        if (word != null) {
            result = 31 * result + word.hashCode();
        } else {
            result = 31 * result + 0;
        }
        if (article != null) {
            result = 31 * result + article.hashCode();
        } else {
            result = 31 * result + 0;
        }
        return result;
    }
}
