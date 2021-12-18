# Java Oxford Dictionaries

Java client for the Oxford Dictionaries API (herebyafter calls the OD API).

This projects aims to facilitate the interaction with the Oxford Dictionaries API.
A complete documentation for the OD API can be reached at:
https://developer.oxforddictionaries.com/documentation.

A development status is considered `ALPHA`.
A programming interface will be changed.

When you want to use complete implementation of the OD API client on JVM,
I recommend [Kotlin-oxford-dictionaries library](https://search.maven.org/artifact/com.github.sparkmuse/kotlin-oxford-dictionaries) 
for productions.

## Supported endpoints

The client supports basic two endpoints of the OD API; `entries` and `translations`.

<details>
<summary>endpoints</summary>
<p>

| Api                                                                              	| Supported? 	|
|----------------------------------------------------------------------------------	|:----------:	|
| /api/v2/entries/{source_lang}/{word_id}:                                         	|      ✅     	|
| /api/v2/lemmas/{source_lang}/{word_id}:                                          	|           	|
| /api/v2/translations/{source_lang_translate}/{target_lang_translate}/{word_id}:  	|      ✅     	|
| /api/v2/thesaurus/{lang}/{word_id}:                                              	|            	|
| /api/v2/sentences/{source_lang}/{word_id}:                                       	|           	|
| /api/v2/words/{source_lang}:                                                     	|           	|
| /api/v2/inflections/{source_lang}/{word_id}:                                    	|           	|
| __Search__                                                                      	|            	|
| /api/v2/search/translations/{source_lang_search}/{target_lang_search}:           	|           	|
| /api/v2/search/{source_lang}:                                                    	|           	|
| /api/v2/search/thesaurus/{source_lang}                                           	|           	|
| __Utility__                                                                      	|            	|
| /api/v2/domains/{source_lang}:                                                   	|           	|
| /api/v2/domains/{source_lang_domains}/{target_lang_domains}:                     	|           	|
| /api/v2/fields:                                                                  	|           	|
| /api/v2/fields/{endpoint}:                                                       	|           	|
| /api/v2/filters:                                                                 	|           	|
| /api/v2/filters/{endpoint}:                                                      	|           	|
| /api/v2/grammaticalFeatures/{source_lang}:                                       	|           	|
| /api/v2/grammaticalFeatures/{source_lang_grammatical}/{target_lang_grammatical}: 	|           	|
| /api/v2/languages:                                                               	|           	|
| /api/v2/lexicalCategories/{source_lang}:                                         	|           	|
| /api/v2/lexicalCategories/{source_lang_lexical}/{target_lang_lexical}:           	|           	|
| /api/v2/registers/{source_lang}:                                                 	|           	|
| /api/v2/registers/{source_lang_registers}/{target_lang_registers}:               	|           	|

</p>
</details>

## Install

All needed to start using the project is to add the dependency

**Maven**
<details><p>

```xml
<dependency>
  <groupId>tokyo.northside</groupId>
  <artifactId>java-oxford-dictionaries</artifactId>
  <version>0.3.0</version>
</dependency>
```

</p></details>

**Gradle Kotlin DSL**
```console
implementation("tokyo.northside:java-oxford-dictionaries:0.3.0")
```

**Gradle**
```console
implementation 'tokyo.northside:java-oxford-dictionaries:0.3.0'
```

## Authentication App Key and Id

Oxford Dictionaries comes with three price tiers: Prototype, Developer and Research. A key can be obtained by following
the link [https://developer.oxforddictionaries.com/?tag=#plans](https://developer.oxforddictionaries.com/?tag=#plans).

Use the **AppId** and **AppKey**  when creating the client.

## Usage in Java application

1. Get definitions in HTML with threading query


<details>
<p>

```java
class Main {
     public static String getDefinitions() {
         String appId = System.getenv("APP_ID");
         String appKey = System.getenv("APP_KEY");
         String lang = "en-gb";
         boolean strictMatch = false;
         Collection<String> words = Arrays.asList("ace", "alpha");
         //
         OxfordThreadClient oxfordThreadClient = new OxfordThreadClient(appId, appkey);
         List<OxfordDictionaryEntry> result = oxfordThreadClient.getDefinitions(words, lang, strictMatch);
         //
         return result.stream()
                 .map(res -> res.getWord() + ": " + res.getArticle())
                 .collect(Collectors.joining("<br/>"));
     }
 }
```

</p>
</details>

2. query definition, and return HTML format of definitions.

<details>
<p>

```java
class Main {
 public static String getDefinitions() {
  String appId = System.getenv("APP_ID");
  String appKey = System.getenv("APP_KEY");
  String baseUrl = "https://od-api.oxforddictionaries.com/api/v2";
  String lang = "en-gb";
  boolean strictMatch = false;
  String word = "ace";
  //
  OxfordClient oxfordClient = new OxfordClient(appId, appKey, baseUrl);
  List<Result> results = oxfordClient.queryEntries(word, lang, strictMatch);
  //
  Result result = results.get(0);
  assert(result.getId().equals(word));
  //
  StringBuilder sb = new StringBuilder();
  List<LexicalEntry> lexicalEntries = result.getLExicalEntries();
  String title = lexicalEntry.getText();
  sb.append("<h3>").append(title).append("</h3>");
  for (LexicalEntry lexicalEntry: lexicalEntries) {
   sb.append("<ol>");
   for (Entry entry : lexicalEntry.getEntries()) {
    for (Sense sense : entry.getSenses()) {
     if (sense.getDefinitions() == null) {
      continue;
     }
     for (String text : sense.getDefinitions()) {
      sb.append("<li>").append(text).append("</li>");
     }
    }
   }
   sb.append("</ol>");
  }
  return sb.toString();
 }
}
```

</p></details>

3. query translations in French.

<details>
<p>

```java
import java.util.ArrayList;

class Main {
 public static List<String> getTranslations() {
  String appId = System.getenv("APP_ID");
  String appKey = System.getenv("APP_KEY");
  String baseUrl = "https://od-api.oxforddictionaries.com/api/v2";
  String source = "en-gb";
  String target = "fr";
  String word = "ace";
  //
  OxfordClient oxfordClient = new OxfordClient(appId, appKey, baseUrl);
  List<Result> results = oxfordClient.queryTranslations(word, source, target);
  //
  Result result = results.get(0);
  assert (result.getId().equals(word));
  //
  List<LexialEntry> lexicalEntries = result.getLexicalEntries();
  List<Entry> entries = lexicalEntries.get(0).getEntries();
  List<Sense> senses = entries.get(0).getSenses();
  List<Translation> translations = senses.get(0).getTranslations();
  //
  List<String> out = new ArrayList<>();
  for (Translation translation : translations) {
        out.add(translation.getText());
  }
  return out;
 }
}
```

</p>
</details>

4. generic query programming interface.

<details><p>

```java
class Main {
    public static void main() {
        OxfordClient oxfordClient = new OxfordClient(appId, appKey, baseUrl);
        RequestFactory f = new RequestFactory(appId, appKey, baseUrl);
        f.setQueryWord("ace");
        List<Result> results = oxfordClient.query(f.getURL(), f.getHeader());
    }
}
```

</p></details>