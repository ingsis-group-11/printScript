package interpreter;

import AST.ASTVisitor;
import AST.nodes.*;
import token.TokenType;
import token.ValueToken;

import java.util.ListResourceBundle;

public class LiteralTransformer implements ASTVisitor<LiteralNode> {

  private final VariableAssignation variableAssignation;

  public LiteralTransformer(VariableAssignation variableAssignation) {
    this.variableAssignation = variableAssignation;
  }

  @Override
  public LiteralNode visit(DeclarationNode node) {
    return null;
  }

  @Override
  public LiteralNode visit(LiteralNode node) {
    return node;
  }

  @Override
  public LiteralNode visit(PrintNode node) {
    return null;
  }

  @Override
  public LiteralNode visit(AssignationNode node) {
    return null;
  }

  @Override
  public LiteralNode visit(OperatorNode node) {
    String operator = node.getOperator();
    LiteralNode left = node.getLeftNode().accept(this);
    LiteralNode right = node.getRightNode().accept(this);
    return new LiteralNode(
        new ValueToken(
            left.getType(), parseCalc(operator, left, right), left.getLine(), left.getColumn()));
  }

  @Override
  public LiteralNode visit(VariableNode node) {
    String variableName = node.getValue();
    LiteralNode variableValue = variableAssignation.getVariable(variableName);
    return new LiteralNode(
        new ValueToken(
            variableValue.getType(),
            variableValue.getValue(),
            variableValue.getLine(),
            variableValue.getColumn()));
  }

  @Override
  public LiteralNode visit(ReassignmentNode node) {
    return null;
  }

  @Override
  public LiteralNode visit(EmptyNode emptyNode) {
    return new LiteralNode(new ValueToken(emptyNode.getType(), "", null, null));
  }

  private String parseCalc(String operator, LiteralNode left, LiteralNode right) {
    TokenType leftType = left.getType();
    TokenType rightType = right.getType();
    Operators operatorEnum = Operators.fromSymbol(operator);
    return switch (operatorEnum) {
        case ADDITION -> {
        if (leftType == TokenType.STRING || rightType == TokenType.STRING) {
          yield left.getValue() + right.getValue();
        }
        checkInvalidOperation(left, right, leftType, rightType, operator);
        if(isDouble(left.getValue()) || isDouble(right.getValue())) {
          yield String.valueOf(parseToDouble(left.getValue()) + parseToDouble(right.getValue()));
        }
        yield String.valueOf(parseToInteger(left.getValue()) + parseToInteger(right.getValue()));
      }
        case SUBTRACTION -> {
        checkInvalidOperation(left, right, leftType, rightType, operator);
        if(isDouble(left.getValue()) || isDouble(right.getValue())) {
          yield String.valueOf(parseToDouble(left.getValue()) - parseToDouble(right.getValue()));
        }
        yield String.valueOf(parseToInteger(left.getValue()) - parseToInteger(right.getValue()));
      }
        case MULTIPLICATION -> {
        checkInvalidOperation(left, right, leftType, rightType, operator);
          if(isDouble(left.getValue()) || isDouble(right.getValue())) {
            yield String.valueOf(parseToDouble(left.getValue()) * parseToDouble(right.getValue()));
          }
          yield String.valueOf(parseToInteger(left.getValue()) * parseToInteger(right.getValue()));
      }
        case DIVISION -> {
        checkInvalidOperation(left, right, leftType, rightType, operator);
          if(isDouble(left.getValue()) || isDouble(right.getValue())) {
            yield String.valueOf(divide(parseToDouble(left.getValue()), parseToDouble(right.getValue())));
          }
          yield String.valueOf(divide(parseToInteger(left.getValue()), parseToInteger(right.getValue())));
      }
    };
  }

  private void checkInvalidOperation(
      LiteralNode left,
      LiteralNode right,
      TokenType leftType,
      TokenType rightType,
      String operator) {
    if ((leftType == TokenType.STRING || leftType == TokenType.BOOLEAN)
        || (rightType == TokenType.STRING || rightType == TokenType.BOOLEAN)) {
      throw new RuntimeException(
          "Invalid operation: "
              + left.getValue()
              + " "
              + operator
              + " "
              + right.getValue()
              + " on "
              + left.getLine()
              + ":"
              + left.getColumn());
    }
  }

  private boolean isDouble(String value) {
    return value.matches("^\\d+\\.\\d+$");
  }

  private Double parseToDouble(String value) {
    return Double.parseDouble(value);
  }

  private Integer parseToInteger(String value) {
    return Integer.parseInt(value);
  }

  private Number divide(int a, int b) {
    double result = (double) a / b;
    if (result == (int) result) {
      return (int) result;
    } else {
      return result;
    }
  }

  private Number divide(double a, double b) {
    double result = a / b;
    if (result == (int) result) {
      return (int) result;
    } else {
      return result;
    }
  }
}
