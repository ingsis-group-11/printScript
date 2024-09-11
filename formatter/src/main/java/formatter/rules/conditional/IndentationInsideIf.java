package formatter.rules.conditional;

import formatter.rules.TokenIndex;
import token.Token;
import token.TokenType;
import token.ValueToken;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.List;

public class IndentationInsideIf implements IfRule {
  private final TokenIndex tokenIndex = new TokenIndex();
  public String value;
  public int actualIndentation;

  @Override
  public void setValue(String value) {
    this.value = value;
    this.actualIndentation = 0;
  }

  @Override
  public List<Token> format(List<Token> tokens) {
    List<Token> result = new ArrayList<>(tokens);
    int bracketOpenIndex = tokenIndex.getIndex(result, TokenType.BRACE_OPEN);
    int semicolonIndex = tokenIndex.getIndex(result, TokenType.SEMICOLON);
    if (bracketOpenIndex == -1 || semicolonIndex == -1) {
      return tokens;
    }
    for (int i = 0; i < actualIndentation; i++) {
      result.add(bracketOpenIndex + 2, new ValueToken(TokenType.WHITESPACE, " ", i,
          tokens.get(bracketOpenIndex).getLine() + 1));
    }
    while (semicolonIndex != -1) {
      if (tokenIndex.getIndex(result, TokenType.SEMICOLON, semicolonIndex + 2) == -1) {
        break;
      }
      bracketOpenIndex = tokenIndex.getIndex(result, TokenType.BRACE_OPEN, bracketOpenIndex + 1);
      if (bracketOpenIndex == -1 || bracketOpenIndex > semicolonIndex) {
        for (int i = 0; i < actualIndentation; i++) {
          result.add(semicolonIndex + 2, new ValueToken(TokenType.WHITESPACE, " ", i,
              tokens.get(semicolonIndex).getLine() + 1));
        }
      } else {
        this.actualIndentation += Integer.parseInt(value);
        List<Token> ifBlock = getIfBlock(result);
        result.addAll(ifBlock);
        this.actualIndentation -= Integer.parseInt(value);
      }
      semicolonIndex = tokenIndex.getIndex(result, TokenType.SEMICOLON, semicolonIndex + 1);
    }
    this.actualIndentation += Integer.parseInt(value);
    return result;
  }

  private List<Token> getIfBlock(List<Token> tokens) {
    List<Token> result = new ArrayList<>();
    int bracketOpenIndex = tokenIndex.getIndex(tokens, TokenType.BRACE_OPEN);
    if (bracketOpenIndex == -1) {
      return result;
    }

    int nestingLevel = 1; // Start with 1 because we found the first BRACE_OPEN
    result.add(tokens.get(bracketOpenIndex));

    for (int i = bracketOpenIndex + 1; i < tokens.size(); i++) {
      Token token = tokens.get(i);
      result.add(token);

      if (token.getType() == TokenType.BRACE_OPEN) {
        nestingLevel++;
      } else if (token.getType() == TokenType.BRACE_CLOSE) {
        nestingLevel--;
        if (nestingLevel == 0) {
          break; // Found the matching BRACE_CLOSE
        }
      }
    }

    return result;
  }
}
