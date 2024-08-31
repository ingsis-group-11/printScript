package parser.semantic;

import AST.ASTVisitor;
import AST.ExpressionTypeVisitor;
import AST.nodes.*;
import java.util.List;
import parser.semantic.result.SemanticErrorResult;
import parser.semantic.result.SemanticResult;
import parser.semantic.result.SemanticSuccessResult;
import token.TokenType;

public class SemanticVisitor implements ASTVisitor<SemanticResult> {

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
    ExpressionTypeVisitor expressionTypeVisitor = new ExpressionTypeVisitor();
    TokenType variableType = node.getDeclaration().getTypeToken().getType();
    TokenType expressionType = node.getExpression().accept(expressionTypeVisitor);

    if (variableType == TokenType.NUMBER_TYPE && expressionType == TokenType.NUMBER) {
      return new SemanticSuccessResult();
    } else if (variableType == TokenType.STRING_TYPE && expressionType == TokenType.STRING) {
      return new SemanticSuccessResult();
    } else if(variableType == TokenType.BOOLEAN_TYPE && expressionType == TokenType.BOOLEAN) {
      return new SemanticSuccessResult();
    }
    else if (expressionType == TokenType.IDENTIFIER) {
      return new SemanticSuccessResult();
    }
    else if (expressionType == TokenType.READINPUT_KEYWORD) {
      return new SemanticSuccessResult();
    }
    return new SemanticErrorResult(
        List.of(
            "Semantic error in "
                + node.getLine()
                + ":"
                + node.getColumn()
                + " Variable type is "
                + variableType
                + " but value is "
                + expressionType));
  }

  @Override
  public SemanticResult visit(OperatorNode node) {
    ExpressionTypeVisitor expressionTypeVisitor = new ExpressionTypeVisitor();
    String operator = node.getOperator();
    return switch (operator) {
      case "+" -> new SemanticSuccessResult();
      case "-", "*", "/" -> {
        if (bothNumbersOrIdentifiers(node, expressionTypeVisitor)) {
          yield new SemanticSuccessResult();
        } else {
          yield new SemanticErrorResult(
              List.of(
                  "Semantic error in "
                      + node.getLine()
                      + ":"
                      + node.getColumn()
                      + " Operator "
                      + operator
                      + " can only be applied to numbers"));
        }
      }
      default ->
          new SemanticErrorResult(
              List.of(
                  "Semantic error in "
                      + node.getLine()
                      + ":"
                      + node.getColumn()
                      + " Operator not recognized"));
    };
  }

  @Override
  public SemanticResult visit(VariableNode node) {
    return new SemanticSuccessResult();
  }

  @Override
  public SemanticResult visit(ReassignmentNode node) {
    return new SemanticSuccessResult();
  }

  @Override
  public SemanticResult visit(ReadInputNode node) {
    ExpressionTypeVisitor expressionTypeVisitor = new ExpressionTypeVisitor();
    TokenType expressionType = node.getString().accept(expressionTypeVisitor);
    if (expressionType == TokenType.IDENTIFIER) {
      return new SemanticSuccessResult();
    } else if(expressionType == TokenType.STRING) {
      return new SemanticSuccessResult();
    }
    return new SemanticErrorResult(
        List.of(
            "Semantic error in "
                + node.getLine()
                + ":"
                + node.getColumn()
                + " Read input can only receive a string"));
  }

  private static Boolean bothNumbersOrIdentifiers(
      OperatorNode operatorNode, ExpressionTypeVisitor expressionTypeVisitor) {
    if (operatorNode.getLeftNode().accept(expressionTypeVisitor) == TokenType.NUMBER
        && operatorNode.getRightNode().accept(expressionTypeVisitor) == TokenType.NUMBER) {
      return true;
    } else if ((operatorNode.getLeftNode().accept(expressionTypeVisitor) == TokenType.IDENTIFIER
        && operatorNode.getRightNode().accept(expressionTypeVisitor) == TokenType.NUMBER)
    || (operatorNode.getLeftNode().accept(expressionTypeVisitor) == TokenType.NUMBER
        && operatorNode.getRightNode().accept(expressionTypeVisitor) == TokenType.IDENTIFIER)){
      return true;
    }
    return false;
  }
}
