package parser.syntax.parsers;

import AST.nodes.ASTNode;
import AST.nodes.ReassignmentNode;
import AST.nodes.VariableNode;
import parser.syntax.TokenStream;
import parser.syntax.factory.ExpressionFactory;
import parser.syntax.result.SyntaxErrorResult;
import parser.syntax.result.SyntaxResult;
import parser.syntax.result.SyntaxSuccessResult;
import token.Token;
import token.TokenType;

public class ReassignationSyntaxParser implements SyntaxParser {

  @Override
  public SyntaxResult syntaxParse(TokenStream tokens, String version) {
    ASTNode result = parseReassignment(tokens, version);
    if (tokens.getErrorMessages().isEmpty()) {
      return new SyntaxSuccessResult(result);
    } else {
      return new SyntaxErrorResult(tokens.getErrorMessages());
    }
  }

  private ASTNode parseReassignment(TokenStream tokenStream, String version) {
    VariableNode variableNode = parseVariable(tokenStream);
    tokenStream.expect(TokenType.ASSIGN, "Expected '='");
    ASTNode expressionNode = ExpressionFactory.createExpression(tokenStream, version);
    tokenStream.expect(TokenType.SEMICOLON, "Expected ';'");
    return new ReassignmentNode(
        variableNode, expressionNode, expressionNode.getLine(), expressionNode.getColumn());
  }

  private VariableNode parseVariable(TokenStream tokenStream) {
    Token variable = tokenStream.getCurrentToken();
    tokenStream.advance();
    return new VariableNode(variable);
  }
}