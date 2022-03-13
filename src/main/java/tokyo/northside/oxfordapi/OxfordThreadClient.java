package tokyo.northside.oxfordapi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import tokyo.northside.oxfordapi.dtd.Result;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Oxford dictionaries Async API client.
 *
 * @author Hiroshi Miura
 */
public class OxfordThreadClient extends OxfordClientBase {
    private final String appId;
    private final String appKey;

    public OxfordThreadClient(final String appId, final String appKey) {
        this.appId = appId;
        this.appKey = appKey;
    }

    @Override
    public Map<String, List<Result>> queryEntries(final Collection<String> words, final String language,
                                                  final boolean strict) throws OxfordClientException {
        Map<String, List<Result>> result = new HashMap<>();
        try (PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
             CloseableHttpClient httpclient = HttpClients.custom()
                .setConnectionManager(cm)
                .build()) {
            final List<RequestFactory> requests = new ArrayList<>();
            for (final String query : words) {
                RequestFactory factory = new RequestFactory(appId, appKey);
                factory.setQueryWord(query).setLanguage(language).setStrictMatch(strict);
                requests.add(factory);
            }
            runThread(result, httpclient, requests);
        } catch (IOException | InterruptedException e) {
            throw new OxfordClientException(e.getMessage());
        }
        return result;
    }

    @Override
    public Map<String, List<Result>> queryTranslations(final Collection<String> words, final String source,
                                                       final String target) throws OxfordClientException {
        Map<String, List<Result>> result = new HashMap<>();
        try (PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
             CloseableHttpClient httpclient = HttpClients.custom()
                .setConnectionManager(cm)
                .build()) {
            List<RequestFactory> requests = new ArrayList<>();
            for (final String query : words) {
                RequestFactory factory = new RequestFactory(appId, appKey);
                factory.setQueryWord(query).setSourceLanguage(source).setTargetLanguage(target);
                requests.add(factory);
            }
            runThread(result, httpclient, requests);
        } catch (IOException | InterruptedException e) {
            throw new OxfordClientException(e.getMessage());
        }
        return result;
    }

    private void runThread(final Map<String, List<Result>> result, final CloseableHttpClient httpclient,
                           final List<RequestFactory> requests) throws OxfordClientException, InterruptedException {
        GetThread[] threads = new GetThread[requests.size()];
        for (int i = 0; i < threads.length; i++) {
            HttpGet httpGet = new HttpGet(requests.get(i).getUrl());
            requests.get(i).getHeader().forEach(httpGet::addHeader);
            threads[i] = new GetThread(httpclient, httpGet, requests.get(i).getQueryWord(), result, i + 1);
        }
        for (final GetThread thread : threads) {
            thread.start();
        }
        for (final GetThread thread : threads) {
            thread.join();
        }
    }

    @Override
    public void close() {
    }

    @Override
    public List<Result> queryTranslation(final String word, final String source, final String target)
            throws OxfordClientException {
        return queryTranslations(Collections.singletonList(word), source, target).get(word);
    }

    @Override
    public List<Result> queryEntry(final String word, final String language, final boolean strict)
            throws OxfordClientException {
        return queryEntries(Collections.singletonList(word), language, strict).get(word);
    }

    private static Map<String, List<Result>> parseResponse(final String word, final String json) {
        Map<String, List<Result>> articles = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode node = mapper.readTree(json);
            articles.put(word, mapper.readValue(node.get("results").traverse(), new TypeReference<List<Result>>() { }));
        } catch (IOException ignored) {
        }
        return articles;
    }

    /**
     * A thread that performs a GET.
     */
    static class GetThread extends Thread {

        private final CloseableHttpClient httpClient;
        private final HttpGet httpget;
        private final Map<String, List<Result>> result;
        private final String word;
        private final int id;

        GetThread(final CloseableHttpClient httpClient, final HttpGet httpget,
                         final String word, final Map<String, List<Result>> result, final int id) {
            this.httpClient = httpClient;
            this.httpget = httpget;
            this.word = word;
            this.result = result;
            this.id = id;
        }

        /**
         * Executes the GetMethod and prints some status information.
         */
        @Override
        public void run() {
            try {
                String response = httpClient.execute(httpget, RESPONSE_HANDLER);
                if (response != null) {
                    synchronized (result) {
                        result.putAll(parseResponse(word, response));
                    }
                }
            } catch (IOException e) {
                System.out.println(id + " - error: " + e);
                System.out.println(id + " - request: " + httpget.getRequestUri());
            }
        }
    }
}
