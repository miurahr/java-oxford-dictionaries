package tokyo.northside.oxfordapi

import org.apache.commons.lang3.StringUtils
import org.junit.jupiter.api.Assumptions
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertEquals

class OxfordThreadClientTest {
    def appId = System.properties.getProperty("oxfordId")
    def appKey = System.properties.getProperty("oxfordKey")

    @Test
    void simpleThreadQueryTest() {
        Assumptions.assumeFalse(StringUtils.isBlank(appId))
        IOxfordClient client = new OxfordThreadClient(appId, appKey)
        def result = client.queryEntries(Arrays.asList("ace", "alpha"), "en-GB", true)
        assertEquals(2, result.size())
        result = client.queryEntries(Arrays.asList("best", "beta"), "en-GB", true)
        assertEquals(2, result.size())
        client.close()
    }
}
