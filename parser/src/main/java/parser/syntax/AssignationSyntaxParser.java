package parser.syntax;

import AST.nodes.ASTNode;
import AST.nodes.AssignationNode;
import AST.nodes.DeclarationNode;
import token.Token;
import token.TokenType;

import java.util.List;

public class AssignationSyntaxParser implements SyntaxParser {

    @Override
    public ASTNode syntaxParse(List<Token> tokens) {
        TokenStream tokenStream = new TokenStream(tokens);
        return parseAssignation(tokenStream);
    }

    private ASTNode parseAssignation(TokenStream tokenStream) {
        tokenStream.expect(TokenType.LET_KEYWORD, "Expected 'let'");
        DeclarationNode declarationNode = parseDeclaration(tokenStream);
        tokenStream.expect(TokenType.ASSIGN, "Expected '='");
        ASTNode expressionNode = ExpressionFactory.createExpression(tokenStream);
        tokenStream.expect(TokenType.SEMICOLON, "Expected ';'");
        return new AssignationNode(declarationNode, expressionNode, declarationNode.getLine(), declarationNode.getColumn());
    }

    private DeclarationNode parseDeclaration(TokenStream tokenStream) {
        Token nameToken = tokenStream.getCurrentToken();
        tokenStream.expect(TokenType.IDENTIFIER, "Expected identifier");
        tokenStream.expect(TokenType.COLON, "Expected ':'");
        Token typeToken = tokenStream.getCurrentToken();
        if (typeToken.getType() != TokenType.STRING_TYPE && typeToken.getType() != TokenType.NUMBER_TYPE) {
            throw new IllegalArgumentException("Expected type to be 'string' or 'number'");
        }
        tokenStream.advance();
        return new DeclarationNode(typeToken, nameToken, nameToken.getLine(), nameToken.getColumn());
    }
}