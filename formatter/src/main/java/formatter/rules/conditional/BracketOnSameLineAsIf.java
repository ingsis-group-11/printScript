package formatter.rules.conditional;

import ast.tokens.AstToken;
import ast.tokens.AstTokenType;
import ast.tokens.ValueAstToken;
import formatter.rules.TokenIndex;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BracketOnSameLineAsIf implements IfRule {
  private final TokenIndex tokenIndex = new TokenIndex();
  public String value;

  @Override
  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public List<AstToken> format(List<AstToken> tokens) {
    List<AstToken> result = new ArrayList<>(tokens);
    int bracketIndex = tokenIndex.getIndex(tokens, AstTokenType.BRACE_OPEN);
    if (Objects.equals(value, "false")) {
      result.add(
          bracketIndex,
          new ValueAstToken(
              AstTokenType.LINE_BREAK,
              "\n",
              tokens.get(bracketIndex).getColumn() + 1,
              tokens.get(bracketIndex).getLine()));
    } else {
      result.add(
          bracketIndex,
          new ValueAstToken(
              AstTokenType.WHITESPACE,
              " ",
              tokens.get(bracketIndex).getColumn() + 1,
              tokens.get(bracketIndex).getLine()));
    }
    return result;
  }
}
