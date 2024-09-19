package interpreter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import ast.nodes.AssignationNode;
import ast.nodes.AstNode;
import ast.nodes.DeclarationNode;
import ast.nodes.LiteralNode;
import ast.nodes.OperatorNode;
import ast.nodes.PrintNode;
import ast.nodes.ReadInputNode;
import ast.nodes.ReassignmentNode;
import ast.nodes.VariableNode;
import java.util.List;

import ast.tokens.ValueAstToken;
import org.junit.jupiter.api.Test;
import providers.inputprovider.InputProvider;
import providers.inputprovider.TestInputProvider;
import providers.printprovider.TestPrintProvider;
import ast.tokens.AstTokenType;

public class ReadInputTest {
  @Test
  public void testSimpleValidReadEnv() {
    // GIVEN
    // readInput("Test");
    List<AstNode> astNodes =
        List.of(
            new ReadInputNode(
                new LiteralNode(new ValueAstToken(AstTokenType.STRING, "Test", 8, 2)), 1, 1));
    TestInputProvider inputProvider = new TestInputProvider(List.of("Hello"));
    Interpreter interpreter = new Interpreter(inputProvider);
    interpreter.interpret(astNodes.iterator());
    assertFalse(inputProvider.hasInputsToRead());
  }

  @Test
  public void testInvalidTypeReadInput() {
    // GIVEN
    // let a: number = readInput("Ingrese a: "); -> hola -> should fail
    // println(a);

    List<AstNode> astNodes =
        List.of(
            new AssignationNode(
                new DeclarationNode(
                    new ValueAstToken(AstTokenType.NUMBER_TYPE, "number", 10, 1),
                    new ValueAstToken(AstTokenType.IDENTIFIER, "a", 4, 1),
                    new ValueAstToken(AstTokenType.LET_KEYWORD, "let", 20, 1),
                    1,
                    0),
                new ReadInputNode(
                    new LiteralNode(new ValueAstToken(AstTokenType.STRING, "Ingrese a: ", 8, 2)), 2, 1),
                2,
                1),
            new PrintNode(new VariableNode(new ValueAstToken(AstTokenType.IDENTIFIER, "a", 8, 2)), 2, 1));

    InputProvider inputProvider = new TestInputProvider(List.of("hola"));
    TestPrintProvider printProvider = new TestPrintProvider();
    Interpreter interpreter = new Interpreter(inputProvider, printProvider);
    assertThrows(RuntimeException.class, () -> interpreter.interpret(astNodes.iterator()));
  }

  @Test
  public void testValidSingleReadInput() {
    // GIVEN
    // let a: string = readInput("Ingrese a: "); -> hola
    // println(a); -> hola

    List<AstNode> astNodes =
        List.of(
            new AssignationNode(
                new DeclarationNode(
                    new ValueAstToken(AstTokenType.STRING_TYPE, "string", 10, 1),
                    new ValueAstToken(AstTokenType.IDENTIFIER, "a", 4, 1),
                    new ValueAstToken(AstTokenType.LET_KEYWORD, "let", 20, 1),
                    1,
                    0),
                new ReadInputNode(
                    new LiteralNode(new ValueAstToken(AstTokenType.STRING, "Ingrese a: ", 8, 2)), 2, 1),
                2,
                1),
            new PrintNode(new VariableNode(new ValueAstToken(AstTokenType.IDENTIFIER, "a", 8, 2)), 2, 1));

    InputProvider inputProvider = new TestInputProvider(List.of("hola"));
    TestPrintProvider printProvider = new TestPrintProvider();
    Interpreter interpreter = new Interpreter(inputProvider, printProvider);
    interpreter.interpret(astNodes.iterator());
    assertEquals("hola\n", printProvider.getMessages().next());
  }

  @Test
  public void testValidMultipleReadInput() {
    // GIVEN
    // let a: number = readInput("Ingrese a: "); -> 2
    // let b: number = readInput("Ingrese b: "); -> 2
    // println(a + b); -> 4

    List<AstNode> astNodes =
        List.of(
            new AssignationNode(
                new DeclarationNode(
                    new ValueAstToken(AstTokenType.NUMBER_TYPE, "number", 10, 1),
                    new ValueAstToken(AstTokenType.IDENTIFIER, "a", 4, 1),
                    new ValueAstToken(AstTokenType.LET_KEYWORD, "let", 20, 1),
                    1,
                    0),
                new ReadInputNode(
                    new LiteralNode(new ValueAstToken(AstTokenType.STRING, "Ingrese a: ", 8, 2)), 2, 1),
                2,
                1),
            new AssignationNode(
                new DeclarationNode(
                    new ValueAstToken(AstTokenType.NUMBER_TYPE, "number", 10, 1),
                    new ValueAstToken(AstTokenType.IDENTIFIER, "b", 4, 1),
                    new ValueAstToken(AstTokenType.LET_KEYWORD, "let", 20, 1),
                    1,
                    0),
                new ReadInputNode(
                    new LiteralNode(new ValueAstToken(AstTokenType.STRING, "Ingrese b: ", 8, 2)), 2, 1),
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

    InputProvider inputProvider = new TestInputProvider(List.of("2", "2"));
    TestPrintProvider printProvider = new TestPrintProvider();
    Interpreter interpreter = new Interpreter(inputProvider, printProvider);
    interpreter.interpret(astNodes.iterator());
    assertEquals("4\n", printProvider.getMessages().next());
  }

  @Test
  public void testEmptyInput() {
    // GIVEN
    // let a: number = readInput("Ingrese a: "); -> "2.76"
    // println(a); -> ""

    List<AstNode> astNodes =
        List.of(
            new AssignationNode(
                new DeclarationNode(
                    new ValueAstToken(AstTokenType.NUMBER_TYPE, "number", 10, 1),
                    new ValueAstToken(AstTokenType.IDENTIFIER, "a", 4, 1),
                    new ValueAstToken(AstTokenType.LET_KEYWORD, "let", 20, 1),
                    1,
                    0),
                new ReadInputNode(
                    new LiteralNode(new ValueAstToken(AstTokenType.STRING, "Ingrese a: ", 8, 2)), 2, 1),
                2,
                1),
            new PrintNode(new VariableNode(new ValueAstToken(AstTokenType.IDENTIFIER, "a", 8, 2)), 2, 1));

    InputProvider inputProvider = new TestInputProvider(List.of("2.76"));
    TestPrintProvider printProvider = new TestPrintProvider();
    Interpreter interpreter = new Interpreter(inputProvider, printProvider);
    interpreter.interpret(astNodes.iterator());
    assertEquals("2.76\n", printProvider.getMessages().next());
  }

  @Test
  public void testSpecialCharactersInput() {
    // Given
    // let a: string = readInput("Ingrese a: "); -> "hola"
    // println(a);

    List<AstNode> astNodes =
        List.of(
            new AssignationNode(
                new DeclarationNode(
                    new ValueAstToken(AstTokenType.STRING_TYPE, "string", 10, 1),
                    new ValueAstToken(AstTokenType.IDENTIFIER, "a", 4, 1),
                    new ValueAstToken(AstTokenType.LET_KEYWORD, "let", 20, 1),
                    1,
                    0),
                new ReadInputNode(
                    new LiteralNode(new ValueAstToken(AstTokenType.STRING, "Ingrese a: ", 8, 2)), 2, 1),
                2,
                1),
            new PrintNode(new VariableNode(new ValueAstToken(AstTokenType.IDENTIFIER, "a", 8, 2)), 2, 1));

    InputProvider inputProvider = new TestInputProvider(List.of("hola"));
    TestPrintProvider printProvider = new TestPrintProvider();
    Interpreter interpreter = new Interpreter(inputProvider, printProvider);
    interpreter.interpret(astNodes.iterator());
    assertEquals("hola\n", printProvider.getMessages().next());
  }

  @Test
  public void testReassignmentWithInput() {
    // Given
    // let a: number = readInput("Ingrese a: "); -> 5
    // a = readInput("Ingrese a: "); -> hola

    List<AstNode> astNodes =
        List.of(
            new AssignationNode(
                new DeclarationNode(
                    new ValueAstToken(AstTokenType.NUMBER_TYPE, "number", 10, 1),
                    new ValueAstToken(AstTokenType.IDENTIFIER, "a", 4, 1),
                    new ValueAstToken(AstTokenType.LET_KEYWORD, "let", 20, 1),
                    1,
                    0),
                new ReadInputNode(
                    new LiteralNode(new ValueAstToken(AstTokenType.STRING, "Ingrese a: ", 8, 2)), 2, 1),
                2,
                1),
            new ReassignmentNode(
                new VariableNode(new ValueAstToken(AstTokenType.IDENTIFIER, "a", 8, 2)),
                new ReadInputNode(
                    new LiteralNode(new ValueAstToken(AstTokenType.STRING, "Ingrese a: ", 8, 2)), 2, 1),
                2,
                1));

    InputProvider inputProvider = new TestInputProvider(List.of("5", "hola"));
    Interpreter interpreter = new Interpreter(inputProvider);
    assertThrows(RuntimeException.class, () -> interpreter.interpret(astNodes.iterator()));
  }
}
