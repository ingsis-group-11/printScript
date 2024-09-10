package parser.semantic.result;

public record SemanticErrorResult(String message) implements SemanticResult {

  @Override
  public boolean hasErrors() {
    return true;
  }
}
