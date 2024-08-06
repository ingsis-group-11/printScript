package interpreter;

import AST.ASTVisitor;
import AST.nodes.ASTNode;
import AST.nodes.AssignationNode;
import AST.nodes.DeclarationNode;
import AST.nodes.LiteralNode;
import AST.nodes.PrintNode;

import java.util.List;

public class Interpreter implements ASTVisitor<Void> {
    private final List<ASTNode> astNodes;

    public Interpreter(List<ASTNode> astNodes) {
        this.astNodes = astNodes;
    }

    public void interpret() {
        for (ASTNode node : astNodes) {
            node.accept(this);
        }
    }

    @Override
    public Void visit(DeclarationNode node) {
        return null;
    }

    @Override
    public Void visit(LiteralNode node) {
        return null;
    }

    @Override
    public Void visit(PrintNode node) {
        LiteralNode expression = (LiteralNode) node.getExpression();
        String message = expression.getValue();
        System.out.println(message);
        return null;
    }

    @Override
    public Void visit(AssignationNode node) {
        return null;
    }
}