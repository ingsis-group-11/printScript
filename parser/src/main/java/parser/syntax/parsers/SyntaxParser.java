package parser.syntax.parsers;

import ast.nodes.AstNode;
import parser.syntax.TokenStream;

public interface SyntaxParser {

  AstNode syntaxParse(TokenStream tokens, String version);
}
