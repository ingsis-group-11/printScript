package formatter;

import java.util.List;
import token.Token;

public class TokenOutput {
  public String toString(List<Token> tokens) {
    StringBuilder output = new StringBuilder();
    for (Token token : tokens) {
      output.append(token.getValue());
    }
    return output.toString();
  }
}
