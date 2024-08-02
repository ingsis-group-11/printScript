package AST.nodes;

import AST.ASTVisitor;
import token.Token;

public class DeclarationNode implements ASTNode {
    private final Token type;
    private final Token name;

    public DeclarationNode(Token type, Token name) {
        this.type = type;
        this.name = name;
    }

    public Token getType() {
        return type;
    }

    public Token getName() {
        return name;
    }

    @Override
    public void accept(ASTVisitor visitor) {
    }
}
