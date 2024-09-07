package linter.rulesMap;


import linter.rules.Rule;


public interface RulesMap {
  Rule getRule(String ruleName);
}
