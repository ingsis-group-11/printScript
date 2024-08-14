package interpreter;

import AST.nodes.ASTNode;

import java.util.List;

public class Interpreter {
    private final VariableAssignation variableAssignation = new VariableAssignation();

    public void interpret(List<ASTNode> astNodes) {

        InterpreterVisitor interpreterVisitor = new InterpreterVisitor(variableAssignation);
        for (ASTNode node : astNodes) {
            node.accept(interpreterVisitor);
        }
    }
}
