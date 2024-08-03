package AST.nodes;

import AST.ASTVisitor;

public class AssignationNode implements ASTNode {
    private final ASTNode declaration;
    private final ASTNode expression;

    public AssignationNode(ASTNode declaration, ASTNode expression) {
        this.declaration = declaration;
        this.expression = expression;
    }

    public ASTNode getDeclaration() {
        return declaration;
    }

    public ASTNode getExpression() {
        return expression;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
