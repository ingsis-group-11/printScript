package parser.syntax.provider;

import parser.syntax.ReassignationSyntaxParser;
import parser.syntax.SyntaxParser;
import parser.syntax.TokenStream;
import token.TokenType;

public class ReassignationSyntaxParserProvider implements SyntaxParserProvider{

  @Override
  public boolean supports(TokenStream tokens) {
    return tokens.getCurrentToken().getType() == TokenType.IDENTIFIER;
  }

  @Override
  public SyntaxParser createParser() {
    return new ReassignationSyntaxParser();
  }
}