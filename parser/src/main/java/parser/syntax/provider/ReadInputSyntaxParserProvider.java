package parser.syntax.provider;

import parser.syntax.parsers.ReadInputSyntaxParser;
import parser.syntax.parsers.SyntaxParser;
import parser.syntax.TokenStream;
import token.TokenType;

public class ReadInputSyntaxParserProvider implements SyntaxParserProvider{
  @Override
  public boolean supports(TokenStream tokens) {
    return tokens.getCurrentToken().getType() == TokenType.READ_INPUT;
  }

  @Override
  public SyntaxParser createParser() {
    return new ReadInputSyntaxParser();
  }
}