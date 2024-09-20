package parser.syntax.map;

import ast.tokens.AstTokenType;
import java.util.HashMap;
import token.TokenType;

public class TokenAdapter {

  private final HashMap<TokenType, AstTokenType> map = new HashMap<>();

  public TokenAdapter() {
    map.put(TokenType.NUMBER, AstTokenType.NUMBER);
    map.put(TokenType.STRING, AstTokenType.STRING);
    map.put(TokenType.BOOLEAN, AstTokenType.BOOLEAN);
    map.put(TokenType.IDENTIFIER, AstTokenType.IDENTIFIER);
    map.put(TokenType.OPERATOR, AstTokenType.OPERATOR);
    map.put(TokenType.ASSIGN, AstTokenType.ASSIGN);
    map.put(TokenType.SEMICOLON, AstTokenType.SEMICOLON);
    map.put(TokenType.PARENTHESIS_OPEN, AstTokenType.PARENTHESIS_OPEN);
    map.put(TokenType.PARENTHESIS_CLOSE, AstTokenType.PARENTHESIS_CLOSE);
    map.put(TokenType.COLON, AstTokenType.COLON);
    map.put(TokenType.WHITESPACE, AstTokenType.WHITESPACE);
    map.put(TokenType.LINE_BREAK, AstTokenType.LINE_BREAK);
    map.put(TokenType.CONST_KEYWORD, AstTokenType.CONST_KEYWORD);
    map.put(TokenType.LET_KEYWORD, AstTokenType.LET_KEYWORD);
    map.put(TokenType.BRACE_OPEN, AstTokenType.BRACE_OPEN);
    map.put(TokenType.BRACE_CLOSE, AstTokenType.BRACE_CLOSE);
    map.put(TokenType.IF_KEYWORD, AstTokenType.IF_KEYWORD);
    map.put(TokenType.ELSE_KEYWORD, AstTokenType.ELSE_KEYWORD);
    map.put(TokenType.READ_INPUT, AstTokenType.READ_INPUT);
    map.put(TokenType.READ_ENV, AstTokenType.READ_ENV);
    map.put(TokenType.EMPTY, AstTokenType.EMPTY);
    map.put(TokenType.STRING_TYPE, AstTokenType.STRING_TYPE);
    map.put(TokenType.NUMBER_TYPE, AstTokenType.NUMBER_TYPE);
    map.put(TokenType.BOOLEAN_TYPE, AstTokenType.BOOLEAN_TYPE);
  }

  public AstTokenType getAstTokenType(TokenType tokenType) {
    return map.get(tokenType);
  }
}
