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
import ast.tokens.AstToken;
import ast.tokens.AstTokenType;
import formatter.AstMap;
import formatter.nodeformatter.NodeFormatter;
import formatter.rules.Rule;
import formatter.rules.conditional.IndentationInsideIf;
import java.util.ArrayList;
import java.util.List;

public class TokenListFactory implements AstVisitor<List<AstToken>> {
  private List<Rule> rules;
  private final TokenFactory tokenFactory = new TokenFactory();

  @Override
  public List<AstToken> visit(DeclarationNode node) {
    List<AstToken> result = new ArrayList<>();
    result.add(node.getDeclarationKeyWord());
    result.add(
        tokenFactory.createToken(
            AstTokenType.WHITESPACE,
            " ",
            node.getDeclarationKeyWord().getColumn() + 1,
            node.getDeclarationKeyWord().getLine()));
    result.add(node.getNameToken());
    result.add(
        tokenFactory.createToken(
            AstTokenType.COLON,
            ":",
            node.getNameToken().getColumn() + 1,
            node.getNameToken().getLine()));
    result.add(node.getTypeToken());
    return result;
  }

  @Override
  public List<AstToken> visit(LiteralNode node) {
    return List.of(node.getToken());
  }

  @Override
  public List<AstToken> visit(PrintNode node) {
    List<AstToken> result = new ArrayList<>();
    result.add(
        tokenFactory.createToken(
            AstTokenType.PRINT_KEYWORD, "println", node.getColumn(), node.getLine()));
    result.add(
        tokenFactory.createToken(
            AstTokenType.PARENTHESIS_OPEN, "(", node.getLine(), node.getColumn()));
    result.addAll(node.getExpression().accept(this));
    result.add(
        tokenFactory.createToken(
            AstTokenType.PARENTHESIS_CLOSE, ")", node.getLine(), node.getColumn()));
    result.add(
        tokenFactory.createToken(
            AstTokenType.SEMICOLON, ";", node.getExpression().getColumn(), node.getLine()));
    return result;
  }

  @Override
  public List<AstToken> visit(AssignationNode node) {
    List<AstToken> result = new ArrayList<>(node.getDeclaration().accept(this));
    if (!(node.getExpression() instanceof EmptyNode)) {
      result.add(tokenFactory.createToken(AstTokenType.ASSIGN, "=", node.getColumn(), node.getLine()));
    }
    result.addAll(node.getExpression().accept(this));
    if (!(node.getExpression() instanceof ReadInputNode)
        && !(node.getExpression() instanceof ReadEnvNode)) {
      result.add(tokenFactory.createToken(AstTokenType.SEMICOLON, ";", result.size(), node.getLine()));
    }
    return result;
  }

  @Override
  public List<AstToken> visit(OperatorNode node) {
    List<AstToken> result = new ArrayList<>(node.getLeftNode().accept(this));
    result.add(
        tokenFactory.createToken(
            AstTokenType.WHITESPACE,
            " ",
            node.getLeftNode().getColumn() + 1,
            node.getLeftNode().getLine()));
    result.add(
        tokenFactory.createToken(
            AstTokenType.OPERATOR,
            node.getOperator(),
            node.getLeftNode().getColumn() + 1,
            node.getLine()));
    result.add(
        tokenFactory.createToken(
            AstTokenType.WHITESPACE,
            " ",
            node.getRightNode().getColumn() + 1,
            node.getRightNode().getLine()));
    result.addAll(node.getRightNode().accept(this));
    return result;
  }

  @Override
  public List<AstToken> visit(VariableNode node) {
    return List.of(node.getToken());
  }

  @Override
  public List<AstToken> visit(ReassignmentNode node) {
    List<AstToken> result = new ArrayList<>(node.getVariableNode().accept(this));
    result.add(
        tokenFactory.createToken(
            AstTokenType.ASSIGN, "=", node.getVariableNode().getColumn(), node.getLine()));
    result.addAll(node.getExpression().accept(this));
    result.add(
        tokenFactory.createToken(
            AstTokenType.SEMICOLON, ";", node.getExpression().getColumn(), node.getLine()));
    return result;
  }

  @Override
  public List<AstToken> visit(EmptyNode emptyNode) {
    return List.of();
  }

  @Override
  public List<AstToken> visit(ReadInputNode readInputNode) {
    List<AstToken> result = new ArrayList<>();
    result.add(
        tokenFactory.createToken(
            AstTokenType.READ_INPUT, "readInput", readInputNode.getColumn(), readInputNode.getLine()));
    result.add(
        tokenFactory.createToken(
            AstTokenType.PARENTHESIS_OPEN,
            "(",
            readInputNode.getColumn() + 4,
            readInputNode.getLine()));
    result.addAll(readInputNode.getExpression().accept(this));
    result.add(
        tokenFactory.createToken(
            AstTokenType.PARENTHESIS_CLOSE,
            ")",
            readInputNode.getExpression().getColumn() + 1,
            readInputNode.getLine()));
    result.add(
        tokenFactory.createToken(
            AstTokenType.SEMICOLON,
            ";",
            readInputNode.getExpression().getColumn(),
            readInputNode.getLine()));
    return result;
  }

  @Override
  public List<AstToken> visit(ReadEnvNode readEnvNode) {
    List<AstToken> result = new ArrayList<>();
    result.add(
        tokenFactory.createToken(
            AstTokenType.READ_ENV, "readEnv", readEnvNode.getColumn(), readEnvNode.getLine()));
    result.add(
        tokenFactory.createToken(
            AstTokenType.PARENTHESIS_OPEN, "(", readEnvNode.getColumn() + 7, readEnvNode.getLine()));
    result.addAll(readEnvNode.getExpression().accept(this));
    result.add(
        tokenFactory.createToken(
            AstTokenType.PARENTHESIS_CLOSE,
            ")",
            readEnvNode.getExpression().getColumn() + 1,
            readEnvNode.getLine()));
    result.add(
        tokenFactory.createToken(
            AstTokenType.SEMICOLON,
            ";",
            readEnvNode.getExpression().getColumn(),
            readEnvNode.getLine()));
    return result;
  }

  @Override
  public List<AstToken> visit(IfNode ifNode) {
    List<AstToken> result = new ArrayList<>();
    result.add(
        tokenFactory.createToken(AstTokenType.IF_KEYWORD, "if", ifNode.getColumn(), ifNode.getLine()));
    result.add(
        tokenFactory.createToken(
            AstTokenType.WHITESPACE, " ", ifNode.getColumn() + 1, ifNode.getLine()));
    result.add(
        tokenFactory.createToken(
            AstTokenType.PARENTHESIS_OPEN, "(", ifNode.getColumn() + 2, ifNode.getLine()));
    result.addAll(ifNode.getCondition().accept(this));
    result.add(
        tokenFactory.createToken(
            AstTokenType.PARENTHESIS_CLOSE,
            ")",
            ifNode.getCondition().getColumn() + 1,
            ifNode.getLine()));
    result.add(
        tokenFactory.createToken(
            AstTokenType.BRACE_OPEN, "{", ifNode.getCondition().getColumn() + 2, ifNode.getLine()));
    result.add(
        tokenFactory.createToken(
            AstTokenType.LINE_BREAK, "\n", ifNode.getColumn() + 1, ifNode.getLine()));
    result.addAll(ifNode.getIfBlock().accept(this));
    result.add(tokenFactory.createToken(AstTokenType.BRACE_CLOSE, "}", 2, ifNode.getLine() + 1));
    if (!ifNode.getElseBlock().getStatements().isEmpty()) {
      result.add(
          tokenFactory.createToken(
              AstTokenType.WHITESPACE, " ", ifNode.getIfBlock().getColumn() + 2, ifNode.getLine()));
      result.add(
          tokenFactory.createToken(
              AstTokenType.ELSE_KEYWORD,
              "else",
              ifNode.getIfBlock().getColumn() + 3,
              ifNode.getLine()));
      result.add(
          tokenFactory.createToken(
              AstTokenType.WHITESPACE, " ", ifNode.getIfBlock().getColumn() + 7, ifNode.getLine()));
      result.add(
          tokenFactory.createToken(
              AstTokenType.BRACE_OPEN, "{", ifNode.getIfBlock().getColumn() + 8, ifNode.getLine()));
      result.addAll(ifNode.getElseBlock().accept(this));
      result.add(
          tokenFactory.createToken(
              AstTokenType.BRACE_CLOSE, "}", ifNode.getElseBlock().getColumn() + 1, ifNode.getLine()));
    }
    return result;
  }

  @Override
  public List<AstToken> visit(BlockNode blockNode) {
    List<AstToken> result = new ArrayList<>();
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
