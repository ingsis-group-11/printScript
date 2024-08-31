package linter.rules.validator;

import AST.nodes.*;
import linter.result.FailedLinterResult;
import linter.result.LinterResult;
import linter.result.SuccessLinterResult;
import linter.rules.CamelCaseRule;
import linter.rules.InputPreventExpressionRule;
import linter.rules.PrintPreventExpressionRule;

import java.util.ArrayList;
import java.util.List;

public class RuleValidator implements RuleVisitor{

    @Override
    public LinterResult visit(CamelCaseRule rule, ASTNode node) {
        List<String> errors = new ArrayList<>();
        if (node instanceof AssignationNode assignationNode){
          String variableName = assignationNode.getDeclaration().getNameToken().getValue();
            if (!isCamelCase(variableName)) {
                int line = assignationNode.getDeclaration().getNameToken().getLine();
                int column = assignationNode.getDeclaration().getNameToken().getColumn();
                errors.add("Variable " + variableName + " is not in camelCase format at " + line + ":" + column);
            }
        }

        return errors.isEmpty() ? new SuccessLinterResult() : new FailedLinterResult(errors);
    }

    private boolean isCamelCase(String str) {
        String regex = "^[a-z]+([A-Z][a-z]+)*$";
        return str.matches(regex);
    }

    @Override
    public LinterResult visit(PrintPreventExpressionRule rule, ASTNode node) {
      List<String> errors = new ArrayList<>();
      if (node instanceof PrintNode printNode){
        ASTNode expression = printNode.getExpression();
        if (expression instanceof OperatorNode) {
          int line = printNode.getLine();
          int column = printNode.getColumn();
          errors.add("Print statement at " + line + ":" + column + " has an expression");
        }
      }

      return errors.isEmpty() ? new SuccessLinterResult() : new FailedLinterResult(errors);
    }

  @Override
  public LinterResult visit(InputPreventExpressionRule inputPreventExpressionRule, ASTNode node) {
    List<String> errors = new ArrayList<>();
    if (node instanceof AssignationNode assignationNode){
      ASTNode expression = assignationNode.getExpression();
      if (expression instanceof ReadInputNode readInputNode) {
        if(readInputNode.getString() instanceof OperatorNode){
          int line = readInputNode.getLine();
          int column = readInputNode.getColumn();
          errors.add("readInput statement at " + line + ":" + column + " has an expression");
        }
      }
    }

    return errors.isEmpty() ? new SuccessLinterResult() : new FailedLinterResult(errors);
  }
}
