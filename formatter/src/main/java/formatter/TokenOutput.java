package formatter;

import java.util.List;

import ast.tokens.AstToken;

public class TokenOutput {
  public String toString(List<AstToken> tokens) {
    StringBuilder output = new StringBuilder();
    for (AstToken token : tokens) {
      output.append(token.getValue());
    }
    return output.toString();
  }
}
