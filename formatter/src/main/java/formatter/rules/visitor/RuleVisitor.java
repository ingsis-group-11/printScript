package formatter.rules.visitor;

import formatter.rules.assign.SpacesBetweenAssign;
import formatter.rules.colon.SpaceAfterColon;
import formatter.rules.colon.SpaceBeforeColon;
import formatter.rules.conditional.IfRule;
import formatter.rules.identifier.Identifier;
import formatter.rules.semicolon.LineBreakAfterSemicolon;
import formatter.rules.print.LinebreakBeforePrint;
import formatter.rules.types.StringQuotes;
import formatter.rules.types.Types;
import token.Token;

import java.util.List;

public interface RuleVisitor {
  List<Token> visit(SpaceBeforeColon rule, List<Token> tokens);
  List<Token> visit(SpaceAfterColon rule, List<Token> tokens);
  List<Token> visit(LineBreakAfterSemicolon rule, List<Token> tokens);
  List<Token> visit(SpacesBetweenAssign rule, List<Token> tokens);
  List<Token> visit(LinebreakBeforePrint linebreakBeforePrint, List<Token> tokens);
  List<Token> visit(Identifier identifier, List<Token> tokens);
  List<Token> visit(Types types, List<Token> tokens);
  List<Token> visit(StringQuotes stringQuotes, List<Token> tokens);
  List<Token> visit(IfRule ifRule, List<Token> tokens, int indentationLevel);
}
