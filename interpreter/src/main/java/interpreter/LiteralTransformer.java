package interpreter;

import AST.ASTVisitor;
import AST.nodes.*;
import interpreter.providers.inputProvider.InputProvider;
import interpreter.inputType.InputTypeDetector;
import token.TokenType;
import token.ValueToken;

public class LiteralTransformer implements ASTVisitor<LiteralNode> {

  private final VariableAssignation variableAssignation;
  private final InputProvider inputProvider;

  public LiteralTransformer(VariableAssignation variableAssignation, InputProvider inputProvider) {
    this.variableAssignation = variableAssignation;
    this.inputProvider = inputProvider;
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
  public LiteralNode visit(ReadInputNode node) {
    LiteralNode expression = node.getString().accept(this);
    String message = expression.getValue();
    String input = inputProvider.getInput(message);
    TokenType inputType = new InputTypeDetector().detectInputType(input);
    return new LiteralNode(
        new ValueToken(inputType, input, node.getColumn(), node.getLine()));
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
        yield String.valueOf(
            Double.parseDouble(left.getValue()) + Double.parseDouble(right.getValue()));
      }
        case SUBTRACTION -> {
        checkInvalidOperation(left, right, leftType, rightType, operator);
        yield String.valueOf(
            Double.parseDouble(left.getValue()) - Double.parseDouble(right.getValue()));
      }
        case MULTIPLICATION -> {
        checkInvalidOperation(left, right, leftType, rightType, operator);
        yield String.valueOf(
            Double.parseDouble(left.getValue()) * Double.parseDouble(right.getValue()));
      }
        case DIVISION -> {
        checkInvalidOperation(left, right, leftType, rightType, operator);
        yield String.valueOf(
            Double.parseDouble(left.getValue()) / Double.parseDouble(right.getValue()));
      }
    };
  }

  private void checkInvalidOperation(
      LiteralNode left,
      LiteralNode right,
      TokenType leftType,
      TokenType rightType,
      String operator) {
    if (leftType == TokenType.STRING || rightType == TokenType.STRING) {
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
}
