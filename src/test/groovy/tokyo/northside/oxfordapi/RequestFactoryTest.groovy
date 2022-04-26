package tokyo.northside.oxfordapi

import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertEquals


class RequestFactoryTest {

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
        RequestFactory f = new RequestFactory(appId, appKey)
            .setType(RequestFactory.QueryType.TRANSLATIONS)
            .setSourceLanguage(source)
            .setTargetLanguage(target)
            .setFields(fields)
            .setQueryWord(word)
        assertEquals("https://od-api.oxforddictionaries.com/api/v2/translations/en-gb/fr?q=ace&fields=definitions,pronunciations", f.getUrl())
    }

}
