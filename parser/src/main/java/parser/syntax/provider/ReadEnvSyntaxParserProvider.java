package parser.syntax.provider;

import parser.syntax.TokenStream;
import parser.syntax.parsers.ReadEnvSyntaxParser;
import parser.syntax.parsers.SyntaxParser;
import token.TokenType;

public class ReadEnvSyntaxParserProvider implements SyntaxParserProvider {
  @Override
  public boolean supports(TokenStream tokens) {
    return tokens.getCurrentToken().getType() == TokenType.READ_ENV;
  }

  @Override
  public SyntaxParser createParser() {
    return new ReadEnvSyntaxParser();
  }
}
