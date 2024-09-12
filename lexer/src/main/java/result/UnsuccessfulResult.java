package result;

public record UnsuccessfulResult(String message) implements LexingResult {
  @Override
  public boolean isSuccess() {
    return false;
  }
}
