package interpreter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ast.nodes.AstNode;
import ast.nodes.BlockNode;
import ast.nodes.IfNode;
import ast.nodes.LiteralNode;
import ast.nodes.PrintNode;
import ast.tokens.AstTokenType;
import ast.tokens.ValueAstToken;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.Test;
import providers.printprovider.TestPrintProvider;

public class IfTest {

  @Test
  public void simpleIfTest() {

    // if (true) {
    //   print("if statement working correctly");
    // }

    TestPrintProvider printProvider = new TestPrintProvider();
    List<AstNode> astNodes =
        List.of(
            new IfNode(
                new LiteralNode(new ValueAstToken(AstTokenType.BOOLEAN, "true", 8, 1)),
                new BlockNode(
                    List.of(
                        new PrintNode(
                            new LiteralNode(
                                new ValueAstToken(
                                    AstTokenType.STRING, "if statement working correctly", 8, 1)),
                            1,
                            1))),
                new BlockNode(List.of()),
                1,
                1));

    Interpreter interpreter = new Interpreter(printProvider);
    interpreter.interpret(astNodes.iterator());
    Iterator<String> messages = printProvider.getMessages();
    assertEquals("if statement working correctly\n", messages.next());
  }
}
