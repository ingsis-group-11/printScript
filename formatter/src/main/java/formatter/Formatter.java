package formatter;

import fileReader.FileReader;
import token.Token;
import java.util.List;

public class Formatter {
    private final FileReader fileReader = new FileReader();

    public void formatFile(List<Token> tokens, String input, String output) {
        return;
    }
}


// let name: string="John";
// LET_TOKEN, WHITESPACE_TOKEN, IDENTIFIER_TOKEN, COLON_TOKEN, WHITESPACE_TOKEN,
// STRING_TOKEN, EQUALS_TOKEN, STRING_LITERAL_TOKEN, SEMICOLON_TOKEN
// let name : string = "John"; -> Nueva lista de tokens, armo un nuevo archivo