package tokyo.northside.oxfordapi;

import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import tokyo.northside.oxfordapi.dtd.LexicalEntry;
import tokyo.northside.oxfordapi.dtd.Result;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class OxfordClientBase implements IOxfordClient {

    public List<OxfordDictionaryEntry> getTranslations(final Collection<String> words, final String source,
                                                       final String target) throws OxfordClientException {
        List<OxfordDictionaryEntry> dictionaryEntries = new ArrayList<>();
        queryTranslations(words, source, target).entrySet()
                .forEach(entry -> {
                    for (Result result: entry.getValue()) {
                        for (LexicalEntry lexicalEntry: result.getLexicalEntries()) {
                            dictionaryEntries.add(new OxfordDictionaryEntry(
                                    entry.getKey(),
                                    lexicalEntry.getText(),
                                    HTMLFormatter.formatDefinitions(lexicalEntry)));
                        }
                    }
                });
        return dictionaryEntries;
    }

    public List<OxfordDictionaryEntry> getDefinitions(final Collection<String> words, final String language,
                                                      final boolean strict) throws OxfordClientException {
        List<OxfordDictionaryEntry> dictionaryEntries = new ArrayList<>();
        queryEntries(words, language, strict).entrySet()
                .forEach(entry -> {
                    for (Result result: entry.getValue()) {
                        for (LexicalEntry lexicalEntry: result.getLexicalEntries()) {
                            dictionaryEntries.add(new OxfordDictionaryEntry(
                                    entry.getKey(),
                                    lexicalEntry.getText(),
                                    HTMLFormatter.formatDefinitions(lexicalEntry)));
                        }
                    }
                });
        return dictionaryEntries;
    }

    protected static final HttpClientResponseHandler<String> RESPONSE_HANDLER = response -> {
        final int status = response.getCode();
        if (status >= HttpStatus.SC_SUCCESS && status < HttpStatus.SC_REDIRECTION) {
            try (HttpEntity entity = response.getEntity()) {
                if (entity != null) {
                    return EntityUtils.toString(entity);
                } else {
                    return null;
                }
            } catch (final ParseException ex) {
                throw new ClientProtocolException(ex);
            }
        } else {
            throw new ClientProtocolException(String.format("Unexpected response status: %d", status));
        }
    };
}
