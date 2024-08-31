package interpreter;

import AST.nodes.ASTNode;
import interpreter.providers.inputProvider.InputProvider;
import interpreter.providers.inputProvider.TestInputProvider;
import interpreter.providers.printProvider.PrintProvider;
import interpreter.providers.printProvider.TestPrintProvider;

import java.util.Iterator;

public class Interpreter {
  private final VariableAssignation variableAssignation = new VariableAssignation();
  private final InputProvider inputProvider;
  private final PrintProvider printProvider;

  public Interpreter(InputProvider inputProvider, PrintProvider printProvider) {
    this.inputProvider = inputProvider;
    this.printProvider = printProvider;
  }

  public Interpreter(InputProvider inputProvider) {
    this.inputProvider = inputProvider;
    this.printProvider = new TestPrintProvider();
  }

  public Interpreter(PrintProvider printProvider) {
    this.inputProvider = new TestInputProvider();
    this.printProvider = printProvider;
  }

  public Interpreter() {
    this.inputProvider = new TestInputProvider();
    this.printProvider = new TestPrintProvider();
  }

  public void interpret(Iterator<ASTNode> astIterator) {
    InterpreterVisitor interpreterVisitor = new InterpreterVisitor(variableAssignation, inputProvider, printProvider);
    while(astIterator.hasNext()) {
      astIterator.next().accept(interpreterVisitor);
    }
  }

}
