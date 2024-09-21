package formatter.rules.alwaysactive;

import ast.tokens.AstToken;
import ast.tokens.AstTokenType;
import ast.tokens.ValueAstToken;
import formatter.rules.TokenIndex;
import java.util.ArrayList;
import java.util.List;

public class LineBreakAfterSemicolon implements AlwaysActiveRules {
  private final TokenIndex tokenIndex = new TokenIndex();
  public String value;

  @Override
  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public List<AstToken> format(List<AstToken> tokens) {
    List<AstToken> result = new ArrayList<>(tokens);
    int semicolonIndex = tokenIndex.getIndex(tokens, AstTokenType.SEMICOLON);
    result.add(
        semicolonIndex + 1,
        new ValueAstToken(
            AstTokenType.LINE_BREAK,
            "\n",
            tokens.get(semicolonIndex).getColumn() + 1,
            tokens.get(semicolonIndex).getLine()));
    return result;
  }
}
