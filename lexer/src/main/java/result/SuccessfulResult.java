package result;

import java.util.List;
import token.Token;

public record SuccessfulResult(List<Token> tokens) implements LexingResult {
  @Override
  public boolean isSuccess() {
    return true;
  }
}
