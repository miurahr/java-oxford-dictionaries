# Java Oxford Dictionaries

Java client for the Oxford Dictionaries API.

This projects aims to facilitate the interaction with the Oxford Dictionaries API.
A complete documentation for the API can be reached at:
https://developer.oxforddictionaries.com/documentation.

When you want to use complete implemetation of Oxford Dictionaries API on JVM,
 I recommend [Kotlin-oxford-dictionaries library found at maven central](https://search.maven.org/artifact/com.github.sparkmuse/kotlin-oxford-dictionaries)

## Supported endpoints

The API currently supports just a small part of endpoints.
<details>
<summary>endpoints</summary>
<p>

| Api                                                                              	| Supported? 	|
|----------------------------------------------------------------------------------	|:----------:	|
| /api/v2/entries/{source_lang}/{word_id}:                                         	|      ✅     	|
| /api/v2/lemmas/{source_lang}/{word_id}:                                          	|           	|
| /api/v2/translations/{source_lang_translate}/{target_lang_translate}/{word_id}:  	|      ✅     	|
| /api/v2/thesaurus/{lang}/{word_id}:                                              	|           	|
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
```xml
<dependency>
  <groupId>tokyo.northside</groupId>
  <artifactId>java-oxford-dictionaries</artifactId>
  <version>0.1.0</version>
</dependency>
```

**Gradle Kotlin DSL**
```shell script
implementation("tokyo.northside:java-oxford-dictionaries:0.1.0")
```

**Gradle**
```shell script
implementation 'tokyo.northside:java-oxford-dictionaries:0.1.0'
```

## Authentication App Key and Id

Oxford Dictionaries comes with three price tiers: Prototype, Developer and Research. A key can be obtained by following
the link [https://developer.oxforddictionaries.com/?tag=#plans](https://developer.oxforddictionaries.com/?tag=#plans).

Use the **AppId** and **AppKey**  when creating the client.
