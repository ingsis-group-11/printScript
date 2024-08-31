package interpreter.inputType;

import token.TokenType;

public class InputTypeDetector {
  public TokenType detectInputType(String input) {
    switch (input.toLowerCase()) {
      case "true":
      case "false":
        return TokenType.BOOLEAN;
      default:
        if (isInteger(input)) {
          return TokenType.NUMBER;
        } else if (isDouble(input)) {
          return TokenType.NUMBER;
        } else {
          return TokenType.STRING;
        }
    }
  }

  private boolean isInteger(String input) {
    try {
      Integer.parseInt(input);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  private boolean isDouble(String input) {
    try {
      Double.parseDouble(input);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }
}

