package parser.syntax;

import AST.nodes.ASTNode;
import AST.nodes.LiteralNode;
import AST.nodes.OperatorNode;
import AST.nodes.VariableNode;
import token.Token;
import token.TokenType;

public class ExpressionFactory {

    public static ASTNode createExpression(TokenStream tokenStream) {
        if (tokenStream.getCurrentToken() == null) {
            throw new IllegalArgumentException("Invalid expression");
        }

        ASTNode left = parseExpression(tokenStream);

        while (tokenStream.getCurrentToken() != null) {
            Token token = tokenStream.getCurrentToken();
            if (token.getType() == TokenType.OPERATOR) {
                tokenStream.advance();
                ASTNode right = parseExpression(tokenStream);
                left = new OperatorNode(token.getValue(), left, right, token.getLine(), token.getColumn());
            } else {
                break;
            }
        }

        return left;
    }

    private static ASTNode parseExpression(TokenStream tokenStream) {
        Token token = tokenStream.getCurrentToken();
        if (token != null) {
            if (token.getType() == TokenType.NUMBER || token.getType() == TokenType.STRING) {
                tokenStream.advance();
                return new LiteralNode(token);
            } else if (token.getType() == TokenType.IDENTIFIER) {
                tokenStream.advance();
                return new VariableNode(token);
            }
        }
        assert token != null;
        throw new IllegalArgumentException("Invalid expression at column " + token.getColumn() + " line " + token.getLine());
    }
}