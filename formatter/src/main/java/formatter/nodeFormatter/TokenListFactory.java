package formatter.nodeFormatter;

import AST.ASTVisitor;
import AST.nodes.*;
import token.Token;
import token.TokenType;
import token.ValueToken;

import java.util.ArrayList;
import java.util.List;

public class TokenListFactory implements ASTVisitor<List<Token>> {
  @Override
  public List<Token> visit(DeclarationNode node) {
    List<Token> result = new ArrayList<>();
    result.add(node.getDeclarationKeyWord());
    result.add(new ValueToken(TokenType.WHITESPACE, " ", node.getDeclarationKeyWord().getColumn() + 1,
        node.getDeclarationKeyWord().getLine()));
    result.add(node.getNameToken());
    result.add(new ValueToken(TokenType.COLON, ":", node.getNameToken().getColumn() + 1,
        node.getNameToken().getLine()));
    result.add(node.getTypeToken());
    return result;
  }

  @Override
  public List<Token> visit(LiteralNode node) {
    return List.of(node.getToken());
  }

  @Override
  public List<Token> visit(PrintNode node) {
    List<Token> result = new ArrayList<>();
    result.add(new ValueToken(TokenType.PARENTHESIS_OPEN, "(", node.getLine(), node.getColumn()));
    result.addAll(node.getExpression().accept(this));
    result.add(new ValueToken(TokenType.PARENTHESIS_CLOSE, ")", node.getLine(), node.getColumn()));
    result.add(new ValueToken(TokenType.SEMICOLON, ";", node.getExpression().getColumn(), node.getLine()));
    return result;
  }

  @Override
  public List<Token> visit(AssignationNode node) {
    List<Token> result = new ArrayList<>(node.getDeclaration().accept(this));
    result.add(new ValueToken(TokenType.ASSIGN, "=", node.getColumn(), node.getLine()));
    result.addAll(node.getExpression().accept(this));
    result.add(new ValueToken(TokenType.SEMICOLON, ";", node.getExpression().getColumn(), node.getLine()));
    return result;
  }

  @Override
  public List<Token> visit(OperatorNode node) {
    List<Token> result = new ArrayList<>();
    result.add(new ValueToken(TokenType.PARENTHESIS_OPEN, "(", node.getColumn(), node.getLine()));
    result.addAll(node.getLeftNode().accept(this));
    result.add(new ValueToken(TokenType.WHITESPACE, " ", node.getLeftNode().getColumn() + 1,
        node.getLeftNode().getLine()));
    result.add(new ValueToken(TokenType.OPERATOR, node.getOperator(), node.getLeftNode().getColumn() + 1,
        node.getLine()));
    result.add(new ValueToken(TokenType.WHITESPACE, " ", node.getRightNode().getColumn() + 1,
        node.getRightNode().getLine()));
    result.addAll(node.getRightNode().accept(this));
    result.add(new ValueToken(TokenType.PARENTHESIS_CLOSE, ")", node.getRightNode().getColumn(), node.getLine()));
    return result;
  }

  @Override
  public List<Token> visit(VariableNode node) {
    return List.of(node.getToken());
  }

  @Override
  public List<Token> visit(ReassignmentNode node) {
    List<Token> result = new ArrayList<>(node.getVariableNode().accept(this));
    result.add(new ValueToken(TokenType.ASSIGN, "=", node.getVariableNode().getColumn(), node.getLine()));
    result.addAll(node.getExpression().accept(this));
    result.add(new ValueToken(TokenType.SEMICOLON, ";", node.getExpression().getColumn(), node.getLine()));
    return result;
  }

  @Override
  public List<Token> visit(EmptyNode emptyNode) {
    List<Token> result = new ArrayList<>();
    result.add(new ValueToken(TokenType.SEMICOLON, ";", emptyNode.getColumn(), emptyNode.getLine()));
    return result;
  }

  @Override
  public List<Token> visit(ReadInputNode readInputNode) {
    return null;
  }

  @Override
  public List<Token> visit(ReadEnvNode readEnvNode) {
    return null;
  }

  @Override
  public List<Token> visit(IfNode ifNode) {
    List<Token> result = new ArrayList<>();
    result.add(new ValueToken(TokenType.IF_KEYWORD, "if", ifNode.getColumn(), ifNode.getLine()));
    result.add(new ValueToken(TokenType.WHITESPACE, " ", ifNode.getColumn() + 1, ifNode.getLine()));
    result.add(new ValueToken(TokenType.PARENTHESIS_OPEN, "(", ifNode.getColumn() + 2, ifNode.getLine()));
    result.addAll(ifNode.getCondition().accept(this));
    result.add(new ValueToken(TokenType.PARENTHESIS_CLOSE, ")", ifNode.getCondition().getColumn() + 1,
        ifNode.getLine()));
    result.add(new ValueToken(TokenType.BRACKET_OPEN, "{", ifNode.getCondition().getColumn() + 2,
        ifNode.getLine()));
    result.addAll(ifNode.getIfBlock().accept(this));
    result.add(new ValueToken(TokenType.BRACKET_CLOSE, "}", ifNode.getIfBlock().getColumn() + 1,
        ifNode.getLine()));
    result.add(new ValueToken(TokenType.WHITESPACE, " ", ifNode.getIfBlock().getColumn() + 2,
        ifNode.getLine()));
    if (ifNode.getElseBlock() != null) {
      result.add(new ValueToken(TokenType.ELSE_KEYWORD, "else", ifNode.getIfBlock().getColumn() + 3,
          ifNode.getLine()));
      result.add(new ValueToken(TokenType.WHITESPACE, " ", ifNode.getIfBlock().getColumn() + 7,
          ifNode.getLine()));
      result.add(new ValueToken(TokenType.BRACKET_OPEN, "{", ifNode.getIfBlock().getColumn() + 8,
          ifNode.getLine()));
      result.addAll(ifNode.getElseBlock().accept(this));
      result.add(new ValueToken(TokenType.BRACKET_CLOSE, "}", ifNode.getElseBlock().getColumn() + 1,
          ifNode.getLine()));
    }
    return result;
  }

  @Override
  public List<Token> visit(BlockNode blockNode) {
    List<Token> result = new ArrayList<>();
    for (ASTNode node : blockNode.getStatements()) {
      result.addAll(node.accept(this));
    }
    return result;
  }
}