package linter.rulesMap;

public class RuleMapFactory {
  public RulesMap createRuleMap(String version) {
    if (version.equals("1.0")) {
      return new RuleMap1_0();
    } else if (version.equals("1.1")) {
      return new RuleMap1_1();
    } else {
      throw new RuntimeException("Version " + version + " not found");
    }
  }
}
