package parser.semantic;

import AST.ASTVisitor;
import AST.nodes.*;
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
        return (node.getExpression() instanceof LiteralNode || node.getExpression() instanceof VariableNode);
    }

    @Override
    public Boolean visit(AssignationNode node) {
        TokenType variableType = node.getDeclaration().getTypeToken().getType();
        TokenType expressionType = ((LiteralNode) node.getExpression()).getType();
        if(variableType == TokenType.NUMBER_TYPE && expressionType == TokenType.NUMBER) {
            return true;
        } else if(variableType == TokenType.STRING_TYPE && expressionType == TokenType.STRING) {
            return true;
        } else {
            return false;
        }
    }
}
