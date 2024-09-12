package linter.rules;

import ast.nodes.AstNode;
import linter.result.LinterResult;

public interface Rule {
  void setValue(String value);

  LinterResult lint(AstNode node);
}
