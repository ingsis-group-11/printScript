package parser.syntax.provider;

import parser.syntax.TokenStream;
import parser.syntax.parsers.IfSyntaxParser;
import parser.syntax.parsers.SyntaxParser;
import token.TokenType;

public class IfSyntaxParserProvider implements SyntaxParserProvider {

  @Override
  public boolean supports(TokenStream tokens) {
    return tokens.getCurrentToken().getType() == TokenType.IF_KEYWORD;
  }

  @Override
  public SyntaxParser createParser() {
    return new IfSyntaxParser();
  }
}
