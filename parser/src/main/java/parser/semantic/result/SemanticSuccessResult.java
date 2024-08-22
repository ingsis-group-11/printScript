package parser.semantic.result;

import java.util.ArrayList;
import java.util.List;

public class SemanticSuccessResult implements SemanticResult {

  @Override
  public boolean hasErrors() {
    return false;
  }

  @Override
  public List<String> messages() {
    return new ArrayList<>();
  }
}
