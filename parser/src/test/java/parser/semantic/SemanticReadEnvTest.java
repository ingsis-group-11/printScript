package parser.semantic;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import ast.nodes.AssignationNode;
import ast.nodes.AstNode;
import ast.nodes.DeclarationNode;
import ast.nodes.LiteralNode;
import ast.nodes.ReadEnvNode;
import ast.tokens.AstTokenType;
import ast.tokens.ValueAstToken;
import org.junit.jupiter.api.Test;
import parser.semantic.result.SemanticResult;

public class SemanticReadEnvTest {
  @Test
  public void testValidSemanticReadEnv() {
    // let var: string = readEnv("TEST");
    AstNode assignmentNode =
        new AssignationNode(
            new DeclarationNode(
                new ValueAstToken(AstTokenType.STRING_TYPE, "string", 10, 0),
                new ValueAstToken(AstTokenType.IDENTIFIER, "var", 4, 0),
                new ValueAstToken(AstTokenType.LET_KEYWORD, "let", 20, 1),
                1,
                0),
            new ReadEnvNode(
                new LiteralNode(new ValueAstToken(AstTokenType.STRING, "TEST", 19, 0)), 1, 1),
            1,
            1);
    SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
    SemanticResult semanticError = semanticAnalyzer.analyze(assignmentNode);
    assertFalse(semanticError.hasErrors());
  }

  @Test
  public void testInvalidSemanticReadEnv() {
    // let var: number = readEnv("TEST");
    AstNode assignmentNode =
        new AssignationNode(
            new DeclarationNode(
                new ValueAstToken(AstTokenType.NUMBER_TYPE, "number", 10, 0),
                new ValueAstToken(AstTokenType.IDENTIFIER, "var", 4, 0),
                new ValueAstToken(AstTokenType.LET_KEYWORD, "let", 20, 1),
                1,
                0),
            new ReadEnvNode(
                new LiteralNode(new ValueAstToken(AstTokenType.STRING, "TEST", 19, 0)), 1, 1),
            1,
            1);
    SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
    assertThrows(RuntimeException.class, () -> semanticAnalyzer.analyze(assignmentNode));
  }
}
