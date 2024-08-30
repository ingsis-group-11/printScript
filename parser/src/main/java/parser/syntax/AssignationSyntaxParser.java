package parser.syntax;

import AST.nodes.ASTNode;
import AST.nodes.AssignationNode;
import AST.nodes.DeclarationNode;
import parser.syntax.result.SyntaxErrorResult;
import parser.syntax.result.SyntaxResult;
import parser.syntax.result.SyntaxSuccessResult;
import token.Token;
import token.TokenType;
import java.util.Iterator;

public class AssignationSyntaxParser implements SyntaxParser {

  @Override
  public SyntaxResult syntaxParse(Iterator<Token> tokens) {
    TokenStream tokenStream = new TokenStream(tokens);
    ASTNode result = parseAssignation(tokenStream);
    if (tokenStream.getErrorMessages().isEmpty()) {
      return new SyntaxSuccessResult(result);
    } else {
      return new SyntaxErrorResult(tokenStream.getErrorMessages());
    }
  }

  private ASTNode parseAssignation(TokenStream tokenStream) {
    DeclarationNode declarationNode = parseDeclaration(tokenStream);
    tokenStream.expect(TokenType.ASSIGN, "Expected '='");
    ASTNode expressionNode = ExpressionFactory.createExpression(tokenStream);
    tokenStream.expect(TokenType.SEMICOLON, "Expected ';'");
    return new AssignationNode(
        declarationNode, expressionNode, declarationNode.getLine(), declarationNode.getColumn());
  }

  private DeclarationNode parseDeclaration(TokenStream tokenStream) {
    Token nameToken = tokenStream.getCurrentToken();
    tokenStream.expect(TokenType.IDENTIFIER, "Expected identifier");
    tokenStream.expect(TokenType.COLON, "Expected ':'");
    Token typeToken = tokenStream.getCurrentToken();
    if (typeToken.getType() != TokenType.STRING_TYPE
        && typeToken.getType() != TokenType.NUMBER_TYPE) {
      tokenStream.getErrorMessages().add("Expected type to be 'string' or 'number'");
    } else {
      tokenStream.advance();
    }
    return new DeclarationNode(typeToken, nameToken, nameToken.getLine(), nameToken.getColumn());
  }
}