package linter;

import AST.nodes.ASTNode;
import fileReader.FileReader;
import linter.result.FailedLinterResult;
import linter.result.LinterResult;
import linter.rules.Rule;
import linter.rules.RuleValidator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Linter {

  public void lint(ASTNode node, String configFilePath) throws IOException {
    List<Rule> rules = getRules(configFilePath);
    checkRules(rules, node);
  }

  private List<Rule> getRules(String path) throws IOException {
    RulesReader rulesReader = new RulesReader();
    FileReader fileReader = new FileReader();
    String jsonString = fileReader.readFile(path);
    List<Rule> rules = rulesReader.loadRulesFromJson(jsonString);
    return rules;
  }

  private void checkRules(List<Rule> rules, ASTNode node) {
    RuleValidator validator = new RuleValidator();
    List<String> errors = new ArrayList<>();
    for (Rule rule : rules) {
      LinterResult result = rule.accept(validator, node);
      if(result.hasErrors()) {
        errors.addAll(((FailedLinterResult) result).getErrors());
      }
    }
    resolveErrors(errors);
  }

  private void resolveErrors(List<String> errors) {
    if(!errors.isEmpty()){
      String messages = "";
      for (String message : errors) {
        messages+=message+"\n";
      }
      throw new RuntimeException(messages);
    }
  }
}