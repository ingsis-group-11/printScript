package parser.syntax;

import AST.nodes.ASTNode;
import AST.nodes.DeclarationNode;
import AST.nodes.ReassignmentNode;
import AST.nodes.VariableNode;
import token.Token;
import token.TokenType;

import java.util.List;

public class ReassignationSyntaxParser implements SyntaxParser{

    @Override
    public ASTNode syntaxParse(List<Token> tokens) {
        TokenStream tokenStream = new TokenStream(tokens);
        return parseReassignment(tokenStream);
    }

    private ASTNode parseReassignment(TokenStream tokenStream) {
        VariableNode variableNode = parseVariable(tokenStream);
        tokenStream.expect(TokenType.IDENTIFIER, "Expected a variable name");
        tokenStream.expect(TokenType.ASSIGN, "Expected '='");
        ASTNode expressionNode = ExpressionFactory.createExpression(tokenStream);
        tokenStream.expect(TokenType.SEMICOLON, "Expected ';'");
        return new ReassignmentNode(variableNode, expressionNode, expressionNode.getLine(), expressionNode.getColumn());
    }

    private VariableNode parseVariable(TokenStream tokenStream) {
        Token variable = tokenStream.getCurrentToken();
        return new VariableNode(variable);
    }
}
