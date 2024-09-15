package parser.syntax.parsers;

import ast.nodes.AssignationNode;
import ast.nodes.AstNode;
import ast.nodes.DeclarationNode;
import ast.nodes.EmptyNode;
import parser.syntax.TokenStream;
import parser.syntax.factory.ExpressionFactory;
import parser.syntax.resolver.DeclarationTypeValidator;
import parser.syntax.result.ExpressionResult;
import token.Token;
import token.TokenType;

public class AssignationSyntaxParser implements SyntaxParser {

  @Override
  public AstNode syntaxParse(TokenStream tokens, String version) {
    return parseAssignation(tokens, version);
  }

  private AstNode parseAssignation(TokenStream tokenStream, String version) {

    AstNode expressionNode;

    if (tokenStream.getCurrentToken().getType() == TokenType.CONST_KEYWORD) {
      tokenStream.expect(TokenType.CONST_KEYWORD, "Expected 'const'");
    } else {
      tokenStream.expect(TokenType.LET_KEYWORD, "Expected 'let'");
    }

    DeclarationNode declarationNode = parseDeclaration(tokenStream);
    tokenStream = tokenStream.advance();

    if (tokenStream.getCurrentToken().getType() != TokenType.ASSIGN) {
      tokenStream.expect(TokenType.SEMICOLON, "Expected ';'");
      TokenType type = resolveEmptyType(declarationNode.getTypeToken().getType());
      expressionNode = new EmptyNode(type);
    } else {
      tokenStream.expect(TokenType.ASSIGN, "Expected '='");
      tokenStream = tokenStream.advance();
      ExpressionResult result = ExpressionFactory.createExpression(tokenStream, version);
      expressionNode = result.astNode();
      tokenStream = result.tokenStream();
      tokenStream.expect(TokenType.SEMICOLON, "Expected ';'");
    }

    return new AssignationNode(
        declarationNode, expressionNode, declarationNode.getLine(), declarationNode.getColumn());
  }

  private DeclarationNode parseDeclaration(TokenStream tokenStream) {
    Token keyWordToken = tokenStream.getCurrentToken();
    tokenStream = tokenStream.advance();
    Token nameToken = tokenStream.getCurrentToken();
    tokenStream.expect(TokenType.IDENTIFIER, "Expected identifier");
    tokenStream = tokenStream.advance();
    tokenStream.expect(TokenType.COLON, "Expected ':'");
    tokenStream = tokenStream.advance();
    Token typeToken = tokenStream.getCurrentToken();

    if (!DeclarationTypeValidator.isValidDeclarationType(typeToken.getType())) {
      throw new RuntimeException("Unsupported type: " + typeToken.getType().toString());
    }
    return new DeclarationNode(
        typeToken, nameToken, keyWordToken, nameToken.getLine(), nameToken.getColumn());
  }

  private TokenType resolveEmptyType(TokenType type) {
    if (type == TokenType.STRING_TYPE) {
      return TokenType.STRING;
    } else if (type == TokenType.NUMBER_TYPE) {
      return TokenType.NUMBER;
    }
    return TokenType.EMPTY;
  }
}