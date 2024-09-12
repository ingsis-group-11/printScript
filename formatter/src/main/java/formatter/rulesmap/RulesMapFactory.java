package formatter.rulesmap;

public class RulesMapFactory {
  public RulesMap createRuleMap(String version) {
    if (version.equals("1.0")) {
      return new RulesMapV1();
    } else if (version.equals("1.1")) {
      return new RulesMapV2();
    } else {
      throw new RuntimeException("Version " + version + " not found");
    }
  }
}
