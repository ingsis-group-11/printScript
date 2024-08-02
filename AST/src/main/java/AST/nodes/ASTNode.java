package AST.nodes;

import AST.ASTVisitor;

public interface ASTNode {
    public void accept(ASTVisitor visitor);
}
