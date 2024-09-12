package parser.syntax.parsers;

import ast.nodes.AstNode;
import ast.nodes.PrintNode;
import parser.syntax.TokenStream;
import parser.syntax.factory.ExpressionFactory;
import token.TokenType;

public class PrintSyntaxParser implements SyntaxParser {

  @Override
  public AstNode syntaxParse(TokenStream tokens, String version) {
    AstNode result = parsePrint(tokens, version);
    return result;
  }

  private AstNode parsePrint(TokenStream tokenStream, String version) {
    tokenStream.expect(TokenType.PRINT_KEYWORD, "Expected 'println'");
    tokenStream.advance();
    tokenStream.expect(TokenType.PARENTHESIS_OPEN, "Expected '('");
    tokenStream.advance();
    AstNode expressionNode = ExpressionFactory.createExpression(tokenStream, version);
    tokenStream.expect(TokenType.PARENTHESIS_CLOSE, "Expected ')'");
    tokenStream.advance();
    tokenStream.expect(TokenType.SEMICOLON, "Expected ';'");
    tokenStream.advance();
    return new PrintNode(expressionNode, expressionNode.getLine(), expressionNode.getColumn());
  }
}
