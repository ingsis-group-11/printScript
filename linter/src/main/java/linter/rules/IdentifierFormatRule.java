package linter.rules;

import AST.nodes.ASTNode;
import AST.nodes.AssignationNode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import linter.result.FailedLinterResult;
import linter.result.LinterResult;
import linter.result.SuccessLinterResult;

public class IdentifierFormatRule implements Rule {
  private String value;

  @Override
  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public LinterResult lint(ASTNode node) {
    if (Objects.equals(value, "camel case")) {
      return getCamelCaseResult(node);
    } else if (Objects.equals(value, "snake case")) {
      return getSnakeCaseResult(node);
    }
    throw new RuntimeException("Invalid value for identifier_format rule");
  }

  private LinterResult getSnakeCaseResult(ASTNode node) {
    List<String> errors = new ArrayList<>();
    if (node instanceof AssignationNode assignationNode) {
      String variableName = assignationNode.getDeclaration().getNameToken().getValue();
      if (!isSnakeCase(variableName)) {
        int line = assignationNode.getDeclaration().getNameToken().getLine();
        int column = assignationNode.getDeclaration().getNameToken().getColumn();
        errors.add(
            "Variable " + variableName + " is not in snake_case format at " + line + ":" + column);
      }
    }

    return errors.isEmpty() ? new SuccessLinterResult() : new FailedLinterResult(errors);
  }

  private LinterResult getCamelCaseResult(ASTNode node) {
    List<String> errors = new ArrayList<>();
    if (node instanceof AssignationNode assignationNode) {
      String variableName = assignationNode.getDeclaration().getNameToken().getValue();
      if (!isCamelCase(variableName)) {
        int line = assignationNode.getDeclaration().getNameToken().getLine();
        int column = assignationNode.getDeclaration().getNameToken().getColumn();
        errors.add(
            "Variable " + variableName + " is not in camelCase format at " + line + ":" + column);
      }
    }

    return errors.isEmpty() ? new SuccessLinterResult() : new FailedLinterResult(errors);
  }

  private boolean isCamelCase(String variableName) {
    String regex = "^[a-z]+([A-Z][a-z]+)*$";
    return variableName.matches(regex);
  }

  private boolean isSnakeCase(String variableName) {
    String regex = "^[a-z]+(_[a-z]+)*$";
    return variableName.matches(regex);
  }
}
