package iterator;

import fileReader.FileReaderIterator;
import lexer.Lexer;
import result.*;
import token.Token;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TokenIterator implements Iterator<Token> {
    private final Lexer lexer;
    private final FileReaderIterator inputIterator;

    public TokenIterator(FileReaderIterator inputIterator) {
        this.inputIterator = inputIterator;
        this.lexer = new Lexer(inputIterator);
    }
    @Override
    public boolean hasNext() {
        return inputIterator.hasNext();
    }

    @Override
    public Token next() {
        if(!hasNext()){
            throw new NoSuchElementException("No more tokens to parse");
        }
        LexingResult result = lexer.lex();
        if (result instanceof SuccessfulResult) {
            return ((SuccessfulResult) result).token();
        } else {
            throw new RuntimeException(((UnsuccessfulResult) result).message());
        }
    }
}
