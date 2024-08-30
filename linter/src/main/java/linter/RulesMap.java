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
    return ruleMap.get(ruleName);
  }
}
