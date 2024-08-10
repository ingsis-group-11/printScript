package parser.syntax;

import AST.nodes.ASTNode;
import AST.nodes.LiteralNode;
import AST.nodes.OperatorNode;
import AST.nodes.VariableNode;
import token.Token;
import token.TokenType;

public class ExpressionFactory {

    public static ASTNode createExpression(TokenStream tokenStream) {
        return parseBinaryExpression(tokenStream, 0);
    }

    private static ASTNode parsePrimaryExpression(TokenStream tokenStream) {
        Token token = tokenStream.getCurrentToken();
        if (token != null) {
            if (token.getType() == TokenType.NUMBER || token.getType() == TokenType.STRING) {
                tokenStream.advance();
                return new LiteralNode(token);
            } else if (token.getType() == TokenType.IDENTIFIER) {
                tokenStream.advance();
                return new VariableNode(token);
            } else if (token.getType() == TokenType.PARENTHESIS_OPEN) {
                tokenStream.advance();
                ASTNode expression = parseBinaryExpression(tokenStream, 0);
                tokenStream.expect(TokenType.PARENTHESIS_CLOSE, "Expected ')'");
                return expression;
            }
        }
        assert token != null;
        throw new IllegalArgumentException("Invalid expression at column " + token.getColumn() + " line " + token.getLine());
    }

    private static ASTNode parseBinaryExpression(TokenStream tokenStream, int precedence) {
        ASTNode left = parsePrimaryExpression(tokenStream);

        while (true) {
            Token token = tokenStream.getCurrentToken();
            if (token == null || token.getType() != TokenType.OPERATOR) {
                break;
            }

            int tokenPrecedence = getPriority(token);
            if (tokenPrecedence < precedence) {
                break;
            }

            tokenStream.advance();
            ASTNode right = parseBinaryExpression(tokenStream, tokenPrecedence + 1);
            left = new OperatorNode(token.getValue(), left, right, token.getLine(), token.getColumn());
        }

        return left;
    }

    private static int getPriority(Token token) {
        return switch (token.getValue()) {
            case "*", "/" -> 2;
            case "+", "-" -> 1;
            default -> 0;
        };
    }
}