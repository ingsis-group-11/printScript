package AST.nodes;

import AST.ASTVisitor;

public class AssignationNode implements ASTNode {
    private final DeclarationNode declaration;
    private final ASTNode expression;

    public AssignationNode(DeclarationNode declaration, ASTNode expression) {
        this.declaration = declaration;
        this.expression = expression;
    }

    public DeclarationNode getDeclaration() {
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
