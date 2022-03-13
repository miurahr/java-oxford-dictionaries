package tokyo.northside.oxfordapi;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RequestFactory {

    public enum QueryType {
        /** entries endpoint. */
        ENTRIES,
        /** translations endpoint. */
        TRANSLATIONS,
    }

    private static final String ENDPOINT_URL = "https://od-api.oxforddictionaries.com";
    private static final String BASE_PATH = "/api/v2/";
    private final String appId;
    private final String appKey;

    private QueryType type;
    private String queryWord;
    private String sourceLang;
    private String targetLang;
    private String strictMatch;
    /* fields can be a combination of followings;
            - definitions
            - domains
            - etymologies
            - examples
            - pronunciations
            - regions
            - registers
            - variantForms
     */
    private Set<String> fields;

    public RequestFactory(final String appId, final String appKey) {
        this.appId = appId;
        this.appKey = appKey;
        strictMatch = "false";
        sourceLang = "en-gb";
        type = QueryType.ENTRIES;
        fields = Collections.singleton("definitions");
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

    public RequestFactory setFields(final Set<String> newFields) {
        this.fields = newFields;
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

    public String getPath() throws OxfordClientException {
        if (queryWord == null) {
            throw new OxfordClientException("Query word is mandatory");
        }
        switch (type) {
            case ENTRIES:
                return String.format("%sentries/%s/%s", BASE_PATH, sourceLang, escapeString(queryWord));
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
        StringBuilder sb = new StringBuilder();
        switch (type) {
            case ENTRIES:
                sb.append("strictMatch=").append(strictMatch);
                break;
            case TRANSLATIONS:
                if (queryWord == null) {
                    throw new OxfordClientException("Query word is mandatory");
                }
                sb.append("q=").append(escapeString(queryWord));
                break;
            default:
                return null;
        }
        if (fields != null) {
            sb.append("&fields=").append(parameterToString(fields));
        }
        return sb.toString();
    }

    public String getUrl() throws OxfordClientException {
        if (queryWord == null) {
            throw new OxfordClientException("Query word is mandatory");
        }
        return String.format("%s%s?%s", ENDPOINT_URL, getPath(), getQueryString());
    }

    public String getQueryWord() {
        return queryWord;
    }

    /**
     * Format the given parameter object into string.
     * @param param Object
     * @return Object in string format
     */
    private String parameterToString(final Collection<String> param) {
        if (param == null) {
            return "";
        } else {
            StringBuilder b = new StringBuilder();
            for (String o : param) {
                if (b.length() > 0) {
                    b.append(',');
                }
                b.append(o);
            }
            return b.toString();
        }
    }

    /**
     * Escape the given string to be used as URL query value.
     * @param str String
     * @return Escaped string
     */
    private String escapeString(final String str) {
        try {
            return URLEncoder.encode(str, "utf8").replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException e) {
            return str;
        }
    }

}
