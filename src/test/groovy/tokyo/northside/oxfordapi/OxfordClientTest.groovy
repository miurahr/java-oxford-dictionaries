package tokyo.northside.oxfordapi

import org.junit.Test
import tokyo.northside.oxfordapi.dtd.Result

import static org.junit.Assert.assertEquals

class OxfordClientTest {
    def ENDPOINT_URL = "https://od-api.oxforddictionaries.com/"
    def appId = System.properties.getProperty("oxfordId")
    def appKey = System.properties.getProperty("oxfordKey")

    @Test
    void simpleQueryTest() {
        OxfordClient client = new OxfordClient(appId, appKey, ENDPOINT_URL)
        List<Result> result = client.queryEntry("ace", "en-GB", true)
        assertEquals(2, result.size())
        client.close()
    }

    @Test
    void queryResultTest() {
        OxfordClient client = new OxfordClient(appId, appKey, ENDPOINT_URL)
        List<OxfordDictionaryEntry> result = client.getDefinitions(Collections.singletonList("ace"), "en-GB", true)
        assertEquals(5, result.size())
        client.close()
    }

}
