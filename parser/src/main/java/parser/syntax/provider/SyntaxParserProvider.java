package parser.syntax.provider;

import parser.syntax.SyntaxParser;
import parser.syntax.TokenStream;

public interface SyntaxParserProvider {
  boolean supports(TokenStream tokens);
  SyntaxParser createParser();
}