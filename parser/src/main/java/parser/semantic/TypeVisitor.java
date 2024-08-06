package parser.semantic;

import AST.ASTVisitor;
import AST.nodes.*;
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

    @Override
    public TokenType visit(OperatorNode operatorNode) {
        if(operatorNode.getLeftNode().accept(this) == TokenType.NUMBER && operatorNode.getRightNode().accept(this) == TokenType.NUMBER) {
            return TokenType.NUMBER;
        } else {
            return TokenType.STRING;
        }
    }

    @Override
    public TokenType visit(VariableNode variableNode) {
        return null;
    }
}
