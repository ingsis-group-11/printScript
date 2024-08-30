package parser.syntax;

import AST.nodes.ASTNode;
import AST.nodes.ReassignmentNode;
import AST.nodes.VariableNode;
import parser.syntax.result.SyntaxErrorResult;
import parser.syntax.result.SyntaxResult;
import parser.syntax.result.SyntaxSuccessResult;
import token.Token;
import token.TokenType;

import java.util.Iterator;
import java.util.List;

public class ReassignationSyntaxParser implements SyntaxParser {

    @Override
    public SyntaxResult syntaxParse(Iterator<Token> tokens) {
        TokenStream tokenStream = new TokenStream(tokens);
        ASTNode result = parseReassignment(tokenStream);
        if (tokenStream.getErrorMessages().isEmpty()) {
            return new SyntaxSuccessResult(result);
        } else {
            return new SyntaxErrorResult(tokenStream.getErrorMessages());
        }
    }

    private ASTNode parseReassignment(TokenStream tokenStream) {
        VariableNode variableNode = parseVariable(tokenStream);
        tokenStream.expect(TokenType.IDENTIFIER, "Expected a variable name");
        tokenStream.expect(TokenType.ASSIGN, "Expected '='");
        ASTNode expressionNode = ExpressionFactory.createExpression(tokenStream);
        tokenStream.expect(TokenType.SEMICOLON, "Expected ';'");
        return new ReassignmentNode(
                variableNode, expressionNode, expressionNode.getLine(), expressionNode.getColumn());
    }

    private VariableNode parseVariable(TokenStream tokenStream) {
        Token variable = tokenStream.getCurrentToken();
        return new VariableNode(variable);
    }
}