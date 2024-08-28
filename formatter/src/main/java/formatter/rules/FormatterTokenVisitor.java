package formatter.rules;

import token.Token;
import token.TokenType;
import token.ValueToken;

import java.util.ArrayList;
import java.util.List;

public class FormatterTokenVisitor implements RuleVisitor {

  @Override
  public List<Token> visit(SpaceBeforeColon rule, List<Token> tokens) {
    List<Token> copy = new ArrayList<>(List.copyOf(tokens));
    if (tokens.getLast().getType() == TokenType.WHITESPACE) {
      copy.add(new ValueToken(TokenType.COLON, ":", tokens.getLast().getLine(),
          tokens.getLast().getColumn() + 1));
      return copy;
    }
    copy.add(new ValueToken(TokenType.WHITESPACE, " ", tokens.getLast().getLine(),
        tokens.getLast().getColumn() + 1));
    copy.add(new ValueToken(TokenType.COLON, ":", tokens.getLast().getLine(),
        tokens.getLast().getColumn() + 2));
    return copy;
  }

  @Override
  public List<Token> visit(SpaceAfterColon rule, List<Token> tokens) {
    List<Token> copy = new ArrayList<>(List.copyOf(tokens));
    if (tokens.getLast().getType() == TokenType.COLON) {
      copy.add(new ValueToken(TokenType.WHITESPACE, " ", tokens.getLast().getLine(),
          tokens.getLast().getColumn() + 1));
      return copy;
    }
    return copy;
  }

  @Override
  public List<Token> visit(LineBreakAfterSemicolon rule, List<Token> tokens) {
    List<Token> copy = new ArrayList<>(List.copyOf(tokens));
    return copy;
  }

  @Override
  public List<Token> visit(SpacesBetweenAssign rule, List<Token> tokens) {
    return List.of();
  }

  @Override
  public List<Token> visit(SpacesBetweenOperator spaceBetweenOperator, List<Token> tokens) {
    return List.of();
  }

  @Override
  public List<Token> visit(LinebreakBeforePrint linebreakBeforePrint, List<Token> tokens) {
    return List.of();
  }
}
