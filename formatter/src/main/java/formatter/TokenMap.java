package formatter;

import formatter.tokenFormatter.*;
import token.TokenType;

import java.util.HashMap;
import java.util.Map;

public class TokenMap {
  Map<TokenType, TokenFormatter> tokenMap = new HashMap<>();

  public TokenMap() {
    tokenMap.put(TokenType.COLON, new ColonFormatter());
    tokenMap.put(TokenType.ASSIGN, new AssignFormatter());
    tokenMap.put(TokenType.SEMICOLON, new SemicolonFormatter());
    tokenMap.put(TokenType.PRINT_KEYWORD, new PrintFormatter());
    tokenMap.put(TokenType.IDENTIFIER, new IdentifierFormatter());
    tokenMap.put(TokenType.NUMBER_TYPE, new TypesFormatter());
    tokenMap.put(TokenType.STRING_TYPE, new TypesFormatter());
    tokenMap.put(TokenType.STRING, new StringFormatter());
    tokenMap.put(TokenType.NUMBER, new TypesFormatter());
    tokenMap.put(TokenType.BOOLEAN, new TypesFormatter());
    tokenMap.put(TokenType.BOOLEAN_TYPE, new TypesFormatter());
    tokenMap.put(TokenType.IF_KEYWORD, new IfFormatter());
    tokenMap.put(TokenType.BRACKET_OPEN, new BracketFormatter());
  }

  public TokenFormatter getTokenFormatter(TokenType tokenType) {
    return tokenMap.get(tokenType);
  }

  public boolean containsToken(TokenType tokenType) {
    return tokenMap.containsKey(tokenType);
  }
}
