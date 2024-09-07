package inputType;

import token.TokenType;

public class InputTypeTransformer {
  public TokenType detectInputType(String input) {
    switch (input.toLowerCase()) {
      case "true":
      case "false":
        return TokenType.BOOLEAN;
      default:
        if (isInteger(input) || isDouble(input)) {
          return TokenType.NUMBER;
        } else {
          return TokenType.STRING;
        }
    }
  }

  private boolean isInteger(String input) {
    return input.matches("-?\\d+");
  }

  private boolean isDouble(String input) {
    return input.matches("-?\\d+(\\.\\d+)?");
  }
}
