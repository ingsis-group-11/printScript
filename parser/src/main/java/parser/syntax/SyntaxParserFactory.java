package parser.syntax;

import java.util.List;
import token.Token;
import token.TokenType;

public class SyntaxParserFactory {

  public SyntaxParser getSyntaxParser(List<Token> tokens) {
    if (tokens.isEmpty()) {
      throw new IllegalArgumentException("Empty token list");
    }

    Token firstToken = tokens.get(0);
    if (firstToken.getType() == TokenType.LET_KEYWORD) {
      return new AssignationSyntaxParser();
    } else if (firstToken.getType() == TokenType.IDENTIFIER) {
      return new ReassignationSyntaxParser();
    } else {
      return new PrintSyntaxParser();
    }
  }
}
