package parser.semantic;

import AST.ASTVisitor;
import AST.nodes.*;
import com.sun.source.tree.BreakTree;
import parser.semantic.result.SemanticErrorResult;
import parser.semantic.result.SemanticResult;
import parser.semantic.result.SemanticSuccessResult;
import token.TokenType;

import java.util.List;

public class SemanticVisitor implements ASTVisitor<SemanticResult> {

    @Override
    public SemanticResult visit(DeclarationNode node) {
        return new SemanticSuccessResult();
    }

    @Override
    public SemanticResult visit(LiteralNode node) {
        return new SemanticSuccessResult();
    }

    @Override
    public SemanticResult visit(PrintNode node) {
        return node.getExpression().accept(this);
    }

    @Override
    public SemanticResult visit(AssignationNode node) {
        TypeVisitor typeVisitor = new TypeVisitor();
        TokenType variableType = node.getDeclaration().getTypeToken().getType();
        TokenType expressionType = node.getExpression().accept(typeVisitor);

        if(variableType == TokenType.NUMBER_TYPE && expressionType == TokenType.NUMBER) {
            return new SemanticSuccessResult();
        } else if(variableType == TokenType.STRING_TYPE && expressionType == TokenType.STRING) {
            return new SemanticSuccessResult();
        } else if(expressionType == TokenType.IDENTIFIER) {
            return new SemanticSuccessResult();
        }
        return new SemanticErrorResult(List.of("Semantic error in "+node.getLine()+":"+node.getColumn()+" Variable type is " + variableType + " but value is " + expressionType));
    }

    @Override
    public SemanticResult visit(OperatorNode node) {
        TypeVisitor typeVisitor = new TypeVisitor();
        String operator = node.getOperator();
        return switch (operator) {
            case "+" -> new SemanticSuccessResult();
            case "-", "*", "/" -> {
                if (bothNumbersOrIdentifiers(node, typeVisitor)) {
                    yield new SemanticSuccessResult();
                } else {
                    yield new SemanticErrorResult(List.of("Semantic error in " + node.getLine() + ":" + node.getColumn() + " Operator " + operator + " can only be applied to numbers"));
                }
            }
            default -> new SemanticErrorResult(List.of("Semantic error in " + node.getLine() + ":" + node.getColumn() + " Operator not recognized"));
        };
    }

    @Override
    public SemanticResult visit(VariableNode node) {
        return new SemanticSuccessResult();
    }

    @Override
    public SemanticResult visit(ReasignationNode node) {
        return new SemanticSuccessResult();
    }

    private static Boolean bothNumbersOrIdentifiers(OperatorNode operatorNode, TypeVisitor typeVisitor) {
        if(operatorNode.getLeftNode().accept(typeVisitor) == TokenType.NUMBER && operatorNode.getRightNode().accept(typeVisitor) == TokenType.NUMBER) {
            return true;
        }
        else if(operatorNode.getLeftNode().accept(typeVisitor) == TokenType.IDENTIFIER || operatorNode.getRightNode().accept(typeVisitor) == TokenType.IDENTIFIER) {
            return true;
        }
        return false;
    }

}
