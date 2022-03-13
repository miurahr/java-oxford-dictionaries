package tokyo.northside.oxfordapi

import org.junit.Test

import static org.junit.Assert.assertEquals

class RequestFactoryTest {

    def BASE_URL = "https://od-api.oxforddictionaries.com/"

    @Test
    void requestFactoryUrlTest() {
        def appId = "abcdef"
        def appKey = "12345"
        def source = "en"
        def target = "fr"
        def word = "ace"
        def fields = new HashSet()
        fields.add("definitions")
        fields.add("pronunciations")
        RequestFactory f = new RequestFactory(appId, appKey, BASE_URL)
            .setType(RequestFactory.QueryType.TRANSLATIONS)
            .setSourceLanguage(source)
            .setTargetLanguage(target)
            .setFields(fields)
            .setQueryWord(word)
        assertEquals("https://od-api.oxforddictionaries.com/api/v2/translations/en-gb/fr?q=ace&fields=definitions,pronunciations", f.getUrl())
    }

}
