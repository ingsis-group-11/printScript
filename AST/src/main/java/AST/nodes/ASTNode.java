package AST.nodes;

import AST.ASTVisitor;

public interface ASTNode {
    <T> T accept(ASTVisitor<T> visitor);
}
