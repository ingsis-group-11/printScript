package iterator;

import fileReader.FileReaderIterator;
import lexer.Lexer;
import result.*;
import token.Token;

import java.io.IOException;
import java.util.Iterator;

public class TokenIterator implements Iterator<Token> {
    private final Lexer lexer;
    private final FileReaderIterator input;

    public TokenIterator(FileReaderIterator input, Lexer lexer) {
        this.input = input;
        this.lexer = lexer;
    }
    @Override
    public boolean hasNext() {
        try {
            return lexer.hasNext(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Token next() {
        LexingResult result = lexer.lex(input);
        if (result instanceof SuccessfulResult) {
            return ((SuccessfulResult) result).token();
        } else {
            throw new RuntimeException("Error while lexing");
        }
    }
}
