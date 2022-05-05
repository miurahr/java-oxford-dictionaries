package tokyo.northside.oxfordapi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import tokyo.northside.oxfordapi.dtd.Result;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Oxford dictionaries API access client.
 *
 * @author Hiroshi Miura
 */
public class OxfordClient extends OxfordClientBase implements IOxfordClient {
    private final String appId;
    private final String appKey;

    /**
     * Constructor with baseURL.
     *
     * @param appId AppId of the OD API credentials.
     * @param appKey AppKey of the OD API credentials.
     */
    public OxfordClient(final String appId, final String appKey) {
        this.appId = appId;
        this.appKey = appKey;
    }

    @Override
    public Map<String, List<Result>> queryTranslations(final Collection<String> words, final String source,
                                                       final String target) throws OxfordClientException {
        Map<String, List<Result>> result = new HashMap<>();
        for (String word : words) {
            result.put(word, queryTranslation(word, source, target));
        }
        return result;
    }

    /**
     * Get result of `translations` endpoint query.
     *
     * @param word query word
     * @param source source language in lower case, such as "en-gb", "en-us", and "fr"
     * @param target target language in lower case
     * @return List of Result object.
     * @throws OxfordClientException when connection or parse error occurred
     */
    @Override
    public List<Result> queryTranslation(final String word, final String source, final String target)
            throws OxfordClientException {
        Set<String> fields = new HashSet<>(Arrays.asList("definitions", "pronunciations"));
        RequestFactory f = new RequestFactory(appId, appKey)
                .setType(RequestFactory.QueryType.TRANSLATIONS)
                .setSourceLanguage(source)
                .setTargetLanguage(target)
                .setFields(fields)
                .setQueryWord(word);
        return query(f.getUrl(), f.getHeader());
    }

    @Override
    public Map<String, List<Result>> queryEntries(final Collection<String> words, final String language,
                                                  final boolean strict) throws OxfordClientException {
        Map<String, List<Result>> result = new HashMap<>();
        for (String word : words) {
            result.put(word, queryEntry(word, language, strict));
        }
        return result;
    }

    /**
     * Get result of `entries` endpoint query.
     *
     * @param word query word
     * @param language query language in lower case, such as "en-gb", "en-us", and "fr"
     * @param strict boolean whether match is strict
     * @return List of Result object.
     * @throws OxfordClientException when connection or parse error occurred
     */
    @Override
    public List<Result> queryEntry(final String word, final String language, final boolean strict)
            throws OxfordClientException {
        RequestFactory f = new RequestFactory(appId, appKey)
                .setType(RequestFactory.QueryType.ENTRIES)
                .setLanguage(language)
                .setQueryWord(word)
                .setFields(Collections.singleton("definitions"))
                .setStrictMatch(strict);
        return query(f.getUrl(), f.getHeader());
    }

    @Override
    public void close() {

    }

    /**
     * Oxford dictionaries API query method.
     *
     * @param requestUrl request URL returned from RequestFactory.getURL()
     * @param header header parameters returned from ReqeustFactory.getHeader()
     * @return List of Result objects.
     * @throws OxfordClientException when connection or parse error occurred
     */
    public List<Result> query(final String requestUrl, final Map<String, Object> header) throws OxfordClientException {
        String response;
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(requestUrl);
            header.forEach(httpGet::addHeader);
            try (CloseableHttpResponse httpResponse = httpclient.execute(httpGet)) {
                int status = httpResponse.getCode();
                if (status == HttpStatus.SC_TOO_MANY_REQUESTS) {
                    throw new OxfordClientException("Got 429 Too many requests, Do you exceed your limit?");
                }
                if (status == HttpStatus.SC_FORBIDDEN) {
                    throw new OxfordClientException("Authorization failed.");
                }
                if (status != HttpStatus.SC_NOT_FOUND) {
                    if (status != HttpStatus.SC_OK) {
                        throw new OxfordClientException(String.format("Unexpected status %d.", status));
                    }
                    response = EntityUtils.toString(httpResponse.getEntity(), StandardCharsets.UTF_8);
                } else {
                    return Collections.emptyList();
                }
            }
        } catch (IOException | ParseException e) {
            throw new OxfordClientException(e.getMessage());
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(response);
            node = node.get("results");
            return mapper.readValue(node.traverse(), new TypeReference<List<Result>>() { });
        } catch (IOException e) {
            throw new OxfordClientException(e.getMessage());
        }
    }
}
