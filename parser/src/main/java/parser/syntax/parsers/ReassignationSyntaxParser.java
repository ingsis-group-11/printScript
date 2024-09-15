package parser.syntax.parsers;

import ast.nodes.AstNode;
import ast.nodes.ReassignmentNode;
import ast.nodes.VariableNode;
import parser.syntax.TokenStream;
import parser.syntax.factory.ExpressionFactory;
import parser.syntax.result.ExpressionResult;
import token.Token;
import token.TokenType;

public class ReassignationSyntaxParser implements SyntaxParser {

  @Override
  public AstNode syntaxParse(TokenStream tokens, String version) {
    AstNode result = parseReassignment(tokens, version);
    return result;
  }

  private AstNode parseReassignment(TokenStream tokenStream, String version) {

    VariableNode variableNode = parseVariable(tokenStream);
    tokenStream = tokenStream.advance();

    tokenStream.expect(TokenType.ASSIGN, "Expected '='");
    tokenStream = tokenStream.advance();

    ExpressionResult result = ExpressionFactory.createExpression(tokenStream, version);
    AstNode expressionNode = result.astNode();
    tokenStream = result.tokenStream();
    tokenStream.expect(TokenType.SEMICOLON, "Expected ';'");

    return new ReassignmentNode(
        variableNode, expressionNode, expressionNode.getLine(), expressionNode.getColumn());
  }

  private VariableNode parseVariable(TokenStream tokenStream) {
    Token variable = tokenStream.getCurrentToken();
    return new VariableNode(variable);
  }
}
