package interpreter;

import ast.AstVisitor;
import ast.nodes.AssignationNode;
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
import ast.tokens.ValueAstToken;
import inputyype.InputTypeTransformer;
import providers.inputprovider.InputProvider;
import ast.tokens.AstTokenType;
import variablemap.VariableMap;

public class LiteralTransformer implements AstVisitor<LiteralNode> {

  private final VariableMap variableMap;
  private final InputProvider inputProvider;

  public LiteralTransformer(VariableMap variableMap, InputProvider inputProvider) {
    this.variableMap = variableMap;
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
        new ValueAstToken(
            left.getType(), parseCalc(operator, left, right), left.getLine(), left.getColumn()));
  }

  @Override
  public LiteralNode visit(VariableNode node) {
    String variableName = node.getValue();
    LiteralNode variableValue = variableMap.getVariable(variableName);
    return new LiteralNode(
        new ValueAstToken(
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
    return new LiteralNode(new ValueAstToken(emptyNode.getType(), null, null, null));
  }

  @Override
  public LiteralNode visit(ReadInputNode node) {
    LiteralNode expression = node.getExpression().accept(this);
    String message = expression.getValue();
    String input = inputProvider.getInput(message);
    AstTokenType inputType = new InputTypeTransformer().detectInputType(input);
    return new LiteralNode(new ValueAstToken(inputType, input, node.getColumn(), node.getLine()));
  }

  @Override
  public LiteralNode visit(ReadEnvNode node) {
    LiteralNode expression = node.getExpression().accept(this);
    String variableName = expression.getValue();
    String env = System.getenv(variableName);
    if (env == null) {
      throw new RuntimeException("Environment variable " + variableName + " not found");
    }
    return new LiteralNode(new ValueAstToken(AstTokenType.STRING, env, node.getColumn(), node.getLine()));
  }

  @Override
  public LiteralNode visit(IfNode ifNode) {
    return null;
  }

  @Override
  public LiteralNode visit(BlockNode blockNode) {
    return null;
  }

  private String parseCalc(String operator, LiteralNode left, LiteralNode right) {
    AstTokenType leftType = left.getType();
    AstTokenType rightType = right.getType();
    String leftValue = left.getValue();
    String rightValue = right.getValue();
    Operators operatorEnum = Operators.fromSymbol(operator);
    return switch (operatorEnum) {
      case ADDITION -> {
        if (leftType == AstTokenType.STRING || rightType == AstTokenType.STRING) {
          yield leftValue + rightValue;
        }
        checkInvalidOperation(left, right, leftType, rightType, operator);
        if (isDouble(leftValue) || isDouble(rightValue)) {
          yield String.valueOf(parseToDouble(leftValue) + parseToDouble(rightValue));
        }
        yield String.valueOf(parseToInteger(leftValue) + parseToInteger(rightValue));
      }
      case SUBTRACTION -> {
        checkInvalidOperation(left, right, leftType, rightType, operator);
        if (isDouble(leftValue) || isDouble(right.getValue())) {
          yield String.valueOf(parseToDouble(left.getValue()) - parseToDouble(right.getValue()));
        }
        yield String.valueOf(parseToInteger(left.getValue()) - parseToInteger(right.getValue()));
      }
      case MULTIPLICATION -> {
        checkInvalidOperation(left, right, leftType, rightType, operator);
        if (isDouble(left.getValue()) || isDouble(right.getValue())) {
          yield String.valueOf(parseToDouble(left.getValue()) * parseToDouble(right.getValue()));
        }
        yield String.valueOf(parseToInteger(left.getValue()) * parseToInteger(right.getValue()));
      }
      case DIVISION -> {
        checkInvalidOperation(left, right, leftType, rightType, operator);
        if (isDouble(left.getValue()) || isDouble(right.getValue())) {
          yield String.valueOf(
              divide(parseToDouble(left.getValue()), parseToDouble(right.getValue())));
        }
        yield String.valueOf(
            divide(parseToInteger(left.getValue()), parseToInteger(right.getValue())));
      }
    };
  }

  private void checkInvalidOperation(
      LiteralNode left,
      LiteralNode right,
      AstTokenType leftType,
      AstTokenType rightType,
      String operator) {
    if ((leftType == AstTokenType.STRING || leftType == AstTokenType.BOOLEAN)
        || (rightType == AstTokenType.STRING || rightType == AstTokenType.BOOLEAN)) {
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
