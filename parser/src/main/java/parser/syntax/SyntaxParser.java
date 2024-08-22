package parser.syntax;

import AST.nodes.ASTNode;
import java.util.List;
import token.Token;

public interface SyntaxParser {

  ASTNode syntaxParse(List<Token> tokens);
}
