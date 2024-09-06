package linter.rulesMap;


import linter.rules.Rule;


public interface RulesMap {
  public Rule getRule(String ruleName);
}
