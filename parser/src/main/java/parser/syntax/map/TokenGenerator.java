package parser.syntax.map;

import ast.tokens.AstToken;
import ast.tokens.ValueAstToken;
import token.Token;

public class TokenGenerator {
  public AstToken getAstToken(Token token) {
    TokenAdapter tokenAdapter = new TokenAdapter();
    return new ValueAstToken(
        tokenAdapter.getAstTokenType(token.getType()),
        token.getValue(),
        token.getColumn(),
        token.getLine());
  }
}
