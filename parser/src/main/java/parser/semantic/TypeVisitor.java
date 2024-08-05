package parser.semantic;

import AST.ASTVisitor;
import AST.nodes.AssignationNode;
import AST.nodes.DeclarationNode;
import AST.nodes.LiteralNode;
import AST.nodes.PrintNode;
import token.TokenType;

public class TypeVisitor implements ASTVisitor<TokenType> {

    @Override
    public TokenType visit(DeclarationNode node) {
        return node.getTypeToken().getType();
    }

    @Override
    public TokenType visit(LiteralNode node) {
        return node.getType();
    }

    @Override
    public TokenType visit(PrintNode node) {
        return node.getExpression().accept(this);
    }

    @Override
    public TokenType visit(AssignationNode node) {
        return node.getExpression().accept(this);
    }
}
