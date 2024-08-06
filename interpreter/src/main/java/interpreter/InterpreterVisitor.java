package interpreter;

import AST.ASTVisitor;
import AST.nodes.*;

import java.util.List;

public class InterpreterVisitor implements ASTVisitor<Void> {
    private final List<ASTNode> astNodes;
    private final VariableAssignation variableAssignation = VariableAssignation.getInstance();
    private final LiteralTransformer literalTransformer = new LiteralTransformer();

    public InterpreterVisitor(List<ASTNode> astNodes) {
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
        LiteralNode expression = node.getExpression().accept(literalTransformer);
        String message = expression.getValue();
        System.out.println(message);
        return null;
    }

    @Override
    public Void visit(AssignationNode node) {
        LiteralNode expression = node.getExpression().accept(literalTransformer);
        variableAssignation.addVariable(node.getDeclaration().getNameToken().getValue(), expression);
        return null;
    }

    @Override
    public Void visit(OperatorNode operatorNode) {
        return null;
    }

    @Override
    public Void visit(VariableNode variableNode) {
        return null;
    }
}