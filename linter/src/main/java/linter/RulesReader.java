package linter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import linter.rules.Rule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RulesReader {
    public List<Rule> loadRulesFromJson(String jsonFilePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> rulesConfig = mapper.readValue(jsonFilePath, new TypeReference<>() {});

        List<Rule> activeRules = new ArrayList<>();
        RulesMap rulesMap = new RulesMap();
        for (Map<String, Object> ruleConfig : rulesConfig) {
            String type = (String) ruleConfig.get("type");
            boolean active = (boolean) ruleConfig.get("active");
            if(active) {
                Rule rule = rulesMap.getRule(type);
                activeRules.add(rule);
            }
        }
        return activeRules;
    }
}
