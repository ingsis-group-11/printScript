package result;

import token.Token;

public record SuccessfulResult(Token token) implements LexingResult {
  @Override
  public boolean isSuccess() {
    return true;
  }
}
