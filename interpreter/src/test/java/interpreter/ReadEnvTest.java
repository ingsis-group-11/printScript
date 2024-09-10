package interpreter;

import AST.nodes.*;
import org.junit.jupiter.api.Test;
import providers.printProvider.TestPrintProvider;
import token.TokenType;
import token.ValueToken;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReadEnvTest {

  @Test
  public void testSimpleValidReadEnv(){
    // GIVEN
    // readEnv("TEST_ENV");
    List<ASTNode> astNodes = List.of(
      new ReadEnvNode(new LiteralNode(new ValueToken(TokenType.STRING, "TEST_ENV", 8, 2)),1,1) );
    Interpreter interpreter = new Interpreter();
    interpreter.interpret(astNodes.iterator());
  }

  @Test
  public void testSimpleInvalidReadEnv(){
    // GIVEN
    // readEnv("TEST_ENV_NOT_EXISTS");
    List<ASTNode> astNodes = List.of(
            new ReadEnvNode(new LiteralNode(new ValueToken(TokenType.STRING, "TEST_ENV_NOT_EXISTS", 8, 2)),1,1) );
    Interpreter interpreter = new Interpreter();
    assertThrows(RuntimeException.class, () -> interpreter.interpret(astNodes.iterator()));
  }

  @Test
  public void testInvalidVariableNameReadEnv() {
    // GIVEN
    // let a: string = readEnv("TEST_ENV_NOT_EXISTS");

    List<ASTNode> astNodes = List.of(
            new AssignationNode(
                    new DeclarationNode(
                            new ValueToken(TokenType.STRING_TYPE, "string", 10, 1),
                            new ValueToken(TokenType.IDENTIFIER, "a", 4, 1),
                            new ValueToken(TokenType.LET_KEYWORD, "let", 20, 1),
                            1,
                            0),
                    new ReadEnvNode(new LiteralNode(new ValueToken(TokenType.STRING, "TEST_ENV_NOT_EXISTS", 8, 2)),
                            2,
                            1),
                    2,
                    1)
    );

    TestPrintProvider printProvider = new TestPrintProvider();
    Interpreter interpreter = new Interpreter(printProvider);
    assertThrows(RuntimeException.class, () -> interpreter.interpret(astNodes.iterator()));
  }

  @Test
  public void testValidMultipleReadEnv(){
    // GIVEN
    // let a: string = readEnv("TEST_ENV");
    // let b: string = readEnv("ANOTHER_ENV");
    // println(a + b);

    List<ASTNode> astNodes = List.of(
            new AssignationNode(
                    new DeclarationNode(
                            new ValueToken(TokenType.STRING_TYPE, "string", 10, 1),
                            new ValueToken(TokenType.IDENTIFIER, "a", 4, 1),
                            new ValueToken(TokenType.LET_KEYWORD, "let", 20, 1),
                            1,
                            0),
                    new ReadEnvNode(new LiteralNode(new ValueToken(TokenType.STRING, "TEST_ENV", 8, 2)),
                            2,
                            1),
                    2,
                    1),
            new AssignationNode(
                    new DeclarationNode(
                            new ValueToken(TokenType.STRING_TYPE, "string", 10, 1),
                            new ValueToken(TokenType.IDENTIFIER, "b", 4, 1),
                            new ValueToken(TokenType.LET_KEYWORD, "let", 20, 1),
                            1,
                            0),
                    new ReadEnvNode(new LiteralNode(new ValueToken(TokenType.STRING, "ANOTHER_ENV", 8, 2)),
                            2,
                            1),
                    2,
                    1),
            new PrintNode(new OperatorNode("+",
                    new VariableNode(new ValueToken(TokenType.IDENTIFIER, "a", 8, 2)),
                    new VariableNode(new ValueToken(TokenType.IDENTIFIER, "b", 8, 2)),
                    2,
                    1), 2, 1
            )
    );

    TestPrintProvider printProvider = new TestPrintProvider();
    Interpreter interpreter = new Interpreter(printProvider);
    interpreter.interpret(astNodes.iterator());
    assertEquals("Test variableAnother test variable\n", printProvider.getMessages().next());
  }

  @Test
  public void testReassignmentWithInput(){
    // Given
    // let a: string = readEnv("TEST_ENV");
    // a = readEnv("ANOTHER_ENV");
    // println(a);

    List<ASTNode> astNodes = List.of(
            new AssignationNode(
                    new DeclarationNode(
                            new ValueToken(TokenType.STRING_TYPE, "number", 10, 1),
                            new ValueToken(TokenType.IDENTIFIER, "a", 4, 1),
                            new ValueToken(TokenType.LET_KEYWORD, "let", 20, 1),
                            1,
                            0),
                    new ReadEnvNode(new LiteralNode(new ValueToken(TokenType.STRING, "TEST_ENV", 8, 2)),
                            2,
                            1),
                    2,
                    1),
            new ReassignmentNode(
                    new VariableNode(new ValueToken(TokenType.IDENTIFIER, "a", 8, 2)),
                    new ReadEnvNode(new LiteralNode(new ValueToken(TokenType.STRING, "ANOTHER_ENV", 8, 2)),
                            2,
                            1),
                    2,
                    1),
            new PrintNode(new VariableNode(new ValueToken(TokenType.IDENTIFIER, "a", 8, 2)), 2, 1)
    );

    TestPrintProvider printProvider = new TestPrintProvider();
    Interpreter interpreter = new Interpreter(printProvider);
    interpreter.interpret(astNodes.iterator());
    assertEquals("Another test variable\n", printProvider.getMessages().next());
  }
}
