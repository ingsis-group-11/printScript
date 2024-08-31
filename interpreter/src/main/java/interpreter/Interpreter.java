package interpreter;

import AST.nodes.ASTNode;
import interpreter.inputProvider.InputProvider;

import java.util.Iterator;

public class Interpreter {
  private final VariableAssignation variableAssignation = new VariableAssignation();
  private final InputProvider inputProvider;

  public Interpreter(InputProvider inputProvider) {
    this.inputProvider = inputProvider;
  }

  public void interpret(Iterator<ASTNode> astIterator) {
    InterpreterVisitor interpreterVisitor = new InterpreterVisitor(variableAssignation, inputProvider);
    while(astIterator.hasNext()) {
      astIterator.next().accept(interpreterVisitor);
    }
  }

}
