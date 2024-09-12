package parser.semantic;

import static org.junit.jupiter.api.Assertions.*;

import AST.nodes.*;
import org.junit.jupiter.api.Test;
import parser.semantic.result.SemanticResult;
import token.TokenType;
import token.ValueToken;

public class SemanticReadEnvTest {
  @Test
  public void testValidSemanticReadEnv() {
    // let var: string = readEnv("TEST");
    ASTNode assignmentNode =
        new AssignationNode(
            new DeclarationNode(
                new ValueToken(TokenType.STRING_TYPE, "string", 10, 0),
                new ValueToken(TokenType.IDENTIFIER, "var", 4, 0),
                new ValueToken(TokenType.LET_KEYWORD, "let", 20, 1),
                1,
                0),
            new ReadEnvNode(new LiteralNode(new ValueToken(TokenType.STRING, "TEST", 19, 0)), 1, 1),
            1,
            1);
    SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
    SemanticResult semanticError = semanticAnalyzer.analyze(assignmentNode);
    assertFalse(semanticError.hasErrors());
  }

  @Test
  public void testInvalidSemanticReadEnv() {
    // let var: number = readEnv("TEST");
    ASTNode assignmentNode =
        new AssignationNode(
            new DeclarationNode(
                new ValueToken(TokenType.NUMBER_TYPE, "number", 10, 0),
                new ValueToken(TokenType.IDENTIFIER, "var", 4, 0),
                new ValueToken(TokenType.LET_KEYWORD, "let", 20, 1),
                1,
                0),
            new ReadEnvNode(new LiteralNode(new ValueToken(TokenType.STRING, "TEST", 19, 0)), 1, 1),
            1,
            1);
    SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
    assertThrows(RuntimeException.class, () -> semanticAnalyzer.analyze(assignmentNode));
  }
}
