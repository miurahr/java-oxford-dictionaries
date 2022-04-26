package tokyo.northside.oxfordapi

import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertEquals

class OxfordThreadClientTest {
    def appId = System.properties.getProperty("oxfordId")
    def appKey = System.properties.getProperty("oxfordKey")

    @Test
    void simpleThreadQueryTest() {
        IOxfordClient client = new OxfordThreadClient(appId, appKey)
        def result = client.queryEntries(Arrays.asList("ace", "alpha"), "en-GB", true)
        assertEquals(2, result.size())
        result = client.queryEntries(Arrays.asList("best", "beta"), "en-GB", true)
        assertEquals(2, result.size())
        client.close()
    }
}
