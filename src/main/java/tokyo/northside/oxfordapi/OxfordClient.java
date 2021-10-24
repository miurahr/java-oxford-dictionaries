package tokyo.northside.oxfordapi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import tokyo.northside.oxfordapi.dtd.Result;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Oxford dictionaries API access client.
 *
 * @author Hiroshi Miura
 */
public final class OxfordClient {

    private static final String BASE_URL_V2 = "https://od-api.oxforddictionaries.com/api/v2";
    private final String endpointUrl;
    private final String appId;
    private final String appKey;

    /**
     * Constructor with baseURL.
     *
     * @param appId AppId of the OD API credentials.
     * @param appKey AppKey of the OD API credentials.
     * @param baseUrl base URL of the OD API v2.
     */
    public OxfordClient(final String appId, final String appKey, final String baseUrl) {
        this.appId = appId;
        this.appKey = appKey;
        endpointUrl = baseUrl;
    }

    /**
     * Construcotr with default v2 URL.
     *
     * @param appId AppId of the OD API credentials.
     * @param appKey AppKey of the OD API credentials.
     */
    public OxfordClient(final String appId, final String appKey) {
        this(appId, appKey, BASE_URL_V2);
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
    public List<Result> getTranslations(final String word, final String source, final String target)
            throws OxfordClientException {
        RequestFactory f = new RequestFactory(appId, appKey, endpointUrl);
        f.setType(RequestFactory.QueryType.TRANSLATIONS);
        f.setSourceLanguage(source);
        f.setTargetLanguage(target);
        f.setQueryWord(word);
        return query(f.getUrl(), f.getHeader());
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
    public List<Result> getEntries(final String word, final String language, final boolean strict)
            throws OxfordClientException {
        RequestFactory f = new RequestFactory(appId, appKey, endpointUrl);
        f.setType(RequestFactory.QueryType.ENTRIES);
        f.setLanguage(language);
        f.setQueryWord(word);
        f.setStrictMatch(strict);
        return query(f.getUrl(), f.getHeader());
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
            response = httpclient.execute(httpGet, RESPONSE_HANDLER);
        } catch (IOException e) {
            throw new OxfordClientException(e.getMessage());
        }
        if (response != null) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode node = mapper.readTree(response);
                node = node.get("results");
                return mapper.readValue(node.traverse(), new TypeReference<List<Result>>() { });
            } catch (IOException e) {
                throw new OxfordClientException(e.getMessage());
            }
        }
        return Collections.emptyList();
    }

    private static final HttpClientResponseHandler<String> RESPONSE_HANDLER = response -> {
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
