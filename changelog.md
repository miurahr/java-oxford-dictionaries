# Changelog
All notable changes to this project will be documented in this file.

## [Unreleased]

* Introduce OxfordThreadClient to query multiple words
* Add IOxfordClient interface
* Introduce OxfordDictionaryEntry POJO class that hold
  * query word
  * head word
  * dictionary article in HTML
* Refactoring API methods
  * Add getDefinitions method to return List<OxfordDictionaryEntry>
  * Add getTranslations method to return List<OxfordDictionaryEntry>

## [v0.2.0]
* Update javadoc
* Introduce RequestFactory class
* Move exception class
* Add thesaurus parser test case
* Update entries parser
* Update translations parser

## [v0.1.1]
* Bump Gradle@7.2

## [v0.1.0]
* First alpha

[Unreleased]: https://github.com/miurahr/java-oxford-dictionaries/compare/v0.1.1...HEAD
[v0.1.1]: https://github.com/miurahr/java-oxford-dictionaries/compare/v0.1.0...v0.1.1
[v0.1.0]: https://github.com/miurahr/java-oxford-dictionaries/compare/v0.0.1...v0.1.0
