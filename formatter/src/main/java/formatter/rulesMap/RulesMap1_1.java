package formatter.rulesMap;

import formatter.rules.*;
import formatter.rules.assignation.SpacesBetweenAssign;
import formatter.rules.conditional.BracketOnSameLineAsIf;
import formatter.rules.assignation.SpaceAfterColon;
import formatter.rules.assignation.SpaceBeforeColon;
import formatter.rules.conditional.IndentationInsideIf;
import formatter.rules.print.LinebreakAfterPrint;

import java.util.HashMap;
import java.util.Map;

public class RulesMap1_1 implements RulesMap {
  Map<String, Rule> ruleMap = new HashMap<String, Rule>();

  public RulesMap1_1() {
    ruleMap.put("enforce-spacing-before-colon-in-declaration", new SpaceBeforeColon());
    ruleMap.put("enforce-spacing-after-colon-in-declaration", new SpaceAfterColon());
    ruleMap.put("enforce-spacing-around-equals", new SpacesBetweenAssign());
    ruleMap.put("line-breaks-after-println", new LinebreakAfterPrint());
    ruleMap.put("if-brace-same-line", new BracketOnSameLineAsIf());
    ruleMap.put("indent-inside-if", new IndentationInsideIf());
  }

  @Override
  public Rule getRule(String ruleName) {
    return ruleMap.get(ruleName);
  }
}
