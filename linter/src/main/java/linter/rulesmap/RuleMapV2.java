package linter.rulesmap;

import java.util.HashMap;
import java.util.Map;
import linter.rules.IdentifierFormatRule;
import linter.rules.PrintPreventExpressionRule;
import linter.rules.ReadInputPreventExpressionRule;
import linter.rules.Rule;

public class RuleMapV2 implements RulesMap {
  Map<String, Rule> ruleMap = new HashMap<>();

  public RuleMapV2() {
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
