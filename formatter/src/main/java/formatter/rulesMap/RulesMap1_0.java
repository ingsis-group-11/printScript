package formatter.rulesMap;

import formatter.rules.*;
import formatter.rules.assign.SpacesBetweenAssign;
import formatter.rules.colon.SpaceAfterColon;
import formatter.rules.colon.SpaceBeforeColon;
import formatter.rules.print.LinebreakAfterPrint;

import java.util.HashMap;
import java.util.Map;

public class RulesMap1_0 implements RulesMap {
  Map<String, Rule> ruleMap = new HashMap<String, Rule>();

  public RulesMap1_0() {
    ruleMap.put("enforce-spacing-before-colon-in-declaration", new SpaceBeforeColon());
    ruleMap.put("enforce-spacing-after-colon-in-declaration", new SpaceAfterColon());
    ruleMap.put("enforce-spacing-around-equals", new SpacesBetweenAssign());
    ruleMap.put("line-breaks-after-println", new LinebreakAfterPrint());
  }

  @Override
  public Rule getRule(String ruleName) {
    return ruleMap.get(ruleName);
  }
}
