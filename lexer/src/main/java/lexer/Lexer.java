package lexer;

import result.*;

public class Lexer {
    public LexingResult lex(String stringFile) {
        Tokenizer tokenizer = new Tokenizer();
        LexingResult lexerResult = tokenizer.tokenize(stringFile);

        return lexerResult;
    }


}



