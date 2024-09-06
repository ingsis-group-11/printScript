package parser.syntax.parsers;

import parser.syntax.TokenStream;
import parser.syntax.result.SyntaxResult;

public interface SyntaxParser {

  SyntaxResult syntaxParse(TokenStream tokens);
}
