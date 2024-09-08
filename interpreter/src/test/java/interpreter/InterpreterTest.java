package interpreter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import AST.nodes.*;

import java.util.Iterator;
import java.util.List;

import providers.inputProvider.InputProvider;
import providers.inputProvider.TestInputProvider;
import providers.printProvider.PrintProvider;
import providers.printProvider.TestPrintProvider;
import org.junit.jupiter.api.Test;
import token.TokenType;
import token.ValueToken;

public class InterpreterTest {

  @Test
  public void testInterpreter() {
    // GIVEN
    // let name: string = "Olive";
    // println(name);
    TestPrintProvider printProvider = new TestPrintProvider();
    List<ASTNode> astNodes =
        List.of(
            new AssignationNode(
                new DeclarationNode(
                    new ValueToken(TokenType.STRING_TYPE, "string", 10, 0),
                    new ValueToken(TokenType.IDENTIFIER, "name", 4, 0),
                    new ValueToken(TokenType.LET_KEYWORD, "let", 20, 1),
                    1,
                    0),
                new LiteralNode(new ValueToken(TokenType.STRING, "Olive", 19, 0)),
                1,
                1),
            new PrintNode(
                new VariableNode(new ValueToken(TokenType.IDENTIFIER, "name", 8, 1)), 1, 1));

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
    List<ASTNode> astNodes = List.of(
        new AssignationNode(
                new DeclarationNode(
                        new ValueToken(TokenType.NUMBER_TYPE, "number", 10, 0),
                        new ValueToken(TokenType.IDENTIFIER, "a", 4, 0),
                    new ValueToken(TokenType.LET_KEYWORD, "let", 20, 1),
                        1,
                        0),
                new LiteralNode(new ValueToken(TokenType.NUMBER, "5", 19, 0)),
                1,
                1),
        new AssignationNode(
                new DeclarationNode(
                        new ValueToken(TokenType.NUMBER_TYPE, "number", 10, 0),
                        new ValueToken(TokenType.IDENTIFIER, "b", 4, 0),
                    new ValueToken(TokenType.LET_KEYWORD, "let", 20, 1),
                        1,
                        0),
                new LiteralNode(new ValueToken(TokenType.NUMBER, "3", 19, 0)),
                1,
                1),
        new PrintNode(
                new OperatorNode("+",
                        new VariableNode(new ValueToken(TokenType.IDENTIFIER, "a", 8, 1)),
                        new VariableNode(new ValueToken(TokenType.IDENTIFIER, "b", 8, 1)),
                        1,
                        1),
                1,
                1)
    );
    Interpreter interpreter = new Interpreter(printProvider);
    interpreter.interpret(astNodes.iterator());

    assertEquals("8\n", printProvider.getMessages().next());
  }

  @Test
  public void testCreationOfTheSameVariable() {
    // GIVEN
    // let name: string = "a";
    // let name: string = "b";
    List<ASTNode> astNodes =
        List.of(
            new AssignationNode(
                new DeclarationNode(
                    new ValueToken(TokenType.STRING_TYPE, "string", 10, 0),
                    new ValueToken(TokenType.IDENTIFIER, "name", 4, 0),
                    new ValueToken(TokenType.LET_KEYWORD, "let", 20, 1),
                    0,
                    0),
                new LiteralNode(new ValueToken(TokenType.STRING, "a", 19, 0)),
                1,
                1),
            new AssignationNode(
                new DeclarationNode(
                    new ValueToken(TokenType.STRING_TYPE, "string", 10, 0),
                    new ValueToken(TokenType.IDENTIFIER, "name", 4, 0),
                    new ValueToken(TokenType.LET_KEYWORD, "let", 20, 1),
                    1,
                    0),
                new LiteralNode(new ValueToken(TokenType.STRING, "b", 19, 0)),
                1,
                1));

    Interpreter interpreter = new Interpreter();
    assertThrows(RuntimeException.class, () -> interpreter.interpret(astNodes.iterator()));
  }

    @Test
    public void testReassignmentOfVariable() {
      //GIVEN
      // let name: string = "a";
      // name = "b";
      // println(name);
      TestPrintProvider printProvider = new TestPrintProvider();
      List<ASTNode> astNodes = List.of(
              new AssignationNode(
                      new DeclarationNode(new ValueToken(TokenType.STRING_TYPE, "string", 10, 0),
                              new ValueToken(TokenType.IDENTIFIER, "name", 4, 0), new ValueToken(TokenType.LET_KEYWORD,
                          "let", 20, 1), 0, 0)

                      , new LiteralNode(new ValueToken(TokenType.STRING, "a", 19, 0)),
                      1, 1
              ), new ReassignmentNode(
                      new VariableNode(new ValueToken(TokenType.IDENTIFIER, "name", 8, 1)),
                      new LiteralNode(new ValueToken(TokenType.STRING, "b", 19, 0)),
                      1, 1
              ), new PrintNode(new VariableNode(new ValueToken(TokenType.IDENTIFIER, "name", 8, 1)), 1, 1)
      );

      Interpreter interpreter = new Interpreter(printProvider);
      interpreter.interpret(astNodes.iterator());

      assertEquals("b\n", printProvider.getMessages().next());
    }

    @Test
    public void testReassignmentOfVariable2() {
        //GIVEN
        // let name: string = "a";
        // name = "b";
        List<ASTNode> astNodes = List.of(
                new AssignationNode(
                        new DeclarationNode(new ValueToken(TokenType.STRING_TYPE, "string", 10, 0),
                                new ValueToken(TokenType.IDENTIFIER, "name", 4, 0),
                            new ValueToken(TokenType.LET_KEYWORD, "let", 20, 1),0, 0)
                        , new LiteralNode(new ValueToken(TokenType.STRING, "a", 19, 0)),
                        1,1
                ), new ReassignmentNode(
                        new VariableNode(new ValueToken(TokenType.IDENTIFIER, "name", 8, 1)),
                        new LiteralNode(new ValueToken(TokenType.NUMBER, "8", 19, 0)),
                        1,1
                )
        );

        // WHEN & THEN
        Interpreter interpreter = new Interpreter();
        assertThrows(RuntimeException.class, () -> interpreter.interpret(astNodes.iterator()));
    }

  @Test
  public void testReassignmentOfVariableBoolean() {
    //GIVEN
    // let bool: boolean = true;
    // println(bool);
    // bool = false;
    // println(bool);
    TestPrintProvider printProvider = new TestPrintProvider();
    List<ASTNode> astNodes = List.of(
        new AssignationNode(
            new DeclarationNode(new ValueToken(TokenType.BOOLEAN_TYPE, "boolean", 10, 0),
                new ValueToken(TokenType.IDENTIFIER, "bool", 4, 0),
                new ValueToken(TokenType.LET_KEYWORD, "let", 20, 1),0, 0)
            , new LiteralNode(new ValueToken(TokenType.BOOLEAN, "true", 19, 0)),
            1,1
        ),
        new PrintNode(
            new VariableNode(new ValueToken(TokenType.IDENTIFIER, "bool", 8, 1)), 1, 1),
        new ReassignmentNode(
            new VariableNode(new ValueToken(TokenType.IDENTIFIER, "bool", 8, 1)),
            new LiteralNode(new ValueToken(TokenType.BOOLEAN, "false", 19, 0)),
            1,1
        ),
        new PrintNode(
            new VariableNode(new ValueToken(TokenType.IDENTIFIER, "bool", 8, 1)), 1, 1)
    );

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
    List<ASTNode> astNodes = List.of(
            new AssignationNode(
                    new DeclarationNode(
                            new ValueToken(TokenType.STRING_TYPE, "string", 10, 0),
                            new ValueToken(TokenType.IDENTIFIER, "a", 4, 0),
                        new ValueToken(TokenType.LET_KEYWORD, "let", 20, 1),
                            1,
                            0),
                    new LiteralNode(new ValueToken(TokenType.STRING, "Hello", 19, 0)),
                    1,
                    1),
            new AssignationNode(
                    new DeclarationNode(
                            new ValueToken(TokenType.STRING_TYPE, "string", 10, 0),
                            new ValueToken(TokenType.IDENTIFIER, "b", 4, 0),
                        new ValueToken(TokenType.LET_KEYWORD, "let", 20, 1),
                            1,
                            0),
                    new LiteralNode(new ValueToken(TokenType.STRING, "World", 19, 0)),
                    1,
                    1),
            new PrintNode(
                    new OperatorNode("/",
                            new VariableNode(new ValueToken(TokenType.IDENTIFIER, "a", 8, 1)),
                            new VariableNode(new ValueToken(TokenType.IDENTIFIER, "b", 8, 1)),
                            1,
                            1),
                    1,
                    1)
    );
    Interpreter interpreter = new Interpreter(printProvider);
    assertThrows(RuntimeException.class, () -> interpreter.interpret(astNodes.iterator()));
  }

  @Test
  public void testInvalidMultiply() {
    // GIVEN
    // let a: string = "Hello ";
    // let b: string = a * 2;
    TestPrintProvider printProvider = new TestPrintProvider();
    List<ASTNode> astNodes = List.of(
            new AssignationNode(
                    new DeclarationNode(
                            new ValueToken(TokenType.STRING_TYPE, "string", 10, 1),
                            new ValueToken(TokenType.IDENTIFIER, "a", 4, 1),
                            new ValueToken(TokenType.LET_KEYWORD, "let", 20, 1),
                            1,
                            0),
                    new LiteralNode(new ValueToken(TokenType.STRING, "Hello ", 19, 1)),
                    1,
                    1),
            new AssignationNode(
                    new DeclarationNode(
                            new ValueToken(TokenType.STRING_TYPE, "string", 10, 1),
                            new ValueToken(TokenType.IDENTIFIER, "b", 4, 1),
                            new ValueToken(TokenType.LET_KEYWORD, "let", 20, 1),
                            1,
                            0),
                    new OperatorNode("*",
                            new VariableNode(new ValueToken(TokenType.IDENTIFIER, "a", 8, 2)),
                            new LiteralNode(new ValueToken(TokenType.NUMBER, "2", 8, 2)),
                            2,
                            1),
                    2,
                    1)
    );
    Interpreter interpreter = new Interpreter(printProvider);
    assertThrows(RuntimeException.class, () -> interpreter.interpret(astNodes.iterator()));
  }

  @Test
  public void testInvalidSubtraction() {
    // GIVEN
    // let a: number = "Hello" - 2;
    TestPrintProvider printProvider = new TestPrintProvider();
    List<ASTNode> astNodes = List.of(
            new AssignationNode(
                    new DeclarationNode(
                            new ValueToken(TokenType.NUMBER_TYPE, "number", 10, 1),
                            new ValueToken(TokenType.IDENTIFIER, "a", 4, 1),
                            new ValueToken(TokenType.LET_KEYWORD, "let", 20, 1),
                            1,
                            0),
                    new OperatorNode("-",
                            new VariableNode(new ValueToken(TokenType.STRING_TYPE, "Hello", 8, 2)),
                            new LiteralNode(new ValueToken(TokenType.NUMBER, "2", 8, 2)),
                            2,
                            1),
                    2,
                    1)
    );
    Interpreter interpreter = new Interpreter(printProvider);
    assertThrows(RuntimeException.class, () -> interpreter.interpret(astNodes.iterator()));
  }

  @Test
  public void testValidSubtraction() {
    // GIVEN
    // let a: number = 5 - 2;
    TestPrintProvider printProvider = new TestPrintProvider();
    List<ASTNode> astNodes = List.of(
            new AssignationNode(
                    new DeclarationNode(
                            new ValueToken(TokenType.NUMBER_TYPE, "number", 10, 1),
                            new ValueToken(TokenType.IDENTIFIER, "a", 4, 1),
                            new ValueToken(TokenType.LET_KEYWORD, "let", 20, 1),
                            1,
                            0),
                    new OperatorNode("-",
                            new LiteralNode(new ValueToken(TokenType.NUMBER, "5", 8, 2)),
                            new LiteralNode(new ValueToken(TokenType.NUMBER, "2", 8, 2)),
                            2,
                            1),
                    2,
                    1)
    );
    Interpreter interpreter = new Interpreter(printProvider);
    interpreter.interpret(astNodes.iterator());
  }

  @Test
  public void testValidMultiply() {
    // GIVEN
    // let a: number = 5;
    // let b: number = a * 2;
    TestPrintProvider printProvider = new TestPrintProvider();
    List<ASTNode> astNodes = List.of(
            new AssignationNode(
                    new DeclarationNode(
                            new ValueToken(TokenType.NUMBER_TYPE, "number", 10, 1),
                            new ValueToken(TokenType.IDENTIFIER, "a", 4, 1),
                            new ValueToken(TokenType.LET_KEYWORD, "let", 20, 1),
                            1,
                            0),
                    new LiteralNode(new ValueToken(TokenType.NUMBER, "5", 19, 1)),
                    1,
                    1),
            new AssignationNode(
                    new DeclarationNode(
                            new ValueToken(TokenType.NUMBER_TYPE, "number", 10, 1),
                            new ValueToken(TokenType.IDENTIFIER, "b", 4, 1),
                            new ValueToken(TokenType.LET_KEYWORD, "let", 20, 1),
                            1,
                            0),
                    new OperatorNode("*",
                            new VariableNode(new ValueToken(TokenType.IDENTIFIER, "a", 8, 2)),
                            new LiteralNode(new ValueToken(TokenType.NUMBER, "2", 8, 2)),
                            2,
                            1),
                    2,
                    1)
    );
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
    List<ASTNode> astNodes = List.of(
            new AssignationNode(
                    new DeclarationNode(
                            new ValueToken(TokenType.NUMBER_TYPE, "string", 10, 0),
                            new ValueToken(TokenType.IDENTIFIER, "a", 4, 0),
                        new ValueToken(TokenType.LET_KEYWORD, "let", 20, 1),
                            1,
                            0),
                    new LiteralNode(new ValueToken(TokenType.NUMBER, "10", 19, 0)),
                    1,
                    1),
            new AssignationNode(
                    new DeclarationNode(
                            new ValueToken(TokenType.NUMBER_TYPE, "string", 10, 0),
                            new ValueToken(TokenType.IDENTIFIER, "b", 4, 0),
                        new ValueToken(TokenType.LET_KEYWORD, "let", 20, 1),
                            1,
                            0),
                    new LiteralNode(new ValueToken(TokenType.NUMBER, "2", 19, 0)),
                    1,
                    1),
            new PrintNode(
                    new OperatorNode("/",
                            new VariableNode(new ValueToken(TokenType.IDENTIFIER, "a", 8, 1)),
                            new VariableNode(new ValueToken(TokenType.IDENTIFIER, "b", 8, 1)),
                            1,
                            1),
                    1,
                    1)
    );
    Interpreter interpreter = new Interpreter(printProvider);
    interpreter.interpret(astNodes.iterator());
  }

  @Test
  public void testValidEmptyAssignment() {
    // GIVEN
    // let a: number;
    // a = 5;
    TestPrintProvider printProvider = new TestPrintProvider();
    List<ASTNode> astNodes = List.of(
            new AssignationNode(
                    new DeclarationNode(
                            new ValueToken(TokenType.NUMBER_TYPE, "number", 10, 1),
                            new ValueToken(TokenType.IDENTIFIER, "a", 4, 1),
                        new ValueToken(TokenType.LET_KEYWORD, "let", 20, 1),
                            1,
                            0),
                    new EmptyNode(TokenType.NUMBER),
                    2,
                    1),
            new ReassignmentNode(
                    new VariableNode(new ValueToken(TokenType.IDENTIFIER, "a", 8, 2)),
                    new LiteralNode(new ValueToken(TokenType.NUMBER, "5", 8, 2)),
                    2,
                    1)
    );
    Interpreter interpreter = new Interpreter(printProvider);
    interpreter.interpret(astNodes.iterator());
  }

  @Test
  public void testInvalidEmptyAssignment() {
    // GIVEN
    // let a: number;
    // a = "Hello";
    TestPrintProvider printProvider = new TestPrintProvider();
    List<ASTNode> astNodes = List.of(
            new AssignationNode(
                    new DeclarationNode(
                            new ValueToken(TokenType.NUMBER_TYPE, "number", 10, 1),
                            new ValueToken(TokenType.IDENTIFIER, "a", 4, 1),
                        new ValueToken(TokenType.LET_KEYWORD, "let", 20, 1),
                            1,
                            0),
                    new EmptyNode(TokenType.NUMBER),
                    2,
                    1),
            new ReassignmentNode(
                    new VariableNode(new ValueToken(TokenType.IDENTIFIER, "a", 8, 2)),
                    new LiteralNode(new ValueToken(TokenType.STRING, "Hello", 8, 2)),
                    2,
                    1)
    );
    Interpreter interpreter = new Interpreter(printProvider);
    assertThrows(RuntimeException.class, () -> interpreter.interpret(astNodes.iterator()));
  }
}
