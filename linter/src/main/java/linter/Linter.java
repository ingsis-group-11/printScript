package linter;

import AST.nodes.ASTNode;
import fileReader.InputStreamToString;
import linter.result.FailedLinterResult;
import linter.result.LinterResult;
import linter.rules.Rule;
import linter.rules.validator.RuleValidator;
import linter.rulesMap.RuleMapFactory;
import linter.rulesMap.RulesMap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Linter {
  public void lint(ASTNode node, List<Rule> rules) throws IOException {
    checkRules(rules, node);
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