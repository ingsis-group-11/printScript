package linter.result;

import java.util.List;

public class SuccessLinterResult implements LinterResult {
  @Override
  public boolean hasErrors() {
    return false;
  }

  @Override
  public List<String> getErrors() {
    return List.of();
  }
}
