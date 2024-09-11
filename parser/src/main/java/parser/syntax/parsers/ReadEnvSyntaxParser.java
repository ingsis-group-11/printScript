package parser.syntax.parsers;

import AST.nodes.ASTNode;
import AST.nodes.ReadEnvNode;
import parser.syntax.TokenStream;
import parser.syntax.factory.ExpressionFactory;
import token.TokenType;

public class ReadEnvSyntaxParser implements SyntaxParser {
  @Override
  public ASTNode syntaxParse(TokenStream tokens, String version) {
    ASTNode result = parseReadInput(tokens, version);
    return result;
  }

  private ASTNode parseReadInput(TokenStream tokenStream, String version) {
    int line = tokenStream.getCurrentToken().getLine();
    int column = tokenStream.getCurrentToken().getColumn();
    tokenStream.expect(TokenType.READ_ENV, "Expected 'readEnv'");
    tokenStream.advance();

    tokenStream.expect(TokenType.PARENTHESIS_OPEN, "Expected '('");
    tokenStream.advance();

    ASTNode expressionNode = ExpressionFactory.createExpression(tokenStream, version);

    tokenStream.expect(TokenType.PARENTHESIS_CLOSE, "Expected ')'");
    tokenStream.advance();

    tokenStream.expect(TokenType.SEMICOLON, "Expected ';'");
    tokenStream.advance();
    return new ReadEnvNode(expressionNode, line, column);
  }
}
