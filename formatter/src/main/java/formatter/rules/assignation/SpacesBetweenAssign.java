package formatter.rules.assignation;

import ast.tokens.AstToken;
import ast.tokens.AstTokenType;
import ast.tokens.ValueAstToken;
import formatter.rules.TokenIndex;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SpacesBetweenAssign implements AssignationRule {
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
    int assignIndex = tokenIndex.getIndex(tokens, AstTokenType.ASSIGN);
    if (assignIndex == -1) {
      return result;
    }
    result.add(
        assignIndex,
        new ValueAstToken(
            AstTokenType.WHITESPACE,
            " ",
            tokens.get(assignIndex).getColumn(),
            tokens.get(assignIndex).getLine()));
    result.add(
        assignIndex + 2,
        new ValueAstToken(
            AstTokenType.WHITESPACE,
            " ",
            tokens.get(assignIndex).getColumn() + 2,
            tokens.get(assignIndex).getLine()));
    return result;
  }
}
