package formatter;

import formatter.rules.*;
import formatter.rules.assign.SpacesBetweenAssign;
import formatter.rules.colon.SpaceAfterColon;
import formatter.rules.colon.SpaceBeforeColon;
import formatter.rules.bracket.IndentationOnIf;
import formatter.rules.semicolon.LineBreakAfterSemicolon;
import formatter.rules.print.LinebreakBeforePrint;

import java.util.HashMap;
import java.util.Map;

public class RulesMap {
  Map<String, Rule> ruleMap = new HashMap<String, Rule>();

  public RulesMap() {
    ruleMap.put("spaceBeforeColon", new SpaceBeforeColon());
    ruleMap.put("spaceAfterColon", new SpaceAfterColon());
    ruleMap.put("spacesBetweenAssign", new SpacesBetweenAssign());
    ruleMap.put("linebreakBeforePrint", new LinebreakBeforePrint());
    ruleMap.put("lineBreakAfterSemicolon", new LineBreakAfterSemicolon());
    ruleMap.put("indentationsOnIf", new IndentationOnIf(2));
  }

  public Rule getRule(String ruleName) {
    return ruleMap.get(ruleName);
  }
}
