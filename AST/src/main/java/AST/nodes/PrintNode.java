package AST.nodes;

import AST.ASTVisitor;

public class PrintNode implements ASTNode {
    private final ASTNode expression;
    private final Integer line;
    private final Integer column;


    public PrintNode(ASTNode expression, Integer line, Integer column) {
        this.expression = expression;
        this.line = line;
        this.column = column;
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
