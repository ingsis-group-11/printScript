package parser.syntax.parsers;

import AST.nodes.ASTNode;
import AST.nodes.ReadInputNode;
import parser.syntax.TokenStream;
import parser.syntax.factory.ExpressionFactory;
import parser.syntax.result.SyntaxErrorResult;
import parser.syntax.result.SyntaxResult;
import parser.syntax.result.SyntaxSuccessResult;
import token.TokenType;

public class ReadInputSyntaxParser implements SyntaxParser {

  @Override
  public SyntaxResult syntaxParse(TokenStream tokens) {
    ASTNode result = parseReadInput(tokens);
    if (tokens.getErrorMessages().isEmpty()) {
      return new SyntaxSuccessResult(result);
    } else {
      return new SyntaxErrorResult(tokens.getErrorMessages());
    }
  }

  private ASTNode parseReadInput(TokenStream tokenStream) {
    int line = tokenStream.getCurrentToken().getLine();
    int column = tokenStream.getCurrentToken().getColumn();
    tokenStream.expect(TokenType.READ_INPUT, "Expected 'readInput'");
    tokenStream.expect(TokenType.PARENTHESIS_OPEN, "Expected '('");
    ASTNode expressionNode = ExpressionFactory.createExpression(tokenStream);
    tokenStream.expect(TokenType.PARENTHESIS_CLOSE, "Expected ')'");
    tokenStream.expect(TokenType.SEMICOLON, "Expected ';'");
    return new ReadInputNode(expressionNode, line, column);
  }
}