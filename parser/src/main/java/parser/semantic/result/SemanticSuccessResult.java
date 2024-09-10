package parser.semantic.result;


public class SemanticSuccessResult implements SemanticResult {

  @Override
  public boolean hasErrors() {
    return false;
  }

  @Override
  public String message() {
    return "";
  }
}
