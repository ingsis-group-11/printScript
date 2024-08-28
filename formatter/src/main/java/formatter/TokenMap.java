package formatter;

import formatter.tokenFormatter.*;
import token.TokenType;

import java.util.HashMap;
import java.util.Map;

public class TokenMap {
  Map<TokenType, TokenFormatter> tokenMap = new HashMap<TokenType, TokenFormatter>();

  public TokenMap() {
    tokenMap.put(TokenType.COLON, new ColonFormatter());
    tokenMap.put(TokenType.ASSIGN, new AssignFormatter());
    tokenMap.put(TokenType.SEMICOLON, new SemicolonFormatter());
    tokenMap.put(TokenType.OPERATOR, new OperatorFormatter());
    tokenMap.put(TokenType.PRINT_KEYWORD, new PrintFormatter());
  }

  public TokenFormatter getTokenFormatter(TokenType tokenType) {
    return tokenMap.get(tokenType);
  }

  public boolean containsToken(TokenType tokenType) {
    return tokenMap.containsKey(tokenType);
  }
}
