package linter.rulesmap;

import linter.rules.Rule;

public interface RulesMap {
  Rule getRule(String ruleName);
}
