package formatter.rulesMap;

import formatter.rules.*;
import formatter.rules.assign.SpacesBetweenAssign;
import formatter.rules.bracket.IndentationOnIf;
import formatter.rules.colon.SpaceAfterColon;
import formatter.rules.colon.SpaceBeforeColon;
import formatter.rules.print.LinebreakAfterPrint;
import formatter.rules.semicolon.LineBreakAfterSemicolon;

import java.util.HashMap;
import java.util.Map;

public class RulesMap1_1 implements RulesMap {
  Map<String, Rule> ruleMap = new HashMap<String, Rule>();

  public RulesMap1_1() {
    ruleMap.put("spaceBeforeColon", new SpaceBeforeColon());
    ruleMap.put("spaceAfterColon", new SpaceAfterColon());
    ruleMap.put("spacesBetweenAssign", new SpacesBetweenAssign());
    ruleMap.put("linebreakAfterPrint", new LinebreakAfterPrint());
    ruleMap.put("lineBreakAfterSemicolon", new LineBreakAfterSemicolon());
    ruleMap.put("indentationOnIf", new IndentationOnIf());
  }

  @Override
  public Rule getRule(String ruleName) {
    return ruleMap.get(ruleName);
  }
}
