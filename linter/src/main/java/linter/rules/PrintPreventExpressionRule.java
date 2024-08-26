package linter.rules;

import AST.nodes.ASTNode;
import linter.result.LinterResult;

import java.util.List;

public class PrintPreventExpressionRule implements Rule {
  @Override
  public LinterResult accept(RuleVisitor visitor, List<ASTNode> nodes) {
    return visitor.visit(this, nodes);
  }

}
