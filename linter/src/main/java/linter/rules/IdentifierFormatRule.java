package linter.rules;

import AST.nodes.ASTNode;
import AST.nodes.AssignationNode;
import AST.nodes.DeclarationNode;
import java.util.ArrayList;
import java.util.List;
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
    List<String> errors = new ArrayList<>();
    if (node instanceof AssignationNode assignationNode) {
      DeclarationNode declarationNode = assignationNode.getDeclaration();
      String variableName = declarationNode.getNameToken().getValue();
      if (notIsOfType(variableName, value)) {
        int line = declarationNode.getNameToken().getLine();
        int column = declarationNode.getNameToken().getColumn();
        errors.add(
            "Variable " + variableName + " is not in snake_case format at " + line + ":" + column);
      }
    }

    return errors.isEmpty() ? new SuccessLinterResult() : new FailedLinterResult(errors);
  }

  private boolean notIsOfType(String variableName, String value) {
    switch (value) {
      case "camel case" -> {
        return !isCamelCase(variableName);
      }
      case "snake case" -> {
        return !isSnakeCase(variableName);
      }
      default -> throw new RuntimeException("Invalid value for identifier_format rule");
    }
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
