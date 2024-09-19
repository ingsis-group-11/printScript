package inputyype;

import ast.tokens.AstTokenType;

public class InputTypeTransformer {
  public AstTokenType detectInputType(String input) {
    switch (input.toLowerCase()) {
      case "true":
      case "false":
        return AstTokenType.BOOLEAN;
      default:
        if (isInteger(input) || isDouble(input)) {
          return AstTokenType.NUMBER;
        } else {
          return AstTokenType.STRING;
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
