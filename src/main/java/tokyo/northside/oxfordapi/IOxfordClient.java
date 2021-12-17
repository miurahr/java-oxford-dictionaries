package tokyo.northside.oxfordapi;

import tokyo.northside.oxfordapi.dtd.Result;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface IOxfordClient {
    Map<String, List<Result>> getTranslations(Collection<String> words, String source, String target)
            throws OxfordClientException;

    List<Result> getTranslations(String word, String source, String target) throws OxfordClientException;

    Map<String, List<Result>> getEntries(Collection<String>words, String language, boolean strict)
            throws OxfordClientException;

    List<Result> getEntries(String word, String language, boolean strict) throws OxfordClientException;

    void close();
}
