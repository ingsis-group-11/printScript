package parser.syntax;

import AST.nodes.ASTNode;
import token.Token;

import java.util.List;

public interface SyntaxParser {

    ASTNode syntaxParse(List<Token> tokens);
}
