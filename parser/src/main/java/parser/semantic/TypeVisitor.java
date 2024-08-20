package parser.semantic;

import AST.ASTVisitor;
import AST.nodes.*;
import token.TokenType;

public class TypeVisitor implements ASTVisitor<TokenType> {

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
}
