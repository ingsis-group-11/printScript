package linter.result;

import java.util.List;

public class FailedLinterResult implements LinterResult {
  List<String> errors;

  public FailedLinterResult(List<String> errors) {
    this.errors = errors;
  }

  @Override
  public boolean hasErrors() {
    return true;
  }

  @Override
  public List<String> getErrors() {
    return errors;
  }
}
