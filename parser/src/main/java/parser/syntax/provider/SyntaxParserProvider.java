package parser.syntax.provider;

import parser.syntax.TokenStream;
import parser.syntax.parsers.SyntaxParser;

public interface SyntaxParserProvider {
  boolean supports(TokenStream tokens);

  SyntaxParser createParser();
}
