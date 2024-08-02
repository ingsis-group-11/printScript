package AST.nodes;

import AST.ASTVisitor;

public class PrintNode implements ASTNode {
    private final ASTNode expression;

    public PrintNode(ASTNode expression) {
        this.expression = expression;
    }

    public ASTNode getExpression() {
        return expression;
    }

    @Override
    public void accept(ASTVisitor visitor) {
    }
}
