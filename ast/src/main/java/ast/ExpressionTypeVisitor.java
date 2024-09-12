package ast;

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
import token.TokenType;

public class ExpressionTypeVisitor implements AstVisitor<TokenType> {

  @Override
  public TokenType visit(DeclarationNode node) {
    return node.getTypeToken().getType();
  }

  @Override
  public TokenType visit(LiteralNode node) {
    return node.getType();
  }

  @Override
  public TokenType visit(PrintNode node) {
    return node.getExpression().accept(this);
  }

  @Override
  public TokenType visit(AssignationNode node) {
    return node.getExpression().accept(this);
  }

  @Override
  public TokenType visit(OperatorNode node) {
    if (node.getLeftNode().accept(this) == TokenType.BOOLEAN
        || node.getRightNode().accept(this) == TokenType.BOOLEAN) {
      return TokenType.BOOLEAN;
    }
    if (node.getLeftNode().accept(this) == TokenType.NUMBER
        && node.getRightNode().accept(this) == TokenType.NUMBER) {
      return TokenType.NUMBER;
    } else if (node.getLeftNode().accept(this) == TokenType.IDENTIFIER
        || node.getRightNode().accept(this) == TokenType.IDENTIFIER) {
      return TokenType.IDENTIFIER;
    }
    return TokenType.STRING;
  }

  @Override
  public TokenType visit(VariableNode node) {
    return TokenType.IDENTIFIER;
  }

  @Override
  public TokenType visit(ReassignmentNode node) {
    return node.getExpression().accept(this);
  }

  @Override
  public TokenType visit(EmptyNode node) {
    return TokenType.EMPTY;
  }

  @Override
  public TokenType visit(ReadInputNode node) {
    return TokenType.READ_INPUT;
  }

  @Override
  public TokenType visit(ReadEnvNode readEnvNode) {
    return TokenType.STRING;
  }

  @Override
  public TokenType visit(IfNode ifNode) {
    return null;
  }

  @Override
  public TokenType visit(BlockNode blockNode) {
    return null;
  }
}