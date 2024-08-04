package parser.syntax;

import token.Token;
import token.TokenType;

import java.util.List;

public class SyntaxParserFactory {

    public SyntaxParser getSyntaxParser(List<Token> tokens) {
        if (tokens.isEmpty()) {
            throw new IllegalArgumentException("Empty token list");
        }

        Token firstToken = tokens.getFirst();
        if (firstToken.getType() == TokenType.LET_KEYWORD) {
            return new AssignationSyntaxParser();
        } else  {
            return new PrintSyntaxParser();
        }
    }
}