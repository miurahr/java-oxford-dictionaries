package tokyo.northside.oxfordapi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.async.methods.*;
import org.apache.hc.client5.http.impl.async.HttpAsyncClients;
import org.apache.hc.client5.http.impl.async.MinimalHttpAsyncClient;
import org.apache.hc.core5.concurrent.FutureCallback;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.http.config.Http1Config;
import org.apache.hc.core5.http.message.StatusLine;
import org.apache.hc.core5.http.nio.AsyncClientEndpoint;
import org.apache.hc.core5.http2.HttpVersionPolicy;
import org.apache.hc.core5.http2.config.H2Config;
import org.apache.hc.core5.io.CloseMode;
import org.apache.hc.core5.reactor.IOReactorConfig;
import org.apache.hc.core5.util.Timeout;
import tokyo.northside.oxfordapi.dtd.Result;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class OxfordAsyncClient implements IOxfordClient {

    private static final String OD_API_HOST = "od-api.oxforddictionaries.com";
    private static final String ENDPOINT_URL = "https://od-api.oxforddictionaries.com/api/v2";
    private final IOReactorConfig ioReactorConfig;
    private final MinimalHttpAsyncClient client;
    private final ObjectMapper mapper;
    private Map<String, List<Result>> articles;

    private final String appId;
    private final String appKey;

    public OxfordAsyncClient(final String appId, final String appKey) {
        this.appId = appId;
        this.appKey = appKey;
        ioReactorConfig = IOReactorConfig.custom()
                .setSoTimeout(Timeout.ofSeconds(5))
                .build();
        client = HttpAsyncClients.createMinimal(
                HttpVersionPolicy.NEGOTIATE,
                H2Config.DEFAULT,
                Http1Config.DEFAULT,
                ioReactorConfig);
        client.start();
        mapper = new ObjectMapper();
    }

    public Map<String, List<Result>> getEntries(Collection<String> words, final String language, boolean strict)
            throws OxfordClientException {
        final HttpHost target = new HttpHost(OD_API_HOST);
        final Future<AsyncClientEndpoint> leaseFuture = client.lease(target, null);
        final AsyncClientEndpoint endpoint;
        try {
            endpoint = leaseFuture.get(30, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new OxfordClientException(e.getMessage());
        }
        final CountDownLatch latch = new CountDownLatch(words.size());
        RequestFactory factory = new RequestFactory(appId, appKey, ENDPOINT_URL);
        for (final String query: words) {
            factory.setQueryWord(query);
            factory.setLanguage(language);
            factory.setStrictMatch(strict);
            final SimpleHttpRequest request = SimpleRequestBuilder.get()
                    .setHttpHost(target)
                    .setHeader(HttpHeaders.ACCEPT, "application/json")
                    .setHeader("app_id", appId)
                    .setHeader("app_key", appKey)
                    .setUri(factory.getUri())
                    .build();
            endpoint.execute(
                    SimpleRequestProducer.create(request),
                    SimpleResponseConsumer.create(),
                    new FutureCallback<SimpleHttpResponse>() {
                        @Override
                        public void completed(final SimpleHttpResponse result) {
                            latch.countDown();
                            StatusLine statusLine = new StatusLine(result);
                            if (statusLine.isSuccessful()) {
                                articles = parseResponse(query, result.getBodyText());
                            }
                        }

                        @Override
                        public void failed(final Exception ex) {
                            latch.countDown();
                        }

                        @Override
                        public void cancelled() {
                            latch.countDown();
                        }
                    }
            );
        }
        return articles;
    }

    private Map<String, List<Result>> parseResponse(final String word, final String json) {
        Map<String, List<Result>> articles = new HashMap<>();
        try {
            JsonNode node = mapper.readTree(json);
            articles.put(word, mapper.readValue(node.get("results").traverse(), new TypeReference<List<Result>>() { }));
        } catch (IOException ignored) {
        }
        return articles;
    }

    @Override
    public Map<String, List<Result>> getTranslations(final Collection<String> words, final String source,
                                                     final String target) throws OxfordClientException {
        final HttpHost httpHost = new HttpHost(OD_API_HOST);
        final Future<AsyncClientEndpoint> leaseFuture = client.lease(httpHost, null);
        final AsyncClientEndpoint endpoint;
        try {
            endpoint = leaseFuture.get(30, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new OxfordClientException(e.getMessage());
        }
        final CountDownLatch latch = new CountDownLatch(words.size());
        RequestFactory factory = new RequestFactory(appId, appKey, ENDPOINT_URL);
        for (final String query: words) {
            factory.setQueryWord(query);
            factory.setSourceLanguage(source);
            factory.setTargetLanguage(target);
            final SimpleHttpRequest request = SimpleRequestBuilder.get()
                    .setHttpHost(httpHost)
                    .setHeader(HttpHeaders.ACCEPT, "application/json")
                    .setHeader("app_id", appId)
                    .setHeader("app_key", appKey)
                    .setUri(factory.getUri())
                    .build();
            endpoint.execute(
                    SimpleRequestProducer.create(request),
                    SimpleResponseConsumer.create(),
                    new FutureCallback<SimpleHttpResponse>() {
                        @Override
                        public void completed(final SimpleHttpResponse result) {
                            latch.countDown();
                            StatusLine statusLine = new StatusLine(result);
                            if (statusLine.isSuccessful()) {
                                articles = parseResponse(query, result.getBodyText());
                            }
                        }

                        @Override
                        public void failed(final Exception ex) {
                            latch.countDown();
                        }

                        @Override
                        public void cancelled() {
                            latch.countDown();
                        }
                    }
            );
        }
        return articles;
    }

    @Override
    public List<Result> getTranslations(final String word, final String source, final String target) throws OxfordClientException {
        return null;
    }

    @Override
    public List<Result> getEntries(final String word, final String language, final boolean strict) throws OxfordClientException {
        return null;
    }

    public void close() {
        client.close(CloseMode.GRACEFUL);
    }
}