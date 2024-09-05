package formatter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import formatter.rules.*;
import formatter.rules.bracket.IndentationOnIf;
import formatter.rules.identifier.Identifier;
import formatter.rules.semicolon.LineBreakAfterSemicolon;
import formatter.rules.types.StringQuotes;
import formatter.rules.types.Types;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RulesReader {
  public List<Rule> loadRulesFromJson(String jsonString) throws IOException {
    List<Rule> activeRules = alwaysActiveRules();
    RulesMap rulesMap = new RulesMap();

    ObjectMapper mapper = new ObjectMapper();
    JsonNode rootNode = mapper.readTree(jsonString);
    JsonNode rulesNode = rootNode.get("rules");

    for (JsonNode ruleNode : rulesNode) {
      String type = ruleNode.get("type").asText();
      if (ruleNode.has("active")) {
        boolean active = ruleNode.get("active").asBoolean();
        if (active) {
          Rule rule = rulesMap.getRule(type);
          activeRules.add(rule);
        }
      } else {
        int value = ruleNode.get("value").asInt();
        activeRules.add(new IndentationOnIf(value));
      }
    }
    return activeRules;
  }

  private List<Rule> alwaysActiveRules() {
    List<Rule> activeRules = new ArrayList<>();
    activeRules.add(new LineBreakAfterSemicolon());
    activeRules.add(new Identifier());
    activeRules.add(new Types());
    activeRules.add(new StringQuotes());
    return activeRules;
  }
}
