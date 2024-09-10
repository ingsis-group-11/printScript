package formatter.rulesMap;

public class RulesMapFactory {
  public RulesMap createRuleMap(String version) {
    if (version.equals("1.0")) {
      return new RulesMap1_0();
    } else if (version.equals("1.1")) {
      return new RulesMap1_1();
    } else {
      throw new RuntimeException("Version " + version + " not found");
    }
  }
}
