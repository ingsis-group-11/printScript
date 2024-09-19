package parser.semantic;

import ast.AstVisitor;
import ast.nodes.AssignationNode;
import ast.nodes.AstNode;
import ast.nodes.BlockNode;
import ast.nodes.DeclarationNode;
import ast.nodes.EmptyNode;
import ast.nodes.IfNode;
import ast.nodes.LiteralNode;
import ast.nodes.OperatorNode;
import ast.nodes.PrintNode;
import ast.nodes.ReadEnvNode;
import ast.nodes.ReadInputNode;
import ast.nodes.ReassignmentNode;
import ast.nodes.VariableNode;
import java.util.List;

import ast.tokens.AstTokenType;
import parser.semantic.result.SemanticErrorResult;
import parser.semantic.result.SemanticResult;
import parser.semantic.result.SemanticSuccessResult;
import parser.semantic.variables.VariablesMap;

public class SemanticVisitor implements AstVisitor<SemanticResult> {

  private final VariablesMap variablesMap;

  public SemanticVisitor() {
    this.variablesMap = new VariablesMap();
  }

  @Override
  public SemanticResult visit(DeclarationNode node) {
    return new SemanticSuccessResult();
  }

  @Override
  public SemanticResult visit(LiteralNode node) {
    return new SemanticSuccessResult();
  }

  @Override
  public SemanticResult visit(PrintNode node) {
    return node.getExpression().accept(this);
  }

  @Override
  public SemanticResult visit(AssignationNode node) {
    ExpressionTypeVisitor expressionTypeVisitor = new ExpressionTypeVisitor(variablesMap);
    AstTokenType variableType = node.getDeclaration().getTypeToken().getType();
    AstTokenType expressionType = node.getExpression().accept(expressionTypeVisitor);
    String variableName = node.getDeclaration().getNameToken().getValue();
    boolean mutable = node.getDeclaration().isMutable();

    if (node.getExpression() instanceof EmptyNode) {
      variablesMap.addVariable(variableName, variableType, mutable);
      return new SemanticSuccessResult();
    } else if (TypeValidator.validateType(variableType, expressionType)) {
      variablesMap.addVariable(variableName, variableType, mutable);
      return new SemanticSuccessResult();
    }

    return new SemanticErrorResult(
        "Semantic error in "
            + node.getLine()
            + ":"
            + node.getColumn()
            + " Variable type is "
            + variableType
            + " but value is "
            + expressionType);
  }

  @Override
  public SemanticResult visit(OperatorNode node) {
    ExpressionTypeVisitor expressionTypeVisitor = new ExpressionTypeVisitor(variablesMap);
    AstTokenType leftType = node.getLeftNode().accept(expressionTypeVisitor);
    AstTokenType rightType = node.getRightNode().accept(expressionTypeVisitor);

    String operator = node.getOperator();
    return switch (operator) {
      case "+" ->
          someBoolean(node, expressionTypeVisitor)
              ? new SemanticErrorResult(
                  "Semantic error in "
                      + node.getLine()
                      + ":"
                      + node.getColumn()
                      + " Operator "
                      + operator
                      + " can only be applied to numbers or strings")
              : new SemanticSuccessResult();
      case "-", "*", "/" -> {
        if (bothNumbers(leftType, rightType)) {
          yield new SemanticSuccessResult();
        } else {
          yield new SemanticErrorResult(
              "Semantic error in "
                  + node.getLine()
                  + ":"
                  + node.getColumn()
                  + " Operator "
                  + operator
                  + " can only be applied to numbers");
        }
      }
      default ->
          new SemanticErrorResult(
              "Semantic error in "
                  + node.getLine()
                  + ":"
                  + node.getColumn()
                  + " Operator not recognized");
    };
  }

  @Override
  public SemanticResult visit(VariableNode node) {
    return new SemanticSuccessResult();
  }

  @Override
  public SemanticResult visit(ReassignmentNode node) {
    ExpressionTypeVisitor expressionTypeVisitor = new ExpressionTypeVisitor(variablesMap);
    AstTokenType variableType = variablesMap.getVariableType(node.getVariableNode().getValue());
    AstTokenType expressionType = node.getExpression().accept(expressionTypeVisitor);
    if (variableType == expressionType) {
      variablesMap.updateVariable(node.getVariableNode().getValue(), expressionType);
      return new SemanticSuccessResult();
    } else if (expressionType == AstTokenType.READ_INPUT || expressionType == AstTokenType.READ_ENV) {
      return new SemanticSuccessResult();
    }
    return new SemanticErrorResult(
        "Semantic error in "
            + node.getLine()
            + ":"
            + node.getColumn()
            + " Variable type is "
            + variableType
            + " but value is of type "
            + expressionType);
  }

  @Override
  public SemanticResult visit(EmptyNode emptyNode) {
    return new SemanticSuccessResult();
  }

  @Override
  public SemanticResult visit(ReadInputNode node) {
    ExpressionTypeVisitor expressionTypeVisitor = new ExpressionTypeVisitor(variablesMap);
    AstTokenType expressionType = node.getExpression().accept(expressionTypeVisitor);
    if (expressionType == AstTokenType.IDENTIFIER) {
      return new SemanticSuccessResult();
    } else if (expressionType == AstTokenType.STRING) {
      return new SemanticSuccessResult();
    }
    return new SemanticErrorResult(
        "Semantic error in "
            + node.getLine()
            + ":"
            + node.getColumn()
            + " readInput can only receive a string");
  }

  @Override
  public SemanticResult visit(ReadEnvNode node) {
    ExpressionTypeVisitor expressionTypeVisitor = new ExpressionTypeVisitor(variablesMap);
    AstTokenType expressionType = node.getExpression().accept(expressionTypeVisitor);
    if (expressionType == AstTokenType.IDENTIFIER) {
      return new SemanticSuccessResult();
    } else if (expressionType == AstTokenType.STRING) {
      return new SemanticSuccessResult();
    }
    return new SemanticErrorResult(
        "Semantic error in "
            + node.getLine()
            + ":"
            + node.getColumn()
            + " readEnv can only receive a string");
  }

  @Override
  public SemanticResult visit(IfNode ifNode) {
    AstTokenType conditionType = ifNode.getCondition().accept(new ExpressionTypeVisitor(variablesMap));
    if (conditionType == AstTokenType.BOOLEAN || conditionType == AstTokenType.IDENTIFIER) {
      SemanticResult ifBlockResult = ifNode.getIfBlock().accept(this);
      if (ifBlockResult.hasErrors()) {
        return ifBlockResult;
      }
      SemanticResult elseBlockResult = ifNode.getElseBlock().accept(this);
      if (elseBlockResult.hasErrors()) {
        return elseBlockResult;
      }
      return new SemanticSuccessResult();
    }
    return new SemanticErrorResult(
        "Semantic error in "
            + ifNode.getCondition().getLine()
            + ":"
            + ifNode.getCondition().getColumn()
            + " Condition must be a boolean or identifier");
  }

  @Override
  public SemanticResult visit(BlockNode blockNode) {
    variablesMap.enterScope();
    List<AstNode> nodes = blockNode.getStatements();
    for (AstNode node : nodes) {
      SemanticResult result = node.accept(this);
      if (result instanceof SemanticErrorResult) {
        return result;
      }
    }
    variablesMap.exitScope();
    return new SemanticSuccessResult();
  }

  // * Helper methods

  private Boolean bothNumbers(AstTokenType leftType, AstTokenType rightType) {
    return leftType == AstTokenType.NUMBER && rightType == AstTokenType.NUMBER;
  }

  private Boolean someBoolean(
      OperatorNode operatorNode, ExpressionTypeVisitor expressionTypeVisitor) {
    return operatorNode.getLeftNode().accept(expressionTypeVisitor) == AstTokenType.BOOLEAN
        || operatorNode.getRightNode().accept(expressionTypeVisitor) == AstTokenType.BOOLEAN;
  }
}
