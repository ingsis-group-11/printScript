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

    if (node.getExpression() instanceof EmptyNode) {
      return new SemanticSuccessResult();
    }

    else if (TypeValidator.validateType(variableType, expressionType)) {
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
    ExpressionTypeVisitor expressionTypeVisitor = new ExpressionTypeVisitor();
    String operator = node.getOperator();
    return switch (operator) {
      case "+" -> new SemanticSuccessResult();
      case "-", "*", "/" -> {
        if (bothNumbersOrIdentifiers(node, expressionTypeVisitor)) {
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
    return new SemanticSuccessResult();
  }

  @Override
  public SemanticResult visit(EmptyNode emptyNode) {
    return new SemanticSuccessResult();
  }

  @Override
  public SemanticResult visit(ReadInputNode node) {
    ExpressionTypeVisitor expressionTypeVisitor = new ExpressionTypeVisitor();
    TokenType expressionType = node.getExpression().accept(expressionTypeVisitor);
    if (expressionType == TokenType.IDENTIFIER) {
      return new SemanticSuccessResult();
    } else if(expressionType == TokenType.STRING) {
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
    ExpressionTypeVisitor expressionTypeVisitor = new ExpressionTypeVisitor();
    TokenType expressionType = node.getExpression().accept(expressionTypeVisitor);
    if (expressionType == TokenType.IDENTIFIER) {
      return new SemanticSuccessResult();
    } else if(expressionType == TokenType.STRING) {
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
    ExpressionTypeVisitor expressionTypeVisitor = new ExpressionTypeVisitor();
    TokenType conditionType = ifNode.getCondition().accept(expressionTypeVisitor);
    if (conditionType == TokenType.BOOLEAN || conditionType == TokenType.IDENTIFIER) {
      SemanticResult ifBlockResult = ifNode.getIfBlock().accept(this);
      if (ifBlockResult instanceof SemanticErrorResult) {
        return ifBlockResult;
      }
      SemanticResult elseBlockResult = ifNode.getElseBlock().accept(this);
      if (elseBlockResult instanceof SemanticErrorResult) {
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
    List<ASTNode> nodes = blockNode.getStatements();
    for (ASTNode node : nodes) {
      SemanticResult result = node.accept(this);
      if (result instanceof SemanticErrorResult) {
        return result;
      }
    }
    return new SemanticSuccessResult();
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
