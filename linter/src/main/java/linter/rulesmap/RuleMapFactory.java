package linter.rulesmap;

public class RuleMapFactory {
  public RulesMap createRuleMap(String version) {
    return switch (version) {
      case "1.0" -> new RuleMapV1();
      case "1.1" -> new RuleMapV2();
      default -> throw new RuntimeException("Version " + version + " not found");
    };
  }
}
