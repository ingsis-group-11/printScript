package AST.nodes;

import AST.ASTVisitor;
import token.Token;

public class LiteralNode implements ASTNode {
    private final Token value;

    public LiteralNode(Token value) {
        this.value = value;
    }

    public Token getValue() {
        return value;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
