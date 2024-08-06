package parser.syntax;

import AST.nodes.ASTNode;
import AST.nodes.LiteralNode;
import AST.nodes.OperatorNode;
import AST.nodes.VariableNode;
import token.Token;
import token.TokenType;
import java.util.Iterator;


public class ExpressionFactory {

    public static ASTNode createExpression(Iterator<Token> iterator) {
        if (!iterator.hasNext()) {
            throw new IllegalArgumentException("Invalid expression");
        }

        ASTNode left = parsePrimary(iterator);

        while (iterator.hasNext()) {
            Token token = iterator.next();
            if (token.getType() == TokenType.OPERATOR) {
                ASTNode right = parsePrimary(iterator);
                left = new OperatorNode(token.getValue(), left, right, token.getLine(), token.getColumn());
            } else {
                break;
            }
        }

        return left;
    }

    private static ASTNode parsePrimary(Iterator<Token> iterator) {
        if (iterator.hasNext()) {
            Token token = iterator.next();
            if (token.getType() == TokenType.NUMBER || token.getType() == TokenType.STRING) {
                return new LiteralNode(token);
            } else if (token.getType() == TokenType.IDENTIFIER) {
                return new VariableNode(token);
            }
        }
        throw new IllegalArgumentException("Invalid primary expression");
    }
}