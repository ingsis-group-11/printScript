package formatter.rules;

import token.Token;

import java.util.List;

public interface RuleVisitor {
  List<Token> visit(SpaceBeforeColon rule, List<Token> tokens);
  List<Token> visit(SpaceAfterColon rule, List<Token> tokens);
  List<Token> visit(LineBreakAfterSemicolon rule, List<Token> tokens);
  List<Token> visit(SpacesBetweenAssign rule, List<Token> tokens);
  List<Token> visit(LinebreakBeforePrint linebreakBeforePrint, List<Token> tokens);
}
