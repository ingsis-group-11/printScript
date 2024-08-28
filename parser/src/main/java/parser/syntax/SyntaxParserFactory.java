package parser.syntax;

import java.util.List;
import token.Token;
import token.TokenType;

public class SyntaxParserFactory {

  public SyntaxParser getSyntaxParser(List<Token> tokens) {
    if (tokens.isEmpty()) {
      throw new IllegalArgumentException("Empty token list");
    }

    int i = 0;
    while (i < tokens.size()) {
      if (tokens.get(i).getType() != TokenType.LINE_BREAK) {
        break;
      }
      i++;
    }

    Token firstToken = tokens.get(i);

    if (firstToken.getType() == TokenType.LET_KEYWORD) {
      return new AssignationSyntaxParser();
    } else if (firstToken.getType() == TokenType.IDENTIFIER) {
      return new ReassignationSyntaxParser();
    } else {
      return new PrintSyntaxParser();
    }
  }
}