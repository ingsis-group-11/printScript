package linter.result;

import java.util.List;

public interface LinterResult {
  boolean hasErrors();

  List<String> getErrors();
}
