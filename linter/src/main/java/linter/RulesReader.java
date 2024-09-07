package linter;

import fileReader.InputStreamToString;
import linter.rules.Rule;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import linter.rulesMap.RulesMap;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class RulesReader {
    public List<Rule> loadRulesFromJson(InputStream configFile, RulesMap rulesMap) throws IOException {
        String jsonString = new InputStreamToString().read(configFile);
        List<Rule> activeRules = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(jsonString);

        rootNode.fields().forEachRemaining(entry -> {
            String key = entry.getKey();
            JsonNode value = entry.getValue();
            Rule rule = rulesMap.getRule(key);
            rule.setValue(value.asText());
            activeRules.add(rule);
        });
        return activeRules;
    }
}
