package interpreter;

import AST.nodes.ASTNode;

import java.util.List;

public class Interpreter {
    public void interpret(List<ASTNode> astNodes) {
        InterpreterVisitor interpreterVisitor = new InterpreterVisitor();
        for (ASTNode node : astNodes) {
            node.accept(interpreterVisitor);
        }
    }
}
