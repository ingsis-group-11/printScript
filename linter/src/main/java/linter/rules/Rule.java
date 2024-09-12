package linter.rules;

import AST.nodes.ASTNode;
import linter.result.LinterResult;

public interface Rule {
  void setValue(String value);

  LinterResult lint(ASTNode node);
}
