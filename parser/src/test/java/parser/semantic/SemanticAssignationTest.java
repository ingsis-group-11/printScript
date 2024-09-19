package parser.semantic;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import ast.nodes.AssignationNode;
import ast.nodes.AstNode;
import ast.nodes.DeclarationNode;
import ast.nodes.LiteralNode;
import ast.tokens.AstTokenType;
import ast.tokens.ValueAstToken;
import org.junit.jupiter.api.Test;
import parser.semantic.result.SemanticResult;
import token.TokenType;
import token.ValueToken;

public class SemanticAssignationTest {
  @Test
  public void validStringAssignationTest() {
    AstNode assignmentNode =
        new AssignationNode(
            new DeclarationNode(
                new ValueAstToken(AstTokenType.STRING_TYPE, "string", 10, 0),
                new ValueAstToken(AstTokenType.IDENTIFIER, "name", 4, 0),
                new ValueAstToken(AstTokenType.LET_KEYWORD, "let", 20, 1),
                1,
                0),
            new LiteralNode(new ValueAstToken(AstTokenType.STRING, "Olive", 19, 0)),
            1,
            1);
    SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
    SemanticResult semanticError = semanticAnalyzer.analyze(assignmentNode);
    assertFalse(semanticError.hasErrors());
  }

  @Test
  public void validNumberAssignationTest() {
    AstNode assignmentNode =
        new AssignationNode(
            new DeclarationNode(
                new ValueAstToken(AstTokenType.NUMBER_TYPE, "number", 10, 0),
                new ValueAstToken(AstTokenType.IDENTIFIER, "num", 4, 0),
                new ValueAstToken(AstTokenType.LET_KEYWORD, "let", 20, 1),
                1,
                0),
            new LiteralNode(new ValueAstToken(AstTokenType.NUMBER, "10", 19, 0)),
            1,
            1);
    SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
    SemanticResult semanticError = semanticAnalyzer.analyze(assignmentNode);
    assertFalse(semanticError.hasErrors());
  }

  @Test
  public void invalidStringAssignationTest() {
    AstNode assignmentNode =
        new AssignationNode(
            new DeclarationNode(
                new ValueAstToken(AstTokenType.NUMBER_TYPE, "number", 10, 0),
                new ValueAstToken(AstTokenType.IDENTIFIER, "name", 4, 0),
                new ValueAstToken(AstTokenType.LET_KEYWORD, "let", 20, 1),
                1,
                0),
            new LiteralNode(new ValueAstToken(AstTokenType.STRING, "Olive", 19, 0)),
            1,
            1);
    SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
    assertThrows(RuntimeException.class, () -> semanticAnalyzer.analyze(assignmentNode));
  }

  @Test
  public void invalidNumberAssignationTest() {
    AstNode assignmentNode =
        new AssignationNode(
            new DeclarationNode(
                new ValueAstToken(AstTokenType.STRING_TYPE, "string", 10, 0),
                new ValueAstToken(AstTokenType.IDENTIFIER, "num", 4, 0),
                new ValueAstToken(AstTokenType.LET_KEYWORD, "let", 20, 1),
                1,
                0),
            new LiteralNode(new ValueAstToken(AstTokenType.NUMBER, "10", 19, 0)),
            1,
            1);
    SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
    assertThrows(RuntimeException.class, () -> semanticAnalyzer.analyze(assignmentNode));
  }

  @Test
  public void validVariableAssignationTest() {
    AstNode assignmentNode =
        new AssignationNode(
            new DeclarationNode(
                new ValueAstToken(AstTokenType.NUMBER_TYPE, "number", 10, 0),
                new ValueAstToken(AstTokenType.IDENTIFIER, "name", 4, 0),
                new ValueAstToken(AstTokenType.LET_KEYWORD, "let", 20, 1),
                1,
                0),
            new LiteralNode(new ValueAstToken(AstTokenType.IDENTIFIER, "a", 19, 0)),
            1,
            1);
    SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
    SemanticResult semanticError = semanticAnalyzer.analyze(assignmentNode);
    assertFalse(semanticError.hasErrors());
  }
}
