package tokyo.northside.oxfordapi

import org.junit.Test
import tokyo.northside.oxfordapi.dtd.Result

import static org.junit.Assert.assertEquals

class OxfordAsyncClientTest {
    def appId = System.properties.getProperty("oxfordId")
    def appKey = System.properties.getProperty("oxfordKey")

    @Test
    void simpleAsyncQueryTest() {
        IOxfordClient client = new OxfordAsyncClient(appId, appKey)
        List<Result> result = client.queryEntries(Collections.singletonList("ace"), "en-GB", true)
        assertEquals(2, result.size())
        client.close()
    }

}
