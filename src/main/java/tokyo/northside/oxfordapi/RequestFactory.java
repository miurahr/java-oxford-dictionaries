package tokyo.northside.oxfordapi;

import java.util.HashMap;
import java.util.Map;

public class RequestFactory {

    public enum QueryType {
        /** entries endpoint. */
        ENTRIES,
        /** translations endpoint. */
        TRANSLATIONS,
    }

    private static final String BASE_PATH = "/api/v2";
    private final String endpointUrl;
    private final String appId;
    private final String appKey;

    private QueryType type;
    private String queryWord;
    private String sourceLang;
    private String targetLang;
    private String strictMatch;

    public RequestFactory(final String appId, final String appKey, final String endpointUrl) {
        this.endpointUrl = endpointUrl;
        this.appId = appId;
        this.appKey = appKey;
        strictMatch = "false";
        sourceLang = "en-gb";
        type = QueryType.ENTRIES;
    }

    public RequestFactory setType(final QueryType queryType) {
        this.type = queryType;
        return this;
    }

    public RequestFactory setQueryWord(final String word) {
        queryWord = word.toLowerCase();
        return this;
    }

    public RequestFactory setLanguage(final String language) {
        if (language.equalsIgnoreCase("en")) {
            sourceLang = "en-gb";
        } else {
            sourceLang = language.toLowerCase();
        }
        return this;
    }

    public RequestFactory setSourceLanguage(final String language) {
        if (language.equalsIgnoreCase("en")) {
            sourceLang = "en-gb";
        } else {
            sourceLang = language.toLowerCase();
        }
        return this;
    }

    public RequestFactory setTargetLanguage(final String language) {
        if (language.equalsIgnoreCase("en")) {
            targetLang = "en-gb";
        } else {
            targetLang = language.toLowerCase();
        }
        return this;
    }

    public RequestFactory setStrictMatch(final boolean strict) {
        if (strict) {
            strictMatch = "true";
        } else {
            strictMatch = "false";
        }
        return this;
    }

    /**
     * Get standard header for the OD API.
     *
     * @return Map of header parameters that can be used for the query method.
     */
    public Map<String, Object> getHeader() {
        Map<String, Object> header = new HashMap<>();
        header.put("Accept", "application/json");
        header.put("app_id", appId);
        header.put("app_key", appKey);
        return header;
    }

    public String getUri() throws OxfordClientException {
        if (queryWord == null) {
            throw new OxfordClientException("Query word is mandatory");
        }
        switch (type) {
            case ENTRIES:
                return String.format("%sentries/%s/%s?strictMatch=%s", BASE_PATH, sourceLang, queryWord, strictMatch);
            case TRANSLATIONS:
                if (targetLang == null) {
                    throw new OxfordClientException("Source and Target languages are mandatory");
                }
                return String.format("%stranslations/%s/%s?q=%s", BASE_PATH, sourceLang, targetLang, queryWord);
            default:
                return null;
        }
    }

    public String getPath() throws OxfordClientException {
        if (queryWord == null) {
            throw new OxfordClientException("Query word is mandatory");
        }
        switch (type) {
            case ENTRIES:
                return String.format("%sentries/%s/%s", BASE_PATH, sourceLang, queryWord);
            case TRANSLATIONS:
                if (targetLang == null) {
                    throw new OxfordClientException("Source and Target languages are mandatory");
                }
                return String.format("%stranslations/%s/%s", BASE_PATH, sourceLang, targetLang);
            default:
                return null;
        }
    }

    public String getQueryString() throws OxfordClientException {
        switch (type) {
            case ENTRIES:
                return String.format("strictMatch=%s", strictMatch);
            case TRANSLATIONS:
                if (queryWord == null) {
                    throw new OxfordClientException("Query word is mandatory");
                }
                return String.format("?q=%s", queryWord);
            default:
                return null;
        }
    }

    public String getUrl() throws OxfordClientException {
        if (queryWord == null) {
            throw new OxfordClientException("Query word is mandatory");
        }
        switch (type) {
            case ENTRIES:
                return String.format("%sentries/%s/%s?strictMatch=%s", endpointUrl, sourceLang, queryWord, strictMatch);
            case TRANSLATIONS:
                if (targetLang == null) {
                    throw new OxfordClientException("Source and Target languages are mandatory");
                }
                return String.format("%stranslations/%s/%s?q=%s", endpointUrl, sourceLang, targetLang, queryWord);
            default:
                return null;
        }
    }

    public String getQueryWord() {
        return queryWord;
    }

}
