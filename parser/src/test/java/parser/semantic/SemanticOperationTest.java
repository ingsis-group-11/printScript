package parser.semantic;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import ast.nodes.AssignationNode;
import ast.nodes.AstNode;
import ast.nodes.DeclarationNode;
import ast.nodes.LiteralNode;
import ast.nodes.OperatorNode;
import ast.nodes.PrintNode;
import org.junit.jupiter.api.Test;
import parser.semantic.result.SemanticResult;
import token.TokenType;
import token.ValueToken;

public class SemanticOperationTest {
  @Test
  public void validStringAssignationOperationTest() {
    AstNode assignmentNode =
        new AssignationNode(
            new DeclarationNode(
                new ValueToken(TokenType.STRING_TYPE, "string", 10, 1),
                new ValueToken(TokenType.IDENTIFIER, "name", 4, 1),
                new ValueToken(TokenType.LET_KEYWORD, "let", 20, 1),
                1,
                1),
            new OperatorNode(
                "+",
                new LiteralNode(new ValueToken(TokenType.STRING, "Olive", 20, 1)),
                new LiteralNode(new ValueToken(TokenType.STRING, "Olive", 29, 1)),
                1,
                20),
            1,
            1);
    SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
    SemanticResult semanticError = semanticAnalyzer.analyze(assignmentNode);
    assertFalse(semanticError.hasErrors());
  }

  @Test
  public void validNumberAssignationOperationTest() {
    AstNode assignmentNode =
        new AssignationNode(
            new DeclarationNode(
                new ValueToken(TokenType.NUMBER_TYPE, "number", 10, 1),
                new ValueToken(TokenType.IDENTIFIER, "sum", 4, 1),
                new ValueToken(TokenType.LET_KEYWORD, "let", 20, 1),
                1,
                1),
            new OperatorNode(
                "+",
                new LiteralNode(new ValueToken(TokenType.NUMBER, "5", 20, 1)),
                new LiteralNode(new ValueToken(TokenType.NUMBER, "5", 24, 1)),
                1,
                20),
            1,
            1);
    SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
    SemanticResult semanticError = semanticAnalyzer.analyze(assignmentNode);
    assertFalse(semanticError.hasErrors());
  }

  @Test
  public void invalidStringAssignationOperationTest() {
    AstNode assignmentNode =
        new AssignationNode(
            new DeclarationNode(
                new ValueToken(TokenType.STRING_TYPE, "string", 10, 1),
                new ValueToken(TokenType.IDENTIFIER, "name", 4, 1),
                new ValueToken(TokenType.LET_KEYWORD, "let", 20, 1),
                1,
                1),
            new OperatorNode(
                "*",
                new LiteralNode(new ValueToken(TokenType.NUMBER, "5", 20, 1)),
                new LiteralNode(new ValueToken(TokenType.NUMBER, "5", 29, 1)),
                1,
                20),
            1,
            1);
    SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
    assertThrows(RuntimeException.class, () -> semanticAnalyzer.analyze(assignmentNode));
  }

  @Test
  public void invalidNumberAssignationOperationTest() {
    AstNode assignmentNode =
        new AssignationNode(
            new DeclarationNode(
                new ValueToken(TokenType.NUMBER_TYPE, "number", 10, 1),
                new ValueToken(TokenType.IDENTIFIER, "sum", 4, 1),
                new ValueToken(TokenType.LET_KEYWORD, "let", 20, 1),
                1,
                1),
            new OperatorNode(
                "+",
                new LiteralNode(new ValueToken(TokenType.STRING, "Hello", 20, 1)),
                new LiteralNode(new ValueToken(TokenType.NUMBER, "5", 24, 1)),
                1,
                20),
            1,
            1);
    SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
    assertThrows(RuntimeException.class, () -> semanticAnalyzer.analyze(assignmentNode));
  }

  @Test
  public void validNumberPrintOperationTest() {
    AstNode assignmentNode =
        new PrintNode(
            new OperatorNode(
                "+",
                new LiteralNode(new ValueToken(TokenType.NUMBER, "20", 20, 1)),
                new LiteralNode(new ValueToken(TokenType.NUMBER, "5", 24, 1)),
                1,
                20),
            1,
            1);
    SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
    SemanticResult semanticError = semanticAnalyzer.analyze(assignmentNode);
    assertFalse(semanticError.hasErrors());
  }

  @Test
  public void invalidNumberPrintOperationTest() {
    AstNode assignmentNode =
        new PrintNode(
            new OperatorNode(
                "%",
                new LiteralNode(new ValueToken(TokenType.NUMBER, "5", 20, 1)),
                new LiteralNode(new ValueToken(TokenType.NUMBER, "5", 24, 1)),
                1,
                20),
            1,
            1);
    SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
    assertThrows(RuntimeException.class, () -> semanticAnalyzer.analyze(assignmentNode));
  }

  @Test
  public void validStringPrintOperationTest() {
    AstNode assignmentNode =
        new PrintNode(
            new OperatorNode(
                "+",
                new LiteralNode(new ValueToken(TokenType.NUMBER, "Hello ", 20, 1)),
                new LiteralNode(new ValueToken(TokenType.NUMBER, "World", 24, 1)),
                1,
                20),
            1,
            1);
    SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
    SemanticResult semanticError = semanticAnalyzer.analyze(assignmentNode);
    assertFalse(semanticError.hasErrors());
  }

  @Test
  public void invalidStringPrintOperationTest() {
    AstNode assignmentNode =
        new PrintNode(
            new OperatorNode(
                "-",
                new LiteralNode(new ValueToken(TokenType.STRING, "Hello ", 20, 1)),
                new LiteralNode(new ValueToken(TokenType.STRING, "World", 24, 1)),
                1,
                20),
            1,
            1);
    SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
    assertThrows(RuntimeException.class, () -> semanticAnalyzer.analyze(assignmentNode));
  }

  @Test
  public void validVariablePrintOperationTest() {
    AstNode assignmentNode =
        new PrintNode(
            new OperatorNode(
                "+",
                new LiteralNode(new ValueToken(TokenType.IDENTIFIER, "a", 20, 1)),
                new LiteralNode(new ValueToken(TokenType.IDENTIFIER, "b", 24, 1)),
                1,
                20),
            1,
            1);
    SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
    SemanticResult semanticError = semanticAnalyzer.analyze(assignmentNode);
    assertFalse(semanticError.hasErrors());
  }

  @Test
  public void validVariableStringPrintOperationTest() {
    AstNode assignmentNode =
        new PrintNode(
            new OperatorNode(
                "+",
                new LiteralNode(new ValueToken(TokenType.IDENTIFIER, "a", 20, 1)),
                new LiteralNode(new ValueToken(TokenType.STRING, "Hello", 24, 1)),
                1,
                20),
            1,
            1);
    SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
    SemanticResult semanticError = semanticAnalyzer.analyze(assignmentNode);
    assertFalse(semanticError.hasErrors());
  }

  @Test
  public void invalidVariableStringPrintOperationTest() {
    AstNode assignmentNode =
        new PrintNode(
            new OperatorNode(
                "-",
                new LiteralNode(new ValueToken(TokenType.IDENTIFIER, "a", 20, 1)),
                new LiteralNode(new ValueToken(TokenType.STRING, "Hello", 24, 1)),
                1,
                20),
            1,
            1);
    SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
    assertThrows(RuntimeException.class, () -> semanticAnalyzer.analyze(assignmentNode));
  }
}
