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
    public SyntaxResult syntaxParse(TokenStream tokens, String version) {
        ASTNode result = parsePrint(tokens, version);
        if (tokens.getErrorMessages().isEmpty()) {
            return new SyntaxSuccessResult(result);
        } else {
            return new SyntaxErrorResult(tokens.getErrorMessages());
        }
    }

    private ASTNode parsePrint(TokenStream tokenStream, String version) {
        tokenStream.expect(TokenType.PRINT_KEYWORD, "Expected 'println'");
        tokenStream.expect(TokenType.PARENTHESIS_OPEN, "Expected '('");
        ASTNode expressionNode = ExpressionFactory.createExpression(tokenStream, version);
        tokenStream.expect(TokenType.PARENTHESIS_CLOSE, "Expected ')'");
        tokenStream.expect(TokenType.SEMICOLON, "Expected ';'");
        return new PrintNode(expressionNode, expressionNode.getLine(), expressionNode.getColumn());
    }
}