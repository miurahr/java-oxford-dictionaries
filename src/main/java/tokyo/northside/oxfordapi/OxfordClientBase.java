package tokyo.northside.oxfordapi;

import tokyo.northside.oxfordapi.dtd.LexicalEntry;
import tokyo.northside.oxfordapi.dtd.Result;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class OxfordClientBase implements IOxfordClient {

    public List<DictionaryEntry> getTranslations(Collection<String> words, String source, String target)
            throws OxfordClientException {
        List<DictionaryEntry> dictionaryEntries = new ArrayList<>();
        try {
            queryTranslations(words, source, target).entrySet()
                    .forEach(entry -> {
                        for (Result result: entry.getValue()) {
                            for (LexicalEntry lexicalEntry: result.getLexicalEntries()) {
                                dictionaryEntries.add(new DictionaryEntry(
                                        entry.getKey(),
                                        lexicalEntry.getText(),
                                        HTMLFormatter.formatDefinitions(lexicalEntry)));
                            }
                        }
                    });
        } catch (OxfordClientException e) {
            return Collections.emptyList();
        }
        return dictionaryEntries;
    }

    public List<DictionaryEntry> getDefinitions(final Collection<String> words, final String language,
                                                 final boolean strict) throws OxfordClientException {
        List<DictionaryEntry> dictionaryEntries = new ArrayList<>();
        try {
            queryEntries(words, language, strict).entrySet()
                    .forEach(entry -> {
                        for (Result result: entry.getValue()) {
                            for (LexicalEntry lexicalEntry: result.getLexicalEntries()) {
                                dictionaryEntries.add(new DictionaryEntry(
                                        entry.getKey(),
                                        lexicalEntry.getText(),
                                        HTMLFormatter.formatDefinitions(lexicalEntry)));
                            }
                        }
                    });
        } catch (OxfordClientException e) {
            return Collections.emptyList();
        }
        return dictionaryEntries;
    }

}
