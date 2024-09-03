package parser.syntax;

import parser.syntax.result.SyntaxResult;

public interface SyntaxParser {

  SyntaxResult syntaxParse(TokenStream tokens);
}
