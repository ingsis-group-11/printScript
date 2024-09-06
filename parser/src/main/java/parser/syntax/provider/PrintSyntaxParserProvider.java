package parser.syntax.provider;

import parser.syntax.parsers.PrintSyntaxParser;
import parser.syntax.parsers.SyntaxParser;
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
