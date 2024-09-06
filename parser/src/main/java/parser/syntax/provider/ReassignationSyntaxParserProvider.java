package parser.syntax.provider;

import parser.syntax.parsers.ReassignationSyntaxParser;
import parser.syntax.parsers.SyntaxParser;
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