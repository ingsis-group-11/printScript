package result;

public record FileFailureResult(String message) implements LexingResult {
  @Override
  public boolean isSuccess() {
    return false;
  }
}
