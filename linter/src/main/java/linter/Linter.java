package linter;

import AST.nodes.ASTNode;
import linter.result.FailedLinterResult;
import linter.result.LinterResult;
import linter.rules.Rule;

import java.util.ArrayList;
import java.util.List;

public class Linter {
  public void lint(ASTNode node, List<Rule> rules) {
    checkRules(rules, node);
  }

  private void checkRules(List<Rule> rules, ASTNode node) {
    List<String> errors = new ArrayList<>();
    for (Rule rule : rules) {
      LinterResult result = rule.lint(node);
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