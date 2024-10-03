package formatter.rules.alwaysactive;

import ast.tokens.AstToken;
import ast.tokens.AstTokenType;
import ast.tokens.ValueAstToken;
import formatter.rules.TokenIndex;
import java.util.ArrayList;
import java.util.List;

public class StringQuotes implements AlwaysActiveRules {
  private final TokenIndex tokenIndex = new TokenIndex();
  public String value;

  @Override
  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public List<AstToken> format(List<AstToken> tokens) {
    List<AstToken> result = new ArrayList<>(tokens);
    int stringIndex = tokenIndex.getIndex(tokens, AstTokenType.STRING);
    if (stringIndex == -1) {
      return tokens;
    }
    AstToken token = tokens.get(stringIndex);
    result.remove(stringIndex);
    result.add(
        stringIndex,
        new ValueAstToken(
            AstTokenType.STRING,
            '"' + token.getValue() + '"',
            token.getColumn() + 1,
            token.getLine()));
    return result;
  }
}
