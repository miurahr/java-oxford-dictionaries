package tokyo.northside.oxfordapi

import org.junit.Test

import static org.junit.Assert.assertEquals

class OxfordAsyncClientTest {
    def appId = System.properties.getProperty("oxfordId")
    def appKey = System.properties.getProperty("oxfordKey")

    @Test
    void simpleAsyncQueryTest() {
        IOxfordClient client = new OxfordAsyncClient(appId, appKey)
        def result = client.queryEntries(Collections.singletonList("ace"), "en-GB", true)
        assertEquals(1, result.size())
        client.close()
    }

}
