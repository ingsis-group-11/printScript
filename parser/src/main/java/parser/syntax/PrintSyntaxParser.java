package parser.syntax;

import AST.nodes.ASTNode;
import AST.nodes.LiteralNode;
import AST.nodes.PrintNode;
import AST.nodes.VariableNode;
import token.Token;
import token.TokenType;

import java.util.List;

public class PrintSyntaxParser implements SyntaxParser {

    @Override
    public ASTNode syntaxParse(List<Token> tokens) {
        TokenStream tokenStream = new TokenStream(tokens);
        return parsePrint(tokenStream);
    }

    private ASTNode parsePrint(TokenStream tokenStream) {
        tokenStream.expect(TokenType.PRINT_KEYWORD, "Expected 'print'");
        tokenStream.expect(TokenType.PARENTHESIS_OPEN, "Expected '('");
        ASTNode expressionNode = ExpressionFactory.createExpression(tokenStream);
        tokenStream.expect(TokenType.PARENTHESIS_CLOSE, "Expected ')'");
        tokenStream.expect(TokenType.SEMICOLON, "Expected ';'");
        return new PrintNode(expressionNode, expressionNode.getLine(), expressionNode.getColumn());
    }
}