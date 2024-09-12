package parser.syntax.parsers;

import AST.nodes.ASTNode;
import AST.nodes.AssignationNode;
import AST.nodes.DeclarationNode;
import AST.nodes.EmptyNode;
import parser.syntax.TokenStream;
import parser.syntax.factory.ExpressionFactory;
import parser.syntax.resolver.DeclarationTypeValidator;
import token.Token;
import token.TokenType;

public class AssignationSyntaxParser implements SyntaxParser {

  @Override
  public ASTNode syntaxParse(TokenStream tokens, String version) {
    ASTNode result = parseAssignation(tokens, version);
    return result;
  }

  private ASTNode parseAssignation(TokenStream tokenStream, String version) {
    ASTNode expressionNode;

    if (tokenStream.getCurrentToken().getType() == TokenType.CONST_KEYWORD) {
      tokenStream.expect(TokenType.CONST_KEYWORD, "Expected 'const'");
    } else {
      tokenStream.expect(TokenType.LET_KEYWORD, "Expected 'let'");
    }
    DeclarationNode declarationNode = parseDeclaration(tokenStream);

    if (tokenStream.getCurrentToken().getType() != TokenType.ASSIGN) {
      tokenStream.expect(TokenType.SEMICOLON, "Expected ';'");
      tokenStream.advance();
      TokenType type = resolveEmptyType(declarationNode.getTypeToken().getType());

      expressionNode = new EmptyNode(type);
    } else {
      tokenStream.expect(TokenType.ASSIGN, "Expected '='");
      tokenStream.advance();
      expressionNode = ExpressionFactory.createExpression(tokenStream, version);
      tokenStream.expect(TokenType.SEMICOLON, "Expected ';'");
      tokenStream.advance();
    }

    return new AssignationNode(
        declarationNode, expressionNode, declarationNode.getLine(), declarationNode.getColumn());
  }

  private DeclarationNode parseDeclaration(TokenStream tokenStream) {
    Token keyWordToken = tokenStream.getCurrentToken();
    tokenStream.advance();
    Token nameToken = tokenStream.getCurrentToken();
    tokenStream.expect(TokenType.IDENTIFIER, "Expected identifier");
    tokenStream.advance();
    tokenStream.expect(TokenType.COLON, "Expected ':'");
    tokenStream.advance();
    Token typeToken = tokenStream.getCurrentToken();
    if (!DeclarationTypeValidator.isValidDeclarationType(typeToken.getType())) {
      throw new RuntimeException("Unsupported type: " + typeToken.getType().toString());
    } else {
      tokenStream.advance();
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
