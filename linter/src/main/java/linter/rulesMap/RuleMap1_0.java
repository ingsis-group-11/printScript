package linter.rulesMap;

import java.util.HashMap;
import java.util.Map;
import linter.rules.IdentifierFormatRule;
import linter.rules.PrintPreventExpressionRule;
import linter.rules.Rule;

public class RuleMap1_0 implements RulesMap {
  Map<String, Rule> ruleMap = new HashMap<>();

  public RuleMap1_0() {
    ruleMap.put("identifier_format", new IdentifierFormatRule());
    ruleMap.put("mandatory-variable-or-literal-in-println", new PrintPreventExpressionRule());
  }

  @Override
  public Rule getRule(String ruleName) {
    if (!ruleMap.containsKey(ruleName)) {
      throw new RuntimeException("Rule " + ruleName + " not found");
    }
    return ruleMap.get(ruleName);
  }
}
