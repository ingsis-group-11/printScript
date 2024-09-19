package interpreter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import ast.nodes.AssignationNode;
import ast.nodes.AstNode;
import ast.nodes.DeclarationNode;
import ast.nodes.LiteralNode;
import ast.nodes.OperatorNode;
import ast.nodes.PrintNode;
import ast.nodes.ReadEnvNode;
import ast.nodes.ReassignmentNode;
import ast.nodes.VariableNode;
import java.util.List;

import ast.tokens.ValueAstToken;
import org.junit.jupiter.api.Test;
import providers.printprovider.TestPrintProvider;
import ast.tokens.AstTokenType;

public class ReadEnvTest {

  @Test
  public void testSimpleValidReadEnv() {
    // GIVEN
    // readEnv("TEST_ENV");
    List<AstNode> astNodes =
        List.of(
            new ReadEnvNode(
                new LiteralNode(new ValueAstToken(AstTokenType.STRING, "TEST_ENV", 8, 2)), 1, 1));
    Interpreter interpreter = new Interpreter();
    interpreter.interpret(astNodes.iterator());
  }

  @Test
  public void testSimpleInvalidReadEnv() {
    // GIVEN
    // readEnv("TEST_ENV_NOT_EXISTS");
    List<AstNode> astNodes =
        List.of(
            new ReadEnvNode(
                new LiteralNode(new ValueAstToken(AstTokenType.STRING, "TEST_ENV_NOT_EXISTS", 8, 2)),
                1,
                1));
    Interpreter interpreter = new Interpreter();
    assertThrows(RuntimeException.class, () -> interpreter.interpret(astNodes.iterator()));
  }

  @Test
  public void testInvalidVariableNameReadEnv() {
    // GIVEN
    // let a: string = readEnv("TEST_ENV_NOT_EXISTS");

    List<AstNode> astNodes =
        List.of(
            new AssignationNode(
                new DeclarationNode(
                    new ValueAstToken(AstTokenType.STRING_TYPE, "string", 10, 1),
                    new ValueAstToken(AstTokenType.IDENTIFIER, "a", 4, 1),
                    new ValueAstToken(AstTokenType.LET_KEYWORD, "let", 20, 1),
                    1,
                    0),
                new ReadEnvNode(
                    new LiteralNode(new ValueAstToken(AstTokenType.STRING, "TEST_ENV_NOT_EXISTS", 8, 2)),
                    2,
                    1),
                2,
                1));

    TestPrintProvider printProvider = new TestPrintProvider();
    Interpreter interpreter = new Interpreter(printProvider);
    assertThrows(RuntimeException.class, () -> interpreter.interpret(astNodes.iterator()));
  }

  @Test
  public void testValidMultipleReadEnv() {
    // GIVEN
    // let a: string = readEnv("TEST_ENV");
    // let b: string = readEnv("ANOTHER_ENV");
    // println(a + b);

    List<AstNode> astNodes =
        List.of(
            new AssignationNode(
                new DeclarationNode(
                    new ValueAstToken(AstTokenType.STRING_TYPE, "string", 10, 1),
                    new ValueAstToken(AstTokenType.IDENTIFIER, "a", 4, 1),
                    new ValueAstToken(AstTokenType.LET_KEYWORD, "let", 20, 1),
                    1,
                    0),
                new ReadEnvNode(
                    new LiteralNode(new ValueAstToken(AstTokenType.STRING, "TEST_ENV", 8, 2)), 2, 1),
                2,
                1),
            new AssignationNode(
                new DeclarationNode(
                    new ValueAstToken(AstTokenType.STRING_TYPE, "string", 10, 1),
                    new ValueAstToken(AstTokenType.IDENTIFIER, "b", 4, 1),
                    new ValueAstToken(AstTokenType.LET_KEYWORD, "let", 20, 1),
                    1,
                    0),
                new ReadEnvNode(
                    new LiteralNode(new ValueAstToken(AstTokenType.STRING, "ANOTHER_ENV", 8, 2)), 2, 1),
                2,
                1),
            new PrintNode(
                new OperatorNode(
                    "+",
                    new VariableNode(new ValueAstToken(AstTokenType.IDENTIFIER, "a", 8, 2)),
                    new VariableNode(new ValueAstToken(AstTokenType.IDENTIFIER, "b", 8, 2)),
                    2,
                    1),
                2,
                1));

    TestPrintProvider printProvider = new TestPrintProvider();
    Interpreter interpreter = new Interpreter(printProvider);
    interpreter.interpret(astNodes.iterator());
    assertEquals("Test variableAnother test variable\n", printProvider.getMessages().next());
  }

  @Test
  public void testReassignmentWithInput() {
    // Given
    // let a: string = readEnv("TEST_ENV");
    // a = readEnv("ANOTHER_ENV");
    // println(a);

    List<AstNode> astNodes =
        List.of(
            new AssignationNode(
                new DeclarationNode(
                    new ValueAstToken(AstTokenType.STRING_TYPE, "number", 10, 1),
                    new ValueAstToken(AstTokenType.IDENTIFIER, "a", 4, 1),
                    new ValueAstToken(AstTokenType.LET_KEYWORD, "let", 20, 1),
                    1,
                    0),
                new ReadEnvNode(
                    new LiteralNode(new ValueAstToken(AstTokenType.STRING, "TEST_ENV", 8, 2)), 2, 1),
                2,
                1),
            new ReassignmentNode(
                new VariableNode(new ValueAstToken(AstTokenType.IDENTIFIER, "a", 8, 2)),
                new ReadEnvNode(
                    new LiteralNode(new ValueAstToken(AstTokenType.STRING, "ANOTHER_ENV", 8, 2)), 2, 1),
                2,
                1),
            new PrintNode(new VariableNode(new ValueAstToken(AstTokenType.IDENTIFIER, "a", 8, 2)), 2, 1));

    TestPrintProvider printProvider = new TestPrintProvider();
    Interpreter interpreter = new Interpreter(printProvider);
    interpreter.interpret(astNodes.iterator());
    assertEquals("Another test variable\n", printProvider.getMessages().next());
  }
}
