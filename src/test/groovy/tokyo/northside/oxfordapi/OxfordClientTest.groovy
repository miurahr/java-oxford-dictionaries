package tokyo.northside.oxfordapi

import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import tokyo.northside.oxfordapi.dtd.Result

import static org.junit.jupiter.api.Assertions.assertEquals

class OxfordClientTest {
    def appId = System.properties.getProperty("oxfordId")
    def appKey = System.properties.getProperty("oxfordKey")

    @Test
    void simpleQueryTest() {
        OxfordClient client = new OxfordClient(appId, appKey)
        List<Result> result = client.queryEntry("ace", "en-GB", true)
        assertEquals(2, result.size())
        client.close()
    }

    @Test
    void queryResultTest() {
        OxfordClient client = new OxfordClient(appId, appKey)
        List<OxfordDictionaryEntry> result = client.getDefinitions(Collections.singletonList("ace"), "en-GB", true)
        assertEquals(5, result.size())
        client.close()
    }

}
