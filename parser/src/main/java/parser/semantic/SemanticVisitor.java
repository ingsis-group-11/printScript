package parser.semantic;

import AST.ASTVisitor;
import AST.nodes.*;
import token.Token;
import token.TokenType;

public class SemanticVisitor implements ASTVisitor<Boolean> {

    @Override
    public Boolean visit(DeclarationNode node) {
        return true;
    }

    @Override
    public Boolean visit(LiteralNode node) {
        return true;
    }

    @Override
    public Boolean visit(PrintNode node) {
        return node.getExpression() instanceof LiteralNode;
    }

    @Override
    public Boolean visit(AssignationNode node) {
        TokenType variableType = node.getDeclaration().getTypeToken().getType();
        TokenType expressionType = ((LiteralNode) node.getExpression()).getType();
        return variableType == expressionType;
    }
}
