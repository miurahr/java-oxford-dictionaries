package tokyo.northside.oxfordapi

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.commons.io.IOUtils
import org.junit.Test
import tokyo.northside.oxfordapi.dtd.Result

import static org.junit.Assert.*


class OxfordDictionariesTranslationsTest {

    @Test
    void testTranslationsParseResult1() {
        InputStream resource = OxfordClient.class.getClassLoader()
                .getResourceAsStream("oxfordapi/results/translation_result1.json")
        def json = IOUtils.toString(resource, "UTF-8")
        ObjectMapper mapper = new ObjectMapper()
        JsonNode node = mapper.readTree(json)
        node = node.get("results")
        def results = mapper.readValue(node.traverse(), new TypeReference<List<Result>>() {})
        def result = results.get(0)
        assertEquals("ace", result.getId())
        def lexicalEntries = result.getLexicalEntries()
        assertEquals("en", lexicalEntries.get(0).getLanguage())
        assertEquals("ace", lexicalEntries.get(0).getText())
        def entries = lexicalEntries.get(0).getEntries()
        assert(entries.size() == 1)
        def senses = entries.get(0).getSenses()
        assert(senses.size() == 3)
        assertEquals("b-en-es0000347.002", senses.get(0).getId())
        def translations = senses.get(0).getTranslations()
        assert(translations.size() == 1)
        assertEquals("as", translations.get(0).getText())
    }

    @Test
    void testTranslationsParseModel() {
        InputStream resource = OxfordClient.class.getClassLoader()
                .getResourceAsStream("oxfordapi/models/translation_model.json")
        String json = IOUtils.toString(resource, "UTF-8")
        ObjectMapper mapper = new ObjectMapper()
        JsonNode node = mapper.readTree(json)
        node = node.get("results")
        def results = mapper.readValue(node.traverse(), new TypeReference<List<Result>>() {})
        Result result = results.get(0)
        assertEquals("string", result.getId())
    }
}
