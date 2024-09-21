package formatter;

import ast.tokens.AstToken;
import java.util.List;

public class TokenOutput {
  public String toString(List<AstToken> tokens) {
    StringBuilder output = new StringBuilder();
    for (AstToken token : tokens) {
      output.append(token.getValue());
    }
    return output.toString();
  }
}
