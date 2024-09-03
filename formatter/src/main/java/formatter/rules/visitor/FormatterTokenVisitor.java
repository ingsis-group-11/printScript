package formatter.rules.visitor;

import formatter.rules.assign.SpacesBetweenAssign;
import formatter.rules.colon.SpaceAfterColon;
import formatter.rules.colon.SpaceBeforeColon;
import formatter.rules.identifier.Identifier;
import formatter.rules.semicolon.LineBreakAfterSemicolon;
import formatter.rules.print.LinebreakBeforePrint;
import formatter.rules.types.StringQuotes;
import formatter.rules.types.Types;
import token.Token;
import token.TokenType;
import token.ValueToken;

import java.util.ArrayList;
import java.util.List;

public class FormatterTokenVisitor implements RuleVisitor {

  @Override
  public List<Token> visit(SpaceBeforeColon rule, List<Token> tokens) {
    List<Token> result = new ArrayList<>(tokens);
    int colonIndex = getIndex(tokens, TokenType.COLON);
    result.add(colonIndex, new ValueToken(TokenType.WHITESPACE, " ", tokens.get(colonIndex).getColumn(),
        tokens.get(colonIndex).getLine()));
    return result;
  }

  @Override
  public List<Token> visit(SpaceAfterColon rule, List<Token> tokens) {
    List<Token> result = new ArrayList<>(tokens);
    int colonIndex = getIndex(tokens, TokenType.COLON);
    result.add(new ValueToken(TokenType.WHITESPACE, " ", tokens.get(colonIndex).getColumn() + 1,
        tokens.get(colonIndex).getLine()));
    return result;
  }

  @Override
  public List<Token> visit(LineBreakAfterSemicolon rule, List<Token> tokens) {
    List<Token> result = new ArrayList<>(tokens);
    int semicolonIndex = getIndex(tokens, TokenType.SEMICOLON);
    result.add(new ValueToken(TokenType.LINE_BREAK, "\n", tokens.get(semicolonIndex).getColumn() + 1,
        tokens.get(semicolonIndex).getLine()));
    return result;
  }

  @Override
  public List<Token> visit(SpacesBetweenAssign rule, List<Token> tokens) {
    List<Token> result = new ArrayList<>(tokens);
    int assignIndex = getIndex(tokens, TokenType.ASSIGN);
    result.add(assignIndex, new ValueToken(TokenType.WHITESPACE, " ", tokens.get(assignIndex).getColumn(),
        tokens.get(assignIndex).getLine()));
    result.add(assignIndex + 2, new ValueToken(TokenType.WHITESPACE, " ",
        tokens.get(assignIndex).getColumn() + 2, tokens.get(assignIndex).getLine()));
    return result;
  }

  @Override
  public List<Token> visit(LinebreakBeforePrint linebreakBeforePrint, List<Token> tokens) {
    List<Token> result = new ArrayList<>(tokens);
    int printIndex = getIndex(tokens, TokenType.PRINT_KEYWORD);
    result.add(printIndex, new ValueToken(TokenType.LINE_BREAK, "\n", tokens.get(printIndex).getColumn(),
        tokens.get(printIndex).getLine()));
    return result;
  }

  @Override
  public List<Token> visit(Identifier identifier, List<Token> tokens) {
    return tokens;
  }

  @Override
  public List<Token> visit(Types types, List<Token> tokens) {
    return tokens;
  }

  @Override
  public List<Token> visit(StringQuotes stringQuotes, List<Token> tokens) {
    Token token = tokens.getLast();
    List<Token> result = new ArrayList<>();
    result.add(new ValueToken(TokenType.STRING, '"' + token.getValue() + '"', token.getColumn() + 1,
        token.getLine()));
    return result;
  }

  private int getIndex(List<Token> tokens, TokenType type) {
    for (int i = 0; i < tokens.size(); i++) {
      if (tokens.get(i).getType() == type) {
        return i;
      }
    }
    return -1;
  }
}
