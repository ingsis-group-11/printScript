package linter.rules;

import AST.nodes.ASTNode;
import AST.nodes.OperatorNode;
import AST.nodes.ReadInputNode;
import linter.nodeFinder.ReadInputNodeFinder;
import linter.result.FailedLinterResult;
import linter.result.LinterResult;
import linter.result.SuccessLinterResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ReadInputPreventExpressionRule implements Rule {
  private String value;

  @Override
  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public LinterResult lint(ASTNode node) {
    if(Objects.equals(value, "false")){
      return new SuccessLinterResult();
    }
    List<String> errors = new ArrayList<>();

    ReadInputNodeFinder readInputNodeFinder = new ReadInputNodeFinder();
    node.accept(readInputNodeFinder);
    List<ReadInputNode> readInputNodes = readInputNodeFinder.getReadInputNodes();

    for (ReadInputNode readInputNode : readInputNodes) {
      ASTNode expression = readInputNode.getExpression();
      if (expression instanceof OperatorNode) {
        int line = readInputNode.getLine();
        int column = readInputNode.getColumn();
        errors.add("readInput statement at " + line + ":" + column + " has an expression");
      }
    }

    return errors.isEmpty() ? new SuccessLinterResult() : new FailedLinterResult(errors);
  }
}
