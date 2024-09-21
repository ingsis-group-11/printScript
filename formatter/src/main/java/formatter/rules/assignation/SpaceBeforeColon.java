package formatter.rules.assignation;

import ast.tokens.AstToken;
import ast.tokens.AstTokenType;
import ast.tokens.ValueAstToken;
import formatter.rules.TokenIndex;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SpaceBeforeColon implements AssignationRule {
  private final TokenIndex tokenIndex = new TokenIndex();
  public String value;

  @Override
  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public List<AstToken> format(List<AstToken> tokens) {
    if (Objects.equals(value, "false")) {
      return tokens;
    }
    List<AstToken> result = new ArrayList<>(tokens);
    int colonIndex = tokenIndex.getIndex(tokens, AstTokenType.COLON);
    if (colonIndex == -1) {
      return result;
    }
    result.add(
        colonIndex,
        new ValueAstToken(
            AstTokenType.WHITESPACE,
            " ",
            tokens.get(colonIndex).getColumn(),
            tokens.get(colonIndex).getLine()));
    return result;
  }
}
