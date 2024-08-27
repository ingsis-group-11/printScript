package parser.syntax;

import java.util.List;
import token.Token;
import token.TokenType;

public class SyntaxParserFactory {

  public SyntaxParser getSyntaxParser(List<Token> tokens) {
    if (tokens.isEmpty()) {
      throw new IllegalArgumentException("Empty token list");
    }
    int i=0;
    Token firstToken = tokens.get(i);

    while((firstToken.getType() == TokenType.WHITESPACE || firstToken.getType() == TokenType.LINE_BREAK) && i < tokens.size()-1) {
      i+=1;
      firstToken = tokens.get(i);
    }

    return switch (firstToken.getType()) {
      case LET_KEYWORD -> new AssignationSyntaxParser();
      case IDENTIFIER -> new ReassignationSyntaxParser();
      case PRINT_KEYWORD -> new PrintSyntaxParser();
      default -> throw new IllegalArgumentException("Invalid");
    };
  }
}
