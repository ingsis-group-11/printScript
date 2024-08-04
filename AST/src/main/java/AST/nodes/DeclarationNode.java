package AST.nodes;

import AST.ASTVisitor;
import token.Token;

public class DeclarationNode implements ASTNode {
    private final Token type;
    private final Token name;
    private final Integer line;
    private final Integer column;

    public DeclarationNode(Token type, Token name, Integer line, Integer column) {
        this.type = type;
        this.name = name;
        this.line = line;
        this.column = column;
    }

    public Token getTypeToken() {
        return type;
    }

    public Token getNameToken() {
        return name;
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
