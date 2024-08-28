package formatter;

import formatter.rules.*;

import java.util.HashMap;
import java.util.Map;

public class RulesMap {
  Map<String, Rule> ruleMap = new HashMap<String, Rule>();

  public RulesMap() {
    ruleMap.put("spaceBeforeColon", new SpaceBeforeColon());
    ruleMap.put("spaceAfterColon", new SpaceAfterColon());
    ruleMap.put("spacesBetweenAssign", new SpacesBetweenAssign());
    ruleMap.put("spacesBetweenOperator", new SpacesBetweenOperator());
    ruleMap.put("linebreakBeforePrint", new LinebreakBeforePrint());
    ruleMap.put("lineBreakAfterSemicolon", new LineBreakAfterSemicolon());
  }

  public Rule getRule(String ruleName) {
    return ruleMap.get(ruleName);
  }
}
