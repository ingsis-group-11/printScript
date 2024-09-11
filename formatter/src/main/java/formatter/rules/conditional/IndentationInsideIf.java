package formatter.rules.conditional;

import formatter.rules.TokenIndex;
import token.Token;
import token.TokenType;
import token.ValueToken;

import java.util.ArrayList;
import java.util.List;

public class IndentationInsideIf implements IfRule {
  private final TokenIndex tokenIndex = new TokenIndex();
  public String value;

  @Override
  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public List<Token> format(List<Token> tokens) {
    List<Token> formattedTokens = new ArrayList<>();
    int indentationLevel = 0;
    boolean newLine = false;
    int tokensSize = tokens.size();

    for (int i = 0; i < tokensSize; i++) {
      Token token = tokens.get(i);
      switch (token.getType()) {
        case IF_KEYWORD:
          if (newLine) {
            addIndentation(formattedTokens, indentationLevel, token);
          }
          formattedTokens.add(token);
          newLine = false;
          break;

        case BRACE_OPEN:
          formattedTokens.add(token);
          addLineBreak(formattedTokens, token);
          indentationLevel++;
          newLine = true;
          break;

        case BRACE_CLOSE:
          indentationLevel--;
          addIndentation(formattedTokens, indentationLevel, token);
          formattedTokens.add(token);
          // Solo agregar line break si no es el último brace_close
          if (i < tokensSize - 1) {
            addLineBreak(formattedTokens, token);
          }
          newLine = true;
          break;

        case SEMICOLON:
          formattedTokens.add(token);
          addLineBreak(formattedTokens, token);
          newLine = true;
          break;

        case LINE_BREAK:
          if (!newLine) {
            formattedTokens.add(token);
            newLine = true;
          }
          break;

        case WHITESPACE:
          formattedTokens.add(token); // Preservar el token WHITESPACE
          break;

        default:
          if (newLine) {
            addIndentation(formattedTokens, indentationLevel, token);
            newLine = false;
          }
          formattedTokens.add(token);
          break;
      }
    }

    return formattedTokens;
  }

  private void addIndentation(List<Token> tokens, int level, Token baseToken) {
    if (level > 0) {
      String indentation = " ".repeat(level * Integer.parseInt(value));
      Token indentToken = new ValueToken(TokenType.WHITESPACE, indentation, baseToken.getLine(), baseToken.getColumn());
      tokens.add(indentToken);
    }
  }

  private void addLineBreak(List<Token> tokens, Token baseToken) {
    Token lineBreakToken = new ValueToken(TokenType.LINE_BREAK, "\n", baseToken.getLine() + 1, 1);
    tokens.add(lineBreakToken);
  }
}


// if () {
//   let a: number = 1;
//   if () {
//    println("Hello");
//   }
// }