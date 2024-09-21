package formatter.rules.print;

import ast.tokens.AstToken;
import ast.tokens.AstTokenType;
import ast.tokens.ValueAstToken;
import formatter.rules.TokenIndex;
import java.util.ArrayList;
import java.util.List;

public class LinebreakAfterPrint implements PrintRule {
  private final TokenIndex tokenIndex = new TokenIndex();
  public String value;

  @Override
  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public List<AstToken> format(List<AstToken> tokens) {
    int lineBreaks = Integer.parseInt(value);
    List<AstToken> result = new ArrayList<>(tokens);
    int printEndIndex = tokenIndex.getIndex(tokens, AstTokenType.SEMICOLON);
    for (int i = 0; i < lineBreaks - 1; i++) {
      result.add(
          printEndIndex + 1,
          new ValueAstToken(
              AstTokenType.LINE_BREAK,
              "\n",
              tokens.get(printEndIndex).getColumn(),
              tokens.get(printEndIndex).getLine() + i));
    }
    return result;
  }
}
