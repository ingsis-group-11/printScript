package interpreter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import ast.nodes.AssignationNode;
import ast.nodes.AstNode;
import ast.nodes.DeclarationNode;
import ast.nodes.EmptyNode;
import ast.nodes.LiteralNode;
import ast.nodes.OperatorNode;
import ast.nodes.PrintNode;
import ast.nodes.ReassignmentNode;
import ast.nodes.VariableNode;
import ast.tokens.AstTokenType;
import ast.tokens.ValueAstToken;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.Test;
import providers.printprovider.TestPrintProvider;

public class InterpreterTest {

  @Test
  public void testInterpreter() {
    // GIVEN
    // let name: string = "Olive";
    // println(name);
    TestPrintProvider printProvider = new TestPrintProvider();
    List<AstNode> astNodes =
        List.of(
            new AssignationNode(
                new DeclarationNode(
                    new ValueAstToken(AstTokenType.STRING_TYPE, "string", 10, 0),
                    new ValueAstToken(AstTokenType.IDENTIFIER, "name", 4, 0),
                    new ValueAstToken(AstTokenType.LET_KEYWORD, "let", 20, 1),
                    1,
                    0),
                new LiteralNode(new ValueAstToken(AstTokenType.STRING, "Olive", 19, 0)),
                1,
                1),
            new PrintNode(
                new VariableNode(new ValueAstToken(AstTokenType.IDENTIFIER, "name", 8, 1)), 1, 1));

    Interpreter interpreter = new Interpreter(printProvider);
    interpreter.interpret(astNodes.iterator());

    assertEquals("Olive\n", printProvider.getMessages().next());
  }

  @Test
  public void testOperationAtPrint() {
    // GIVEN
    // let a: number = 5;
    // let b: number = 3;
    // println(a + b);
    TestPrintProvider printProvider = new TestPrintProvider();
    List<AstNode> astNodes =
        List.of(
            new AssignationNode(
                new DeclarationNode(
                    new ValueAstToken(AstTokenType.NUMBER_TYPE, "number", 10, 0),
                    new ValueAstToken(AstTokenType.IDENTIFIER, "a", 4, 0),
                    new ValueAstToken(AstTokenType.LET_KEYWORD, "let", 20, 1),
                    1,
                    0),
                new LiteralNode(new ValueAstToken(AstTokenType.NUMBER, "5", 19, 0)),
                1,
                1),
            new AssignationNode(
                new DeclarationNode(
                    new ValueAstToken(AstTokenType.NUMBER_TYPE, "number", 10, 0),
                    new ValueAstToken(AstTokenType.IDENTIFIER, "b", 4, 0),
                    new ValueAstToken(AstTokenType.LET_KEYWORD, "let", 20, 1),
                    1,
                    0),
                new LiteralNode(new ValueAstToken(AstTokenType.NUMBER, "3", 19, 0)),
                1,
                1),
            new PrintNode(
                new OperatorNode(
                    "+",
                    new VariableNode(new ValueAstToken(AstTokenType.IDENTIFIER, "a", 8, 1)),
                    new VariableNode(new ValueAstToken(AstTokenType.IDENTIFIER, "b", 8, 1)),
                    1,
                    1),
                1,
                1));
    Interpreter interpreter = new Interpreter(printProvider);
    interpreter.interpret(astNodes.iterator());

    assertEquals("8\n", printProvider.getMessages().next());
  }

  @Test
  public void testDivisionWithDouble() {
    // GIVEN
    // let a: number = 10;
    // let b: number = 2.5;
    // println(a / b);
    TestPrintProvider printProvider = new TestPrintProvider();
    List<AstNode> astNodes =
        List.of(
            new AssignationNode(
                new DeclarationNode(
                    new ValueAstToken(AstTokenType.NUMBER_TYPE, "number", 10, 0),
                    new ValueAstToken(AstTokenType.IDENTIFIER, "a", 4, 0),
                    new ValueAstToken(AstTokenType.LET_KEYWORD, "let", 20, 1),
                    1,
                    0),
                new LiteralNode(new ValueAstToken(AstTokenType.NUMBER, "10", 19, 0)),
                1,
                1),
            new AssignationNode(
                new DeclarationNode(
                    new ValueAstToken(AstTokenType.NUMBER_TYPE, "number", 10, 0),
                    new ValueAstToken(AstTokenType.IDENTIFIER, "b", 4, 0),
                    new ValueAstToken(AstTokenType.LET_KEYWORD, "let", 20, 1),
                    1,
                    0),
                new LiteralNode(new ValueAstToken(AstTokenType.NUMBER, "2.5", 19, 0)),
                1,
                1),
            new PrintNode(
                new OperatorNode(
                    "/",
                    new VariableNode(new ValueAstToken(AstTokenType.IDENTIFIER, "a", 8, 1)),
                    new VariableNode(new ValueAstToken(AstTokenType.IDENTIFIER, "b", 8, 1)),
                    1,
                    1),
                1,
                1));
    Interpreter interpreter = new Interpreter(printProvider);
    interpreter.interpret(astNodes.iterator());

    assertEquals("4\n", printProvider.getMessages().next());
  }

  @Test
  public void testDivisionWithDoubles() {
    // GIVEN
    // let a: number = 10.0;
    // let b: number = 2.5;
    // println(a / b);
    TestPrintProvider printProvider = new TestPrintProvider();
    List<AstNode> astNodes =
        List.of(
            new AssignationNode(
                new DeclarationNode(
                    new ValueAstToken(AstTokenType.NUMBER_TYPE, "number", 10, 0),
                    new ValueAstToken(AstTokenType.IDENTIFIER, "a", 4, 0),
                    new ValueAstToken(AstTokenType.LET_KEYWORD, "let", 20, 1),
                    1,
                    0),
                new LiteralNode(new ValueAstToken(AstTokenType.NUMBER, "10.0", 19, 0)),
                1,
                1),
            new AssignationNode(
                new DeclarationNode(
                    new ValueAstToken(AstTokenType.NUMBER_TYPE, "number", 10, 0),
                    new ValueAstToken(AstTokenType.IDENTIFIER, "b", 4, 0),
                    new ValueAstToken(AstTokenType.LET_KEYWORD, "let", 20, 1),
                    1,
                    0),
                new LiteralNode(new ValueAstToken(AstTokenType.NUMBER, "2.5", 19, 0)),
                1,
                1),
            new PrintNode(
                new OperatorNode(
                    "/",
                    new VariableNode(new ValueAstToken(AstTokenType.IDENTIFIER, "a", 8, 1)),
                    new VariableNode(new ValueAstToken(AstTokenType.IDENTIFIER, "b", 8, 1)),
                    1,
                    1),
                1,
                1));
    Interpreter interpreter = new Interpreter(printProvider);
    interpreter.interpret(astNodes.iterator());

    assertEquals("4\n", printProvider.getMessages().next());
  }

  @Test
  public void testCreationOfTheSameVariable() {
    // GIVEN
    // let name: string = "a";
    // let name: string = "b";
    List<AstNode> astNodes =
        List.of(
            new AssignationNode(
                new DeclarationNode(
                    new ValueAstToken(AstTokenType.STRING_TYPE, "string", 10, 0),
                    new ValueAstToken(AstTokenType.IDENTIFIER, "name", 4, 0),
                    new ValueAstToken(AstTokenType.LET_KEYWORD, "let", 20, 1),
                    0,
                    0),
                new LiteralNode(new ValueAstToken(AstTokenType.STRING, "a", 19, 0)),
                1,
                1),
            new AssignationNode(
                new DeclarationNode(
                    new ValueAstToken(AstTokenType.STRING_TYPE, "string", 10, 0),
                    new ValueAstToken(AstTokenType.IDENTIFIER, "name", 4, 0),
                    new ValueAstToken(AstTokenType.LET_KEYWORD, "let", 20, 1),
                    1,
                    0),
                new LiteralNode(new ValueAstToken(AstTokenType.STRING, "b", 19, 0)),
                1,
                1));

    Interpreter interpreter = new Interpreter();
    assertThrows(RuntimeException.class, () -> interpreter.interpret(astNodes.iterator()));
  }

  @Test
  public void testReassignmentOfVariable() {
    // GIVEN
    // let name: string = "a";
    // name = "b";
    // println(name);
    TestPrintProvider printProvider = new TestPrintProvider();
    List<AstNode> astNodes =
        List.of(
            new AssignationNode(
                new DeclarationNode(
                    new ValueAstToken(AstTokenType.STRING_TYPE, "string", 10, 0),
                    new ValueAstToken(AstTokenType.IDENTIFIER, "name", 4, 0),
                    new ValueAstToken(AstTokenType.LET_KEYWORD, "let", 20, 1),
                    0,
                    0),
                new LiteralNode(new ValueAstToken(AstTokenType.STRING, "a", 19, 0)),
                1,
                1),
            new ReassignmentNode(
                new VariableNode(new ValueAstToken(AstTokenType.IDENTIFIER, "name", 8, 1)),
                new LiteralNode(new ValueAstToken(AstTokenType.STRING, "b", 19, 0)),
                1,
                1),
            new PrintNode(
                new VariableNode(new ValueAstToken(AstTokenType.IDENTIFIER, "name", 8, 1)), 1, 1));

    Interpreter interpreter = new Interpreter(printProvider);
    interpreter.interpret(astNodes.iterator());

    assertEquals("b\n", printProvider.getMessages().next());
  }

  @Test
  public void testReassignmentOfVariable2() {
    // GIVEN
    // let name: string = "a";
    // name = "b";
    List<AstNode> astNodes =
        List.of(
            new AssignationNode(
                new DeclarationNode(
                    new ValueAstToken(AstTokenType.STRING_TYPE, "string", 10, 0),
                    new ValueAstToken(AstTokenType.IDENTIFIER, "name", 4, 0),
                    new ValueAstToken(AstTokenType.LET_KEYWORD, "let", 20, 1),
                    0,
                    0),
                new LiteralNode(new ValueAstToken(AstTokenType.STRING, "a", 19, 0)),
                1,
                1),
            new ReassignmentNode(
                new VariableNode(new ValueAstToken(AstTokenType.IDENTIFIER, "name", 8, 1)),
                new LiteralNode(new ValueAstToken(AstTokenType.NUMBER, "8", 19, 0)),
                1,
                1));

    // WHEN & THEN
    Interpreter interpreter = new Interpreter();
    assertThrows(RuntimeException.class, () -> interpreter.interpret(astNodes.iterator()));
  }

  @Test
  public void testReassignmentOfVariableBoolean() {
    // GIVEN
    // let bool: boolean = true;
    // println(bool);
    // bool = false;
    // println(bool);
    TestPrintProvider printProvider = new TestPrintProvider();
    List<AstNode> astNodes =
        List.of(
            new AssignationNode(
                new DeclarationNode(
                    new ValueAstToken(AstTokenType.BOOLEAN_TYPE, "boolean", 10, 0),
                    new ValueAstToken(AstTokenType.IDENTIFIER, "bool", 4, 0),
                    new ValueAstToken(AstTokenType.LET_KEYWORD, "let", 20, 1),
                    0,
                    0),
                new LiteralNode(new ValueAstToken(AstTokenType.BOOLEAN, "true", 19, 0)),
                1,
                1),
            new PrintNode(
                new VariableNode(new ValueAstToken(AstTokenType.IDENTIFIER, "bool", 8, 1)), 1, 1),
            new ReassignmentNode(
                new VariableNode(new ValueAstToken(AstTokenType.IDENTIFIER, "bool", 8, 1)),
                new LiteralNode(new ValueAstToken(AstTokenType.BOOLEAN, "false", 19, 0)),
                1,
                1),
            new PrintNode(
                new VariableNode(new ValueAstToken(AstTokenType.IDENTIFIER, "bool", 8, 1)), 1, 1));

    Interpreter interpreter = new Interpreter(printProvider);
    interpreter.interpret(astNodes.iterator());
    Iterator<String> messages = printProvider.getMessages();
    assertEquals("true\n", messages.next());
    assertEquals("false\n", messages.next());
  }

  @Test
  public void testInvalidDivisionAtPrint() {
    // GIVEN
    // let a: string = "Hello ";
    // let b: string = "World";
    // println(a / b);
    TestPrintProvider printProvider = new TestPrintProvider();
    List<AstNode> astNodes =
        List.of(
            new AssignationNode(
                new DeclarationNode(
                    new ValueAstToken(AstTokenType.STRING_TYPE, "string", 10, 0),
                    new ValueAstToken(AstTokenType.IDENTIFIER, "a", 4, 0),
                    new ValueAstToken(AstTokenType.LET_KEYWORD, "let", 20, 1),
                    1,
                    0),
                new LiteralNode(new ValueAstToken(AstTokenType.STRING, "Hello", 19, 0)),
                1,
                1),
            new AssignationNode(
                new DeclarationNode(
                    new ValueAstToken(AstTokenType.STRING_TYPE, "string", 10, 0),
                    new ValueAstToken(AstTokenType.IDENTIFIER, "b", 4, 0),
                    new ValueAstToken(AstTokenType.LET_KEYWORD, "let", 20, 1),
                    1,
                    0),
                new LiteralNode(new ValueAstToken(AstTokenType.STRING, "World", 19, 0)),
                1,
                1),
            new PrintNode(
                new OperatorNode(
                    "/",
                    new VariableNode(new ValueAstToken(AstTokenType.IDENTIFIER, "a", 8, 1)),
                    new VariableNode(new ValueAstToken(AstTokenType.IDENTIFIER, "b", 8, 1)),
                    1,
                    1),
                1,
                1));
    Interpreter interpreter = new Interpreter(printProvider);
    assertThrows(RuntimeException.class, () -> interpreter.interpret(astNodes.iterator()));
  }

  @Test
  public void testInvalidMultiply() {
    // GIVEN
    // let a: string = "Hello ";
    // let b: string = a * 2;
    TestPrintProvider printProvider = new TestPrintProvider();
    List<AstNode> astNodes =
        List.of(
            new AssignationNode(
                new DeclarationNode(
                    new ValueAstToken(AstTokenType.STRING_TYPE, "string", 10, 1),
                    new ValueAstToken(AstTokenType.IDENTIFIER, "a", 4, 1),
                    new ValueAstToken(AstTokenType.LET_KEYWORD, "let", 20, 1),
                    1,
                    0),
                new LiteralNode(new ValueAstToken(AstTokenType.STRING, "Hello ", 19, 1)),
                1,
                1),
            new AssignationNode(
                new DeclarationNode(
                    new ValueAstToken(AstTokenType.STRING_TYPE, "string", 10, 1),
                    new ValueAstToken(AstTokenType.IDENTIFIER, "b", 4, 1),
                    new ValueAstToken(AstTokenType.LET_KEYWORD, "let", 20, 1),
                    1,
                    0),
                new OperatorNode(
                    "*",
                    new VariableNode(new ValueAstToken(AstTokenType.IDENTIFIER, "a", 8, 2)),
                    new LiteralNode(new ValueAstToken(AstTokenType.NUMBER, "2", 8, 2)),
                    2,
                    1),
                2,
                1));
    Interpreter interpreter = new Interpreter(printProvider);
    assertThrows(RuntimeException.class, () -> interpreter.interpret(astNodes.iterator()));
  }

  @Test
  public void testInvalidSubtraction() {
    // GIVEN
    // let a: number = "Hello" - 2;
    TestPrintProvider printProvider = new TestPrintProvider();
    List<AstNode> astNodes =
        List.of(
            new AssignationNode(
                new DeclarationNode(
                    new ValueAstToken(AstTokenType.NUMBER_TYPE, "number", 10, 1),
                    new ValueAstToken(AstTokenType.IDENTIFIER, "a", 4, 1),
                    new ValueAstToken(AstTokenType.LET_KEYWORD, "let", 20, 1),
                    1,
                    0),
                new OperatorNode(
                    "-",
                    new VariableNode(new ValueAstToken(AstTokenType.STRING_TYPE, "Hello", 8, 2)),
                    new LiteralNode(new ValueAstToken(AstTokenType.NUMBER, "2", 8, 2)),
                    2,
                    1),
                2,
                1));
    Interpreter interpreter = new Interpreter(printProvider);
    assertThrows(RuntimeException.class, () -> interpreter.interpret(astNodes.iterator()));
  }

  @Test
  public void testValidSubtraction() {
    // GIVEN
    // let a: number = 5 - 2;
    TestPrintProvider printProvider = new TestPrintProvider();
    List<AstNode> astNodes =
        List.of(
            new AssignationNode(
                new DeclarationNode(
                    new ValueAstToken(AstTokenType.NUMBER_TYPE, "number", 10, 1),
                    new ValueAstToken(AstTokenType.IDENTIFIER, "a", 4, 1),
                    new ValueAstToken(AstTokenType.LET_KEYWORD, "let", 20, 1),
                    1,
                    0),
                new OperatorNode(
                    "-",
                    new LiteralNode(new ValueAstToken(AstTokenType.NUMBER, "5", 8, 2)),
                    new LiteralNode(new ValueAstToken(AstTokenType.NUMBER, "2", 8, 2)),
                    2,
                    1),
                2,
                1));
    Interpreter interpreter = new Interpreter(printProvider);
    interpreter.interpret(astNodes.iterator());
  }

  @Test
  public void testValidMultiply() {
    // GIVEN
    // let a: number = 5;
    // let b: number = a * 2;
    TestPrintProvider printProvider = new TestPrintProvider();
    List<AstNode> astNodes =
        List.of(
            new AssignationNode(
                new DeclarationNode(
                    new ValueAstToken(AstTokenType.NUMBER_TYPE, "number", 10, 1),
                    new ValueAstToken(AstTokenType.IDENTIFIER, "a", 4, 1),
                    new ValueAstToken(AstTokenType.LET_KEYWORD, "let", 20, 1),
                    1,
                    0),
                new LiteralNode(new ValueAstToken(AstTokenType.NUMBER, "5", 19, 1)),
                1,
                1),
            new AssignationNode(
                new DeclarationNode(
                    new ValueAstToken(AstTokenType.NUMBER_TYPE, "number", 10, 1),
                    new ValueAstToken(AstTokenType.IDENTIFIER, "b", 4, 1),
                    new ValueAstToken(AstTokenType.LET_KEYWORD, "let", 20, 1),
                    1,
                    0),
                new OperatorNode(
                    "*",
                    new VariableNode(new ValueAstToken(AstTokenType.IDENTIFIER, "a", 8, 2)),
                    new LiteralNode(new ValueAstToken(AstTokenType.NUMBER, "2", 8, 2)),
                    2,
                    1),
                2,
                1));
    Interpreter interpreter = new Interpreter(printProvider);
    interpreter.interpret(astNodes.iterator());
  }

  @Test
  public void testValidDivisionAtPrint() {
    // GIVEN
    // let a: number = 10;
    // let b: number = 2;
    // println(a / b);
    TestPrintProvider printProvider = new TestPrintProvider();
    List<AstNode> astNodes =
        List.of(
            new AssignationNode(
                new DeclarationNode(
                    new ValueAstToken(AstTokenType.NUMBER_TYPE, "string", 10, 0),
                    new ValueAstToken(AstTokenType.IDENTIFIER, "a", 4, 0),
                    new ValueAstToken(AstTokenType.LET_KEYWORD, "let", 20, 1),
                    1,
                    0),
                new LiteralNode(new ValueAstToken(AstTokenType.NUMBER, "10", 19, 0)),
                1,
                1),
            new AssignationNode(
                new DeclarationNode(
                    new ValueAstToken(AstTokenType.NUMBER_TYPE, "string", 10, 0),
                    new ValueAstToken(AstTokenType.IDENTIFIER, "b", 4, 0),
                    new ValueAstToken(AstTokenType.LET_KEYWORD, "let", 20, 1),
                    1,
                    0),
                new LiteralNode(new ValueAstToken(AstTokenType.NUMBER, "2", 19, 0)),
                1,
                1),
            new PrintNode(
                new OperatorNode(
                    "/",
                    new VariableNode(new ValueAstToken(AstTokenType.IDENTIFIER, "a", 8, 1)),
                    new VariableNode(new ValueAstToken(AstTokenType.IDENTIFIER, "b", 8, 1)),
                    1,
                    1),
                1,
                1));
    Interpreter interpreter = new Interpreter(printProvider);
    interpreter.interpret(astNodes.iterator());
  }

  @Test
  public void testValidEmptyAssignment() {
    // GIVEN
    // let a: number;
    // a = 5;
    TestPrintProvider printProvider = new TestPrintProvider();
    List<AstNode> astNodes =
        List.of(
            new AssignationNode(
                new DeclarationNode(
                    new ValueAstToken(AstTokenType.NUMBER_TYPE, "number", 10, 1),
                    new ValueAstToken(AstTokenType.IDENTIFIER, "a", 4, 1),
                    new ValueAstToken(AstTokenType.LET_KEYWORD, "let", 20, 1),
                    1,
                    0),
                new EmptyNode(AstTokenType.NUMBER),
                2,
                1),
            new ReassignmentNode(
                new VariableNode(new ValueAstToken(AstTokenType.IDENTIFIER, "a", 8, 2)),
                new LiteralNode(new ValueAstToken(AstTokenType.NUMBER, "5", 8, 2)),
                2,
                1));
    Interpreter interpreter = new Interpreter(printProvider);
    interpreter.interpret(astNodes.iterator());
  }

  @Test
  public void testInvalidEmptyAssignment() {
    // GIVEN
    // let a: number;
    // a = "Hello";
    TestPrintProvider printProvider = new TestPrintProvider();
    List<AstNode> astNodes =
        List.of(
            new AssignationNode(
                new DeclarationNode(
                    new ValueAstToken(AstTokenType.NUMBER_TYPE, "number", 10, 1),
                    new ValueAstToken(AstTokenType.IDENTIFIER, "a", 4, 1),
                    new ValueAstToken(AstTokenType.LET_KEYWORD, "let", 20, 1),
                    1,
                    0),
                new EmptyNode(AstTokenType.NUMBER),
                2,
                1),
            new ReassignmentNode(
                new VariableNode(new ValueAstToken(AstTokenType.IDENTIFIER, "a", 8, 2)),
                new LiteralNode(new ValueAstToken(AstTokenType.STRING, "Hello", 8, 2)),
                2,
                1));
    Interpreter interpreter = new Interpreter(printProvider);
    assertThrows(RuntimeException.class, () -> interpreter.interpret(astNodes.iterator()));
  }
}
