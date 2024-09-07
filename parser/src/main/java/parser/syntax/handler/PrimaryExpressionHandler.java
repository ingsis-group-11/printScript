package parser.syntax.handler;

import AST.nodes.ASTNode;
import parser.syntax.TokenStream;
import token.Token;

public interface PrimaryExpressionHandler {
  ASTNode handle(TokenStream tokenStream, Token token);
}