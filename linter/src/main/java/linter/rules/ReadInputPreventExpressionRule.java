package linter.rules;

import ast.nodes.AstNode;
import ast.nodes.OperatorNode;
import ast.nodes.ReadInputNode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import linter.nodefinder.ReadInputNodeFinder;
import linter.result.FailedLinterResult;
import linter.result.LinterResult;
import linter.result.SuccessLinterResult;

public class ReadInputPreventExpressionRule implements Rule {
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

    List<ReadInputNode> readInputNodes = node.accept(new ReadInputNodeFinder());

    for (ReadInputNode readInputNode : readInputNodes) {
      AstNode expression = readInputNode.getExpression();
      if (expression instanceof OperatorNode) {
        int line = readInputNode.getLine();
        int column = readInputNode.getColumn();
        errors.add("readInput statement at " + line + ":" + column + " has an expression");
      }
    }

    return errors.isEmpty() ? new SuccessLinterResult() : new FailedLinterResult(errors);
  }
}
