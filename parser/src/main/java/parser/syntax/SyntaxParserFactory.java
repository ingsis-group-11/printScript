package parser.syntax;

import java.util.Iterator;
import token.Token;
import token.TokenType;

public class SyntaxParserFactory {

  public SyntaxParser getSyntaxParser(Iterator<Token> tokens) {
    if (!tokens.hasNext()) {
      throw new IllegalArgumentException("Empty token iterator");
    }

    Token firstToken = null;

    while (tokens.hasNext()) {
      firstToken = tokens.next();
      if (firstToken.getType() != TokenType.LINE_BREAK && firstToken.getType() != TokenType.WHITESPACE) {
        break;
      }
    }

    if (firstToken.getType() == TokenType.LET_KEYWORD) {
      return new AssignationSyntaxParser();
    } else if (firstToken.getType() == TokenType.IDENTIFIER) {
      return new ReassignationSyntaxParser();
    } else {
      return new PrintSyntaxParser();
    }
  }
}