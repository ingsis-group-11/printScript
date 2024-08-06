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
        TypeVisitor typeVisitor = new TypeVisitor();
        TokenType variableType = node.getDeclaration().getTypeToken().getType();
        TokenType expressionType = node.getExpression().accept(typeVisitor);

        if(variableType == TokenType.NUMBER_TYPE && expressionType == TokenType.NUMBER) {
            return true;
        } else if(variableType == TokenType.STRING_TYPE && expressionType == TokenType.STRING) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean visit(OperatorNode operatorNode) {
        TypeVisitor typeVisitor = new TypeVisitor();
        if(operatorNode.getOperator().equals("*") || operatorNode.getOperator().equals("/") || operatorNode.getOperator().equals("-")) {
            return !oneNodeIsString(operatorNode, typeVisitor);
        }
        return true;
    }

    @Override
    public Boolean visit(VariableNode variableNode) {
        return true;
    }

    private boolean oneNodeIsString(OperatorNode operatorNode, TypeVisitor typeVisitor) {
        return operatorNode.getLeftNode().accept(typeVisitor) == TokenType.STRING || operatorNode.getRightNode().accept(typeVisitor) == TokenType.STRING;
    }
}
