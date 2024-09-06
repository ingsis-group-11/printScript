package parser.syntax.provider;

import parser.syntax.parsers.AssignationSyntaxParser;
import parser.syntax.parsers.SyntaxParser;
import parser.syntax.TokenStream;
import token.TokenType;

public class AssignationSyntaxParserProvider implements SyntaxParserProvider {

  @Override
  public boolean supports(TokenStream tokens) {
    return tokens.getCurrentToken().getType() == TokenType.LET_KEYWORD
        || tokens.getCurrentToken().getType() == TokenType.CONST_KEYWORD;
  }

  @Override
  public SyntaxParser createParser() {
    return new AssignationSyntaxParser();
  }

}