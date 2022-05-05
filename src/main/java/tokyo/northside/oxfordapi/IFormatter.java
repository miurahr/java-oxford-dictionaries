package tokyo.northside.oxfordapi;

import tokyo.northside.oxfordapi.dtd.LexicalEntry;

public interface IFormatter {

    String formatTranslations(LexicalEntry lexicalEntry);

    String formatDefinitions(LexicalEntry lexicalEntry);
}
