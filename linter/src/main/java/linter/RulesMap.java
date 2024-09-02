package linter;

import linter.rules.CamelCaseRule;
import linter.rules.PrintPreventExpressionRule;
import linter.rules.Rule;

import java.util.HashMap;
import java.util.Map;

public class RulesMap {
  Map<String, Rule> ruleMap = new HashMap<>();

  public RulesMap() {
    ruleMap.put("camelCase", new CamelCaseRule());
    ruleMap.put("printPreventExpression", new PrintPreventExpressionRule());
  }

  public Rule getRule(String ruleName) {
    if (!ruleMap.containsKey(ruleName)) {
      throw new RuntimeException("Rule " + ruleName + " not found");
    }
    return ruleMap.get(ruleName);
  }
}
