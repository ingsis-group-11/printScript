package parser.syntax.parsers;

import AST.nodes.ASTNode;
import parser.syntax.TokenStream;

public interface SyntaxParser {

  ASTNode syntaxParse(TokenStream tokens, String version);
}
