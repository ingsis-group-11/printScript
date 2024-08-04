package AST.nodes;

import AST.ASTVisitor;

public class AssignationNode implements ASTNode {
    private final DeclarationNode declaration;
    private final ASTNode expression;
    private final Integer line;
    private final Integer column;

    public AssignationNode(DeclarationNode declaration, ASTNode expression, Integer line, Integer column) {
        this.declaration = declaration;
        this.expression = expression;
        this.line = line;
        this.column = column;
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

    @Override
    public Integer getLine() {
        return line;
    }

    @Override
    public Integer getColumn() {
        return column;
    }
}
