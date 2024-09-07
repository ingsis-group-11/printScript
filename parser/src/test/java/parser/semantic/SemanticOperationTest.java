package parser.semantic;

import AST.nodes.*;
import org.junit.jupiter.api.Test;
import parser.semantic.result.SemanticResult;
import token.TokenType;
import token.ValueToken;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SemanticOperationTest {
  @Test
  public void validStringAssignationOperationTest() {
    ASTNode assignmentNode =
            new AssignationNode(
                    new DeclarationNode(
                            new ValueToken(TokenType.STRING_TYPE, "string", 10, 1),
                            new ValueToken(TokenType.IDENTIFIER, "name", 4, 1),
                            new ValueToken(TokenType.LET_KEYWORD, "let", 20, 1),
                            1,
                            1),

                    new OperatorNode("+",
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
    ASTNode assignmentNode =
            new AssignationNode(
                    new DeclarationNode(
                            new ValueToken(TokenType.NUMBER_TYPE, "number", 10, 1),
                            new ValueToken(TokenType.IDENTIFIER, "sum", 4, 1),
                        new ValueToken(TokenType.LET_KEYWORD, "let", 20, 1),

                            1,
                            1),
                    new OperatorNode("+",
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
    ASTNode assignmentNode =
            new AssignationNode(
                    new DeclarationNode(
                            new ValueToken(TokenType.STRING_TYPE, "string", 10, 1),
                            new ValueToken(TokenType.IDENTIFIER, "name", 4, 1),
                        new ValueToken(TokenType.LET_KEYWORD, "let", 20, 1),
                            1,
                            1),
                    new OperatorNode("*",
                            new LiteralNode(new ValueToken(TokenType.NUMBER, "5", 20, 1)),
                            new LiteralNode(new ValueToken(TokenType.NUMBER, "5", 29, 1)),
                            1,
                            20),
                    1,
                    1);
    SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
    SemanticResult semanticError = semanticAnalyzer.analyze(assignmentNode);
    assertTrue(semanticError.hasErrors());
  }

  @Test
  public void invalidNumberAssignationOperationTest() {
    ASTNode assignmentNode =
            new AssignationNode(
                    new DeclarationNode(
                            new ValueToken(TokenType.NUMBER_TYPE, "number", 10, 1),
                            new ValueToken(TokenType.IDENTIFIER, "sum", 4, 1),
                        new ValueToken(TokenType.LET_KEYWORD, "let", 20, 1),
                            1,
                            1),
                    new OperatorNode("+",
                            new LiteralNode(new ValueToken(TokenType.STRING, "Hello", 20, 1)),
                            new LiteralNode(new ValueToken(TokenType.NUMBER, "5", 24, 1)),
                            1,
                            20),
                    1,
                    1);
    SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
    SemanticResult semanticError = semanticAnalyzer.analyze(assignmentNode);
    assertTrue(semanticError.hasErrors());
  }

  @Test
  public void validNumberPrintOperationTest() {
    ASTNode assignmentNode =
            new PrintNode(
                    new OperatorNode("+",
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
    ASTNode assignmentNode =
            new PrintNode(
                    new OperatorNode("%",
                            new LiteralNode(new ValueToken(TokenType.NUMBER, "5", 20, 1)),
                            new LiteralNode(new ValueToken(TokenType.NUMBER, "5", 24, 1)),
                            1,
                            20),
                    1,
                    1);
    SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
    SemanticResult semanticError = semanticAnalyzer.analyze(assignmentNode);
    assertTrue(semanticError.hasErrors());
  }

  @Test
  public void validStringPrintOperationTest() {
    ASTNode assignmentNode =
            new PrintNode(
                    new OperatorNode("+",
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
    ASTNode assignmentNode =
            new PrintNode(
                    new OperatorNode("-",
                            new LiteralNode(new ValueToken(TokenType.STRING, "Hello ", 20, 1)),
                            new LiteralNode(new ValueToken(TokenType.STRING, "World", 24, 1)),
                            1,
                            20),
                    1,
                    1);
    SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
    SemanticResult semanticError = semanticAnalyzer.analyze(assignmentNode);
    assertTrue(semanticError.hasErrors());
  }

  @Test
  public void validVariablePrintOperationTest() {
    ASTNode assignmentNode =
            new PrintNode(
                    new OperatorNode("+",
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
  public void validVariableNumberPrintOperationTest() {
    ASTNode assignmentNode =
            new PrintNode(
                    new OperatorNode("-",
                            new LiteralNode(new ValueToken(TokenType.IDENTIFIER, "a", 20, 1)),
                            new LiteralNode(new ValueToken(TokenType.NUMBER, "10", 24, 1)),
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
    ASTNode assignmentNode =
            new PrintNode(
                    new OperatorNode("+",
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
    ASTNode assignmentNode =
            new PrintNode(
                    new OperatorNode("-",
                            new LiteralNode(new ValueToken(TokenType.IDENTIFIER, "a", 20, 1)),
                            new LiteralNode(new ValueToken(TokenType.STRING, "Hello", 24, 1)),
                            1,
                            20),
                    1,
                    1);
    SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
    SemanticResult semanticError = semanticAnalyzer.analyze(assignmentNode);
    assertTrue(semanticError.hasErrors());
  }


}
