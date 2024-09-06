package parser.syntax.parsers;

import AST.nodes.ASTNode;
import AST.nodes.PrintNode;
import parser.syntax.TokenStream;
import parser.syntax.factory.ExpressionFactory;
import parser.syntax.result.SyntaxErrorResult;
import parser.syntax.result.SyntaxResult;
import parser.syntax.result.SyntaxSuccessResult;
import token.TokenType;

public class PrintSyntaxParser implements SyntaxParser {

    @Override
    public SyntaxResult syntaxParse(TokenStream tokens) {
        ASTNode result = parsePrint(tokens);
        if (tokens.getErrorMessages().isEmpty()) {
            return new SyntaxSuccessResult(result);
        } else {
            return new SyntaxErrorResult(tokens.getErrorMessages());
        }
    }

    private ASTNode parsePrint(TokenStream tokenStream) {
        tokenStream.expect(TokenType.PRINT_KEYWORD, "Expected 'println'");
        tokenStream.expect(TokenType.PARENTHESIS_OPEN, "Expected '('");
        ASTNode expressionNode = ExpressionFactory.createExpression(tokenStream);
        tokenStream.expect(TokenType.PARENTHESIS_CLOSE, "Expected ')'");
        tokenStream.expect(TokenType.SEMICOLON, "Expected ';'");
        return new PrintNode(expressionNode, expressionNode.getLine(), expressionNode.getColumn());
    }
}