package iterator;

import fileReader.FileReaderIterator;
import lexer.Lexer;
import providers.iterator.PrintScriptIterator;
import result.*;
import token.Token;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TokenIterator implements PrintScriptIterator<Token> {
    private final Lexer lexer;
    private final FileReaderIterator inputIterator;
    private Token currentToken;

    public TokenIterator(FileReaderIterator inputIterator, String version) {
        this.inputIterator = inputIterator;
        this.lexer = new Lexer(inputIterator, version);
        if (inputIterator.hasNext()) {
            this.currentToken = this.next();
        }
        else {
            this.currentToken = null;
        }
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
            currentToken = ((SuccessfulResult) result).token();
            return ((SuccessfulResult) result).token();
        } else {
            throw new RuntimeException(((UnsuccessfulResult) result).message());
        }
    }

    @Override
    public Token current(){
        return currentToken;
    }
}
