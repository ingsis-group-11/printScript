package parser.semantic;

import AST.nodes.*;
import org.junit.jupiter.api.Test;
import parser.semantic.result.SemanticResult;
import token.TokenType;
import token.ValueToken;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SemanticOperationTest {
  @Test
  public void validStringAssignationOperationTest() {
    List<ASTNode> trees = new ArrayList<>();
    ASTNode assignmentNode =
            new AssignationNode(
                    new DeclarationNode(
                            new ValueToken(TokenType.STRING_TYPE, "string", 10, 1),
                            new ValueToken(TokenType.IDENTIFIER, "name", 4, 1),
                            1,
                            1),
                    new OperatorNode("+",
                            new LiteralNode(new ValueToken(TokenType.STRING, "Olive", 20, 1)),
                            new LiteralNode(new ValueToken(TokenType.STRING, "Olive", 29, 1)),
                            1,
                            20),
                    1,
                    1);
    trees.add(assignmentNode);
    SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
    SemanticResult semanticError = semanticAnalyzer.analyze(trees);
    assertFalse(semanticError.hasErrors());
  }

  @Test
  public void validNumberAssignationOperationTest() {
    List<ASTNode> trees = new ArrayList<>();
    ASTNode assignmentNode =
            new AssignationNode(
                    new DeclarationNode(
                            new ValueToken(TokenType.NUMBER_TYPE, "number", 10, 1),
                            new ValueToken(TokenType.IDENTIFIER, "sum", 4, 1),
                            1,
                            1),
                    new OperatorNode("+",
                            new LiteralNode(new ValueToken(TokenType.NUMBER, "5", 20, 1)),
                            new LiteralNode(new ValueToken(TokenType.NUMBER, "5", 24, 1)),
                            1,
                            20),
                    1,
                    1);
    trees.add(assignmentNode);
    SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
    SemanticResult semanticError = semanticAnalyzer.analyze(trees);
    assertFalse(semanticError.hasErrors());
  }

  @Test
  public void invalidStringAssignationOperationTest() {
    List<ASTNode> trees = new ArrayList<>();
    ASTNode assignmentNode =
            new AssignationNode(
                    new DeclarationNode(
                            new ValueToken(TokenType.STRING_TYPE, "string", 10, 1),
                            new ValueToken(TokenType.IDENTIFIER, "name", 4, 1),
                            1,
                            1),
                    new OperatorNode("*",
                            new LiteralNode(new ValueToken(TokenType.NUMBER, "5", 20, 1)),
                            new LiteralNode(new ValueToken(TokenType.NUMBER, "5", 29, 1)),
                            1,
                            20),
                    1,
                    1);
    trees.add(assignmentNode);
    SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
    SemanticResult semanticError = semanticAnalyzer.analyze(trees);
    assertTrue(semanticError.hasErrors());
  }

  @Test
  public void invalidNumberAssignationOperationTest() {
    List<ASTNode> trees = new ArrayList<>();
    ASTNode assignmentNode =
            new AssignationNode(
                    new DeclarationNode(
                            new ValueToken(TokenType.NUMBER_TYPE, "number", 10, 1),
                            new ValueToken(TokenType.IDENTIFIER, "sum", 4, 1),
                            1,
                            1),
                    new OperatorNode("+",
                            new LiteralNode(new ValueToken(TokenType.STRING, "Hello", 20, 1)),
                            new LiteralNode(new ValueToken(TokenType.NUMBER, "5", 24, 1)),
                            1,
                            20),
                    1,
                    1);
    trees.add(assignmentNode);
    SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
    SemanticResult semanticError = semanticAnalyzer.analyze(trees);
    assertTrue(semanticError.hasErrors());
  }

  @Test
  public void validNumberPrintOperationTest() {
    List<ASTNode> trees = new ArrayList<>();
    ASTNode assignmentNode =
            new PrintNode(
                    new OperatorNode("+",
                            new LiteralNode(new ValueToken(TokenType.NUMBER, "20", 20, 1)),
                            new LiteralNode(new ValueToken(TokenType.NUMBER, "5", 24, 1)),
                            1,
                            20),
                    1,
                    1);
    trees.add(assignmentNode);
    SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
    SemanticResult semanticError = semanticAnalyzer.analyze(trees);
    assertFalse(semanticError.hasErrors());
  }

  @Test
  public void invalidNumberPrintOperationTest() {
    List<ASTNode> trees = new ArrayList<>();
    ASTNode assignmentNode =
            new PrintNode(
                    new OperatorNode("%",
                            new LiteralNode(new ValueToken(TokenType.NUMBER, "5", 20, 1)),
                            new LiteralNode(new ValueToken(TokenType.NUMBER, "5", 24, 1)),
                            1,
                            20),
                    1,
                    1);
    trees.add(assignmentNode);
    SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
    SemanticResult semanticError = semanticAnalyzer.analyze(trees);
    assertTrue(semanticError.hasErrors());
  }

  @Test
  public void validStringPrintOperationTest() {
    List<ASTNode> trees = new ArrayList<>();
    ASTNode assignmentNode =
            new PrintNode(
                    new OperatorNode("+",
                            new LiteralNode(new ValueToken(TokenType.NUMBER, "Hello ", 20, 1)),
                            new LiteralNode(new ValueToken(TokenType.NUMBER, "World", 24, 1)),
                            1,
                            20),
                    1,
                    1);
    trees.add(assignmentNode);
    SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
    SemanticResult semanticError = semanticAnalyzer.analyze(trees);
    assertFalse(semanticError.hasErrors());
  }

  @Test
  public void invalidStringPrintOperationTest() {
    List<ASTNode> trees = new ArrayList<>();
    ASTNode assignmentNode =
            new PrintNode(
                    new OperatorNode("-",
                            new LiteralNode(new ValueToken(TokenType.STRING, "Hello ", 20, 1)),
                            new LiteralNode(new ValueToken(TokenType.STRING, "World", 24, 1)),
                            1,
                            20),
                    1,
                    1);
    trees.add(assignmentNode);
    SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
    SemanticResult semanticError = semanticAnalyzer.analyze(trees);
    assertTrue(semanticError.hasErrors());
  }

  @Test
  public void validVariablePrintOperationTest() {
    List<ASTNode> trees = new ArrayList<>();
    ASTNode assignmentNode =
            new PrintNode(
                    new OperatorNode("+",
                            new LiteralNode(new ValueToken(TokenType.IDENTIFIER, "a", 20, 1)),
                            new LiteralNode(new ValueToken(TokenType.IDENTIFIER, "b", 24, 1)),
                            1,
                            20),
                    1,
                    1);
    trees.add(assignmentNode);
    SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
    SemanticResult semanticError = semanticAnalyzer.analyze(trees);
    assertFalse(semanticError.hasErrors());
  }

  @Test
  public void validVariableNumberPrintOperationTest() {
    List<ASTNode> trees = new ArrayList<>();
    ASTNode assignmentNode =
            new PrintNode(
                    new OperatorNode("-",
                            new LiteralNode(new ValueToken(TokenType.IDENTIFIER, "a", 20, 1)),
                            new LiteralNode(new ValueToken(TokenType.NUMBER, "10", 24, 1)),
                            1,
                            20),
                    1,
                    1);
    trees.add(assignmentNode);
    SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
    SemanticResult semanticError = semanticAnalyzer.analyze(trees);
    assertFalse(semanticError.hasErrors());
  }

  @Test
  public void validVariableStringPrintOperationTest() {
    List<ASTNode> trees = new ArrayList<>();
    ASTNode assignmentNode =
            new PrintNode(
                    new OperatorNode("+",
                            new LiteralNode(new ValueToken(TokenType.IDENTIFIER, "a", 20, 1)),
                            new LiteralNode(new ValueToken(TokenType.STRING, "Hello", 24, 1)),
                            1,
                            20),
                    1,
                    1);
    trees.add(assignmentNode);
    SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
    SemanticResult semanticError = semanticAnalyzer.analyze(trees);
    assertFalse(semanticError.hasErrors());
  }

  @Test
  public void invalidVariableStringPrintOperationTest() {
    List<ASTNode> trees = new ArrayList<>();
    ASTNode assignmentNode =
            new PrintNode(
                    new OperatorNode("-",
                            new LiteralNode(new ValueToken(TokenType.IDENTIFIER, "a", 20, 1)),
                            new LiteralNode(new ValueToken(TokenType.STRING, "Hello", 24, 1)),
                            1,
                            20),
                    1,
                    1);
    trees.add(assignmentNode);
    SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
    SemanticResult semanticError = semanticAnalyzer.analyze(trees);
    assertTrue(semanticError.hasErrors());
  }


}
