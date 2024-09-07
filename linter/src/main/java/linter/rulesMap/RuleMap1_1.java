package linter.rulesMap;

import linter.rules.IdentifierFormatRule;
import linter.rules.PrintPreventExpressionRule;
import linter.rules.ReadInputPreventExpressionRule;
import linter.rules.Rule;

import java.util.HashMap;
import java.util.Map;

public class RuleMap1_1 implements RulesMap {
  Map<String, Rule> ruleMap = new HashMap<>();

  public RuleMap1_1() {
    ruleMap.put("identifier_format", new IdentifierFormatRule());
    ruleMap.put("mandatory-variable-or-literal-in-println", new PrintPreventExpressionRule());
    ruleMap.put("mandatory-variable-or-literal-in-readInput", new ReadInputPreventExpressionRule());
  }

  @Override
  public Rule getRule(String ruleName) {
    if (!ruleMap.containsKey(ruleName)) {
      throw new RuntimeException("Rule " + ruleName + " not found");
    }
    return ruleMap.get(ruleName);
  }
}
