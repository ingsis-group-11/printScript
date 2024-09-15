package parser.syntax.handler;

import parser.syntax.TokenStream;
import parser.syntax.result.ExpressionResult;
import token.Token;

public interface PrimaryExpressionHandler {
  ExpressionResult handle(TokenStream tokenStream, Token token);
}
