package parser.syntax.handler;

import ast.nodes.AstNode;
import parser.syntax.TokenStream;
import token.Token;

public interface PrimaryExpressionHandler {
  AstNode handle(TokenStream tokenStream, Token token);
}
