package AST.nodes;

import AST.ASTVisitor;

public class ReassignmentNode implements ASTNode{
    private final VariableNode variableNode;
    private final ASTNode expression;
    private final Integer line;
    private final Integer column;

    public ReassignmentNode(VariableNode variableNode, ASTNode expression, Integer line, Integer column){
        this.variableNode = variableNode;
        this.expression = expression;
        this.line = line;
        this.column = column;
    }

    @Override
    public Integer getLine() {
        return line;
    }

    @Override
    public Integer getColumn() {
        return column;
    }

    public VariableNode getVariableNode(){
        return variableNode;
    }

    public ASTNode getExpression(){
        return expression;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
