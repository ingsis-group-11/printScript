package interpreter;

public enum Operators {
  ADDITION("+"),
  SUBTRACTION("-"),
  MULTIPLICATION("*"),
  DIVISION("/");

  private final String symbol;

  Operators(String symbol) {
    this.symbol = symbol;
  }

  public String getSymbol() {
    return symbol;
  }

  public static Operators fromSymbol(String symbol) {
    for (Operators operator : Operators.values()) {
      if (operator.getSymbol().equals(symbol)) {
        return operator;
      }
    }
    throw new IllegalArgumentException("Invalid operator symbol: " + symbol);
  }
}
