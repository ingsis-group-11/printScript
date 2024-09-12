package formatter.tokenfactory;

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
import formatter.AstMap;
import formatter.nodeformatter.NodeFormatter;
import formatter.rules.Rule;
import formatter.rules.conditional.IndentationInsideIf;
import java.util.ArrayList;
import java.util.List;
import token.Token;
import token.TokenType;

public class TokenListFactory implements AstVisitor<List<Token>> {
  private List<Rule> rules;
  private final TokenFactory tokenFactory = new TokenFactory();

  @Override
  public List<Token> visit(DeclarationNode node) {
    List<Token> result = new ArrayList<>();
    result.add(node.getDeclarationKeyWord());
    result.add(
        tokenFactory.createToken(
            TokenType.WHITESPACE,
            " ",
            node.getDeclarationKeyWord().getColumn() + 1,
            node.getDeclarationKeyWord().getLine()));
    result.add(node.getNameToken());
    result.add(
        tokenFactory.createToken(
            TokenType.COLON,
            ":",
            node.getNameToken().getColumn() + 1,
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
    result.add(
        tokenFactory.createToken(
            TokenType.PRINT_KEYWORD, "println", node.getColumn(), node.getLine()));
    result.add(
        tokenFactory.createToken(
            TokenType.PARENTHESIS_OPEN, "(", node.getLine(), node.getColumn()));
    result.addAll(node.getExpression().accept(this));
    result.add(
        tokenFactory.createToken(
            TokenType.PARENTHESIS_CLOSE, ")", node.getLine(), node.getColumn()));
    result.add(
        tokenFactory.createToken(
            TokenType.SEMICOLON, ";", node.getExpression().getColumn(), node.getLine()));
    return result;
  }

  @Override
  public List<Token> visit(AssignationNode node) {
    List<Token> result = new ArrayList<>(node.getDeclaration().accept(this));
    if (!(node.getExpression() instanceof EmptyNode)) {
      result.add(tokenFactory.createToken(TokenType.ASSIGN, "=", node.getColumn(), node.getLine()));
    }
    result.addAll(node.getExpression().accept(this));
    result.add(tokenFactory.createToken(TokenType.SEMICOLON, ";", result.size(), node.getLine()));
    return result;
  }

  @Override
  public List<Token> visit(OperatorNode node) {
    List<Token> result = new ArrayList<>(node.getLeftNode().accept(this));
    result.add(
        tokenFactory.createToken(
            TokenType.WHITESPACE,
            " ",
            node.getLeftNode().getColumn() + 1,
            node.getLeftNode().getLine()));
    result.add(
        tokenFactory.createToken(
            TokenType.OPERATOR,
            node.getOperator(),
            node.getLeftNode().getColumn() + 1,
            node.getLine()));
    result.add(
        tokenFactory.createToken(
            TokenType.WHITESPACE,
            " ",
            node.getRightNode().getColumn() + 1,
            node.getRightNode().getLine()));
    result.addAll(node.getRightNode().accept(this));
    return result;
  }

  @Override
  public List<Token> visit(VariableNode node) {
    return List.of(node.getToken());
  }

  @Override
  public List<Token> visit(ReassignmentNode node) {
    List<Token> result = new ArrayList<>(node.getVariableNode().accept(this));
    result.add(
        tokenFactory.createToken(
            TokenType.ASSIGN, "=", node.getVariableNode().getColumn(), node.getLine()));
    result.addAll(node.getExpression().accept(this));
    result.add(
        tokenFactory.createToken(
            TokenType.SEMICOLON, ";", node.getExpression().getColumn(), node.getLine()));
    return result;
  }

  @Override
  public List<Token> visit(EmptyNode emptyNode) {
    return List.of();
  }

  @Override
  public List<Token> visit(ReadInputNode readInputNode) {
    List<Token> result = new ArrayList<>();
    result.add(
        tokenFactory.createToken(
            TokenType.READ_INPUT, "readInput", readInputNode.getColumn(), readInputNode.getLine()));
    result.add(
        tokenFactory.createToken(
            TokenType.PARENTHESIS_OPEN,
            "(",
            readInputNode.getColumn() + 4,
            readInputNode.getLine()));
    result.addAll(readInputNode.getExpression().accept(this));
    result.add(
        tokenFactory.createToken(
            TokenType.PARENTHESIS_CLOSE,
            ")",
            readInputNode.getExpression().getColumn() + 1,
            readInputNode.getLine()));
    result.add(
        tokenFactory.createToken(
            TokenType.SEMICOLON,
            ";",
            readInputNode.getExpression().getColumn(),
            readInputNode.getLine()));
    return result;
  }

  @Override
  public List<Token> visit(ReadEnvNode readEnvNode) {
    List<Token> result = new ArrayList<>();
    result.add(
        tokenFactory.createToken(
            TokenType.READ_ENV, "readEnv", readEnvNode.getColumn(), readEnvNode.getLine()));
    result.add(
        tokenFactory.createToken(
            TokenType.PARENTHESIS_OPEN, "(", readEnvNode.getColumn() + 7, readEnvNode.getLine()));
    result.addAll(readEnvNode.getExpression().accept(this));
    result.add(
        tokenFactory.createToken(
            TokenType.PARENTHESIS_CLOSE,
            ")",
            readEnvNode.getExpression().getColumn() + 1,
            readEnvNode.getLine()));
    result.add(
        tokenFactory.createToken(
            TokenType.SEMICOLON,
            ";",
            readEnvNode.getExpression().getColumn(),
            readEnvNode.getLine()));
    return result;
  }

  @Override
  public List<Token> visit(IfNode ifNode) {
    List<Token> result = new ArrayList<>();
    result.add(
        tokenFactory.createToken(TokenType.IF_KEYWORD, "if", ifNode.getColumn(), ifNode.getLine()));
    result.add(
        tokenFactory.createToken(
            TokenType.WHITESPACE, " ", ifNode.getColumn() + 1, ifNode.getLine()));
    result.add(
        tokenFactory.createToken(
            TokenType.PARENTHESIS_OPEN, "(", ifNode.getColumn() + 2, ifNode.getLine()));
    result.addAll(ifNode.getCondition().accept(this));
    result.add(
        tokenFactory.createToken(
            TokenType.PARENTHESIS_CLOSE,
            ")",
            ifNode.getCondition().getColumn() + 1,
            ifNode.getLine()));
    result.add(
        tokenFactory.createToken(
            TokenType.BRACE_OPEN, "{", ifNode.getCondition().getColumn() + 2, ifNode.getLine()));
    result.add(
        tokenFactory.createToken(
            TokenType.LINE_BREAK, "\n", ifNode.getColumn() + 1, ifNode.getLine()));
    result.addAll(ifNode.getIfBlock().accept(this));
    result.add(tokenFactory.createToken(TokenType.BRACE_CLOSE, "}", 2, ifNode.getLine() + 1));
    if (!ifNode.getElseBlock().getStatements().isEmpty()) {
      result.add(
          tokenFactory.createToken(
              TokenType.WHITESPACE, " ", ifNode.getIfBlock().getColumn() + 2, ifNode.getLine()));
      result.add(
          tokenFactory.createToken(
              TokenType.ELSE_KEYWORD,
              "else",
              ifNode.getIfBlock().getColumn() + 3,
              ifNode.getLine()));
      result.add(
          tokenFactory.createToken(
              TokenType.WHITESPACE, " ", ifNode.getIfBlock().getColumn() + 7, ifNode.getLine()));
      result.add(
          tokenFactory.createToken(
              TokenType.BRACE_OPEN, "{", ifNode.getIfBlock().getColumn() + 8, ifNode.getLine()));
      result.addAll(ifNode.getElseBlock().accept(this));
      result.add(
          tokenFactory.createToken(
              TokenType.BRACE_CLOSE, "}", ifNode.getElseBlock().getColumn() + 1, ifNode.getLine()));
    }
    return result;
  }

  @Override
  public List<Token> visit(BlockNode blockNode) {
    List<Token> result = new ArrayList<>();
    List<Rule> newRules = removeIndentationRule(this.rules);
    AstMap nodeMap = new AstMap();
    for (AstNode node : blockNode.getStatements()) {
      NodeFormatter nodeFormatter = nodeMap.getNodeFormatter(node);
      result.addAll(nodeFormatter.formatToken(node.accept(this), newRules));
    }
    return result;
  }

  private List<Rule> removeIndentationRule(List<Rule> rules) {
    List<Rule> result = new ArrayList<>(rules);
    for (Rule rule : rules) {
      if (rule instanceof IndentationInsideIf) {
        result.remove(rule);
      }
    }
    return result;
  }

  public void addRules(List<Rule> rules) {
    this.rules = rules;
  }
}
