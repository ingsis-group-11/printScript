package linter.rules;

import ast.nodes.AstNode;
import ast.nodes.OperatorNode;
import ast.nodes.PrintNode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import linter.result.FailedLinterResult;
import linter.result.LinterResult;
import linter.result.SuccessLinterResult;

public class PrintPreventExpressionRule implements Rule {
  private String value;

  @Override
  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public LinterResult lint(AstNode node) {
    if (Objects.equals(value, "false")) {
      return new SuccessLinterResult();
    }
    List<String> errors = new ArrayList<>();
    if (node instanceof PrintNode printNode) {
      AstNode expression = printNode.getExpression();
      if (expression instanceof OperatorNode) {
        int line = printNode.getLine();
        int column = printNode.getColumn();
        errors.add("println statement at " + line + ":" + column + " has an expression");
      }
    }

    return errors.isEmpty() ? new SuccessLinterResult() : new FailedLinterResult(errors);
  }
}
