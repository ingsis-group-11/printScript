package linter.rulesMap;

import linter.rules.CamelCaseRule;
import linter.rules.PrintPreventExpressionRule;
import linter.rules.Rule;

import java.util.HashMap;
import java.util.Map;

public class RuleMap1_0 implements RulesMap {
  Map<String, Rule> ruleMap = new HashMap<>();

  public RuleMap1_0() {
    ruleMap.put("camelCase", new CamelCaseRule());
    ruleMap.put("printPreventExpression", new PrintPreventExpressionRule());
  }

  @Override
  public Rule getRule(String ruleName) {
    if (!ruleMap.containsKey(ruleName)) {
      throw new RuntimeException("Rule " + ruleName + " not found");
    }
    return ruleMap.get(ruleName);
  }
}
