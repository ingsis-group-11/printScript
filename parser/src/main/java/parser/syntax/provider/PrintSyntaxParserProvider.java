package parser.syntax.provider;

import parser.syntax.PrintSyntaxParser;
import parser.syntax.SyntaxParser;
import parser.syntax.TokenStream;
import token.TokenType;

public class PrintSyntaxParserProvider implements SyntaxParserProvider{

  @Override
  public boolean supports(TokenStream tokens) {
    return tokens.getCurrentToken().getType() == TokenType.PRINT_KEYWORD;
  }

  @Override
  public SyntaxParser createParser() {
    return new PrintSyntaxParser();
  }
}
