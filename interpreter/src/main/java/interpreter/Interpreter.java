package interpreter;

import AST.nodes.ASTNode;
import java.util.Iterator;

public class Interpreter {
  private final VariableAssignation variableAssignation = new VariableAssignation();

  public void interpret(Iterator<ASTNode> astIterator) {
    InterpreterVisitor interpreterVisitor = new InterpreterVisitor(variableAssignation);
    while(astIterator.hasNext()) {
      astIterator.next().accept(interpreterVisitor);
    }
  }

}
