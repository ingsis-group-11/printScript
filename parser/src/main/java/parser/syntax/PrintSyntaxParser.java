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
        Token valueToken = tokenStream.getCurrentToken();
        ASTNode node;
        if (tokenStream.match(TokenType.IDENTIFIER)) {
            node = new VariableNode(valueToken);
            tokenStream.advance();
        } else if (tokenStream.match(TokenType.STRING) || tokenStream.match(TokenType.NUMBER)) {
            node = new LiteralNode(valueToken);
            tokenStream.advance();
        } else {
            String message = "Invalid print value at column " + valueToken.getColumn() + " line " + valueToken.getLine();
            throw new RuntimeException(message);
        }
        tokenStream.expect(TokenType.PARENTHESIS_CLOSE, "Expected ')'");
        if (tokenStream.isAtEnd() || !tokenStream.match(TokenType.SEMICOLON)) {
            Token lastToken = tokenStream.getLastToken();
            throw new RuntimeException("Expected ';' at column " + (lastToken != null ? lastToken.getColumn() : "unknown") + " line " + (lastToken != null ? lastToken.getLine() : "unknown"));
        }
        tokenStream.advance();
        return new PrintNode(node, valueToken.getLine(), valueToken.getColumn());
    }
}