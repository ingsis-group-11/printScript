package formatter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import formatter.rules.Rule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RulesReader {
  public List<Rule> loadRulesFromJson(String jsonString) throws IOException {
    List<Rule> activeRules = new ArrayList<>();
    RulesMap rulesMap = new RulesMap();

    ObjectMapper mapper = new ObjectMapper();
    JsonNode rootNode = mapper.readTree(jsonString);
    JsonNode rulesNode = rootNode.get("rules");

    for (JsonNode ruleNode : rulesNode) {
      String type = ruleNode.get("type").asText();
      boolean active = ruleNode.get("active").asBoolean();
      if (active) {
        Rule rule = rulesMap.getRule(type);
        activeRules.add(rule);
      }
    }
    return activeRules;
  }
}
