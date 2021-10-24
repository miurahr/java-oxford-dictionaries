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

    private final String endpointUrl;
    private final String appId;
    private final String appKey;

    private QueryType type;
    private String queryWord;
    private String sourceLang;
    private String targetLang;
    private String strictMatch;

    public RequestFactory(final String endpointUrl, final String appId, final String appKey) {
        this.endpointUrl = endpointUrl;
        this.appId = appId;
        this.appKey = appKey;
        type = QueryType.ENTRIES;
    }

    public void setType(final QueryType type) {
        this.type = type;
    }

    public void setQueryWord(final String word) {
        queryWord = word.toLowerCase();
    }

    public void setLanguage(final String language) {
        if (language.equalsIgnoreCase("en")) {
            sourceLang = "en-gb";
        } else {
            sourceLang = language.toLowerCase();
        }
    }

    public void setSourceLanguage(final String language) {
        if (language.equalsIgnoreCase("en")) {
            sourceLang = "en-gb";
        } else {
            sourceLang = language.toLowerCase();
        }
    }

    public void setTargetLanguage(final String language) {
        if (language.equalsIgnoreCase("en")) {
            targetLang = "en-gb";
        } else {
            targetLang = language.toLowerCase();
        }
    }

    public void setStrictMatch(final boolean strict) {
        if (strict) {
            strictMatch = "true";
        } else {
            strictMatch = "false";
        }
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

    public String getUrl() {
        switch (type) {
            case ENTRIES:
                return String.format("%sentries/%s/%s?strictMatch=%s", endpointUrl, sourceLang, queryWord, strictMatch);
            case TRANSLATIONS:
                return String.format("%stranslations/%s/%s?q=%s", endpointUrl, sourceLang, targetLang, queryWord);
            default:
                return null;
        }
    }

}
