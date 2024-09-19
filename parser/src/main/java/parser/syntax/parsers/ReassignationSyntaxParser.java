package parser.syntax.parsers;

import ast.nodes.AstNode;
import ast.nodes.ReassignmentNode;
import ast.nodes.VariableNode;
import java.util.Optional;
import parser.syntax.TokenStream;
import parser.syntax.factory.ExpressionFactory;
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
    handleExpect(tokenStream.expect(TokenType.ASSIGN, "Expected '='"));
    tokenStream.advance();

    AstNode expressionNode = ExpressionFactory.createExpression(tokenStream, version);

    handleExpect(tokenStream.expect(TokenType.SEMICOLON, "Expected ';'"));
    tokenStream.advance();

    return new ReassignmentNode(
        variableNode, expressionNode, expressionNode.getLine(), expressionNode.getColumn());
  }

  private VariableNode parseVariable(TokenStream tokenStream) {
    Token variable = tokenStream.getCurrentToken();
    tokenStream.advance();
    return new VariableNode(variable);
  }

  private void handleExpect(Optional<Exception> exception) {
    exception.ifPresent(
        e -> {
          if (e instanceof RuntimeException) {
            throw (RuntimeException) e;
          } else {
            throw new RuntimeException(e);
          }
        });
  }
}
