package tokyo.northside.oxfordapi;

import tokyo.northside.oxfordapi.dtd.Result;

import java.util.Collection;
import java.util.List;
import java.util.Map;


public interface IOxfordClient {

    List<Result> queryTranslation(String word, String source, String target) throws OxfordClientException;

    List<Result> queryEntry(String word, String language, boolean strict) throws OxfordClientException;

    Map<String, List<Result>> queryTranslations(Collection<String> words, String source, String target)
            throws OxfordClientException;

    Map<String, List<Result>> queryEntries(Collection<String> words, String language, boolean strict)
            throws OxfordClientException;

    List<OxfordDictionaryEntry> getDefinitions(Collection<String> words, String language, boolean strict, IFormatter formatter)
            throws OxfordClientException;

    default List<OxfordDictionaryEntry> getDefinitions(Collection<String> words, String language, boolean strict)
        throws OxfordClientException {
        return getDefinitions(words, language, strict, new HTMLFormatter());
    }

    List<OxfordDictionaryEntry> getTranslations(Collection<String> words, String source, String target, IFormatter formatter)
            throws OxfordClientException;

    default List<OxfordDictionaryEntry> getTranslations(Collection<String> words, String source, String target)
            throws OxfordClientException {
        return getTranslations(words, source, target, new HTMLFormatter());
    }

    void close();
}
