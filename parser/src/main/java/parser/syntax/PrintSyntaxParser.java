package parser.syntax;

import AST.nodes.ASTNode;
import AST.nodes.PrintNode;
import parser.syntax.result.SyntaxErrorResult;
import parser.syntax.result.SyntaxResult;
import parser.syntax.result.SyntaxSuccessResult;
import token.Token;
import token.TokenType;

import java.util.List;

public class PrintSyntaxParser implements SyntaxParser {

    @Override
    public SyntaxResult syntaxParse(List<Token> tokens) {
        TokenStream tokenStream = new TokenStream(tokens);
        ASTNode result = parsePrint(tokenStream);
        if (tokenStream.getErrorMessages().isEmpty()) {
            return new SyntaxSuccessResult(result);
        } else {
            return new SyntaxErrorResult(tokenStream.getErrorMessages());
        }
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