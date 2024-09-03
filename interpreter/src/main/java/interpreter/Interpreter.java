package interpreter;

import AST.nodes.ASTNode;
import providers.printProvider.PrintProvider;
import providers.printProvider.TestPrintProvider;

import java.util.Iterator;

public class Interpreter {
  private final VariableAssignation variableAssignation = new VariableAssignation();
  private final PrintProvider printProvider;

  public Interpreter(PrintProvider printProvider) {
    this.printProvider = printProvider;
  }

  public Interpreter() {
    this.printProvider = new TestPrintProvider();
  }

  public void interpret(Iterator<ASTNode> astIterator) {
    InterpreterVisitor interpreterVisitor = new InterpreterVisitor(variableAssignation, printProvider);
    while(astIterator.hasNext()) {
      astIterator.next().accept(interpreterVisitor);
    }
  }

}
