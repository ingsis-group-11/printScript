package parser.syntax.parsers;

import ast.nodes.AstNode;
import ast.nodes.ReadEnvNode;
import parser.syntax.TokenStream;
import parser.syntax.factory.ExpressionFactory;
import parser.syntax.result.ExpressionResult;
import token.TokenType;

public class ReadEnvSyntaxParser implements SyntaxParser {
  @Override
  public AstNode syntaxParse(TokenStream tokens, String version) {
    AstNode result = parseReadInput(tokens, version);
    return result;
  }

  private AstNode parseReadInput(TokenStream tokenStream, String version) {

    tokenStream.expect(TokenType.READ_ENV, "Expected 'readEnv'");
    tokenStream = tokenStream.advance();

    tokenStream.expect(TokenType.PARENTHESIS_OPEN, "Expected '('");
    tokenStream = tokenStream.advance();

    ExpressionResult result = ExpressionFactory.createExpression(tokenStream, version);
    AstNode expressionNode = result.astNode();
    tokenStream = result.tokenStream();
    tokenStream.expect(TokenType.PARENTHESIS_CLOSE, "Expected ')'");
    tokenStream = tokenStream.advance();
    int line = tokenStream.getCurrentToken().getLine();
    int column = tokenStream.getCurrentToken().getColumn();
    tokenStream.expect(TokenType.SEMICOLON, "Expected ';'");
    return new ReadEnvNode(expressionNode, line, column);
  }
}
