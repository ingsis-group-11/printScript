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
      copy.add(new ValueToken(TokenType.COLON, ":", tokens.getLast().getColumn() + 1,
          tokens.getLast().getColumn() + 1));
      return copy;
    }
    copy.add(new ValueToken(TokenType.WHITESPACE, " ", tokens.getLast().getColumn() + 1,
        tokens.getLast().getLine()));
    copy.add(new ValueToken(TokenType.COLON, ":", tokens.getLast().getColumn() + 2,
        tokens.getLast().getLine()));
    return copy;
  }

  @Override
  public List<Token> visit(SpaceAfterColon rule, List<Token> tokens) {
    List<Token> copy = new ArrayList<>(List.copyOf(tokens));
    if (tokens.getLast().getType() == TokenType.COLON) {
      copy.add(new ValueToken(TokenType.WHITESPACE, " ", tokens.getLast().getColumn() + 1,
          tokens.getLast().getLine()));
      return copy;
    }
    copy.add(new ValueToken(TokenType.COLON, ":", tokens.getLast().getColumn() + 1,
        tokens.getLast().getLine()));
    copy.add(new ValueToken(TokenType.WHITESPACE, " ", tokens.getLast().getColumn() + 2,
        tokens.getLast().getLine()));
    return copy;
  }

  @Override
  public List<Token> visit(LineBreakAfterSemicolon rule, List<Token> tokens) {
    List<Token> copy = new ArrayList<>(List.copyOf(tokens));
    if (tokens.getLast().getType() == TokenType.WHITESPACE) {
      copy.removeLast();
      copy.add(new ValueToken(TokenType.SEMICOLON, ";", tokens.getLast().getColumn() + 2,
          tokens.getLast().getLine()));
      copy.add(new ValueToken(TokenType.LINE_BREAK, "\n", tokens.getLast().getColumn() + 3,
          tokens.getLast().getLine()));
      return copy;
    }
    return copy;
  }

  @Override
  public List<Token> visit(SpacesBetweenAssign rule, List<Token> tokens) {
    List<Token> copy = new ArrayList<>(List.copyOf(tokens));
    if (tokens.getLast().getType() == TokenType.WHITESPACE) {
      copy.add(new ValueToken(TokenType.ASSIGN, "=", tokens.getLast().getColumn() + 1,
          tokens.getLast().getLine()));
      copy.add(new ValueToken(TokenType.WHITESPACE, " ", tokens.getLast().getColumn() + 2,
          tokens.getLast().getLine()));
      return copy;
    }
    copy.add(new ValueToken(TokenType.WHITESPACE, " ", tokens.getLast().getColumn() + 1,
        tokens.getLast().getLine()));
    copy.add(new ValueToken(TokenType.ASSIGN, "=", tokens.getLast().getColumn() + 2,
        tokens.getLast().getLine()));
    copy.add(new ValueToken(TokenType.WHITESPACE, " ", tokens.getLast().getColumn() + 3,
        tokens.getLast().getLine()));
    return copy;
  }

  @Override
  public List<Token> visit(LinebreakBeforePrint linebreakBeforePrint, List<Token> tokens) {
    List<Token> copy = new ArrayList<>(List.copyOf(tokens));
    copy.add(new ValueToken(TokenType.LINE_BREAK, "\n", tokens.getLast().getColumn() + 1,
        tokens.getLast().getLine()));
    copy.add(new ValueToken(TokenType.PRINT_KEYWORD, "println", tokens.getLast().getColumn() + 2,
        tokens.getLast().getLine()));
    return copy;
  }
}
