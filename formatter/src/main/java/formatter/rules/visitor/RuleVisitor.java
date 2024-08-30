package formatter.rules.visitor;

import formatter.rules.*;
import token.Token;

import java.util.Iterator;
import java.util.List;

public interface RuleVisitor {
  List<Token> visit(SpaceBeforeColon rule, Iterator<Token> tokens);
  List<Token> visit(SpaceAfterColon rule, Iterator<Token> tokens);
  List<Token> visit(LineBreakAfterSemicolon rule, Iterator<Token> tokens);
  List<Token> visit(SpacesBetweenAssign rule, Iterator<Token> tokens);
  List<Token> visit(LinebreakBeforePrint linebreakBeforePrint, Iterator<Token> tokens);
}
