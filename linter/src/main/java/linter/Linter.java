package linter;

import AST.nodes.ASTNode;
import fileReader.FileReader;
import lexer.Lexer;
import linter.result.FailedLinterResult;
import linter.result.LinterResult;
import linter.rules.Rule;
import linter.rules.RuleValidator;
import parser.Parser;
import result.LexingResult;
import result.SuccessfulResult;
import result.UnsuccessfulResult;
import token.Token;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Linter {

  public void lint(List<ASTNode> astNodes, String configFilePath) throws IOException {
    List<Rule> rules = getRules(configFilePath);
    checkRules(rules, astNodes);
  }

  private List<Rule> getRules(String path) throws IOException {
    RulesReader rulesReader = new RulesReader();
    String json = new JSONReader().read(path);
    List<Rule> rules = rulesReader.loadRulesFromJson(json);
    return rules;
  }

  private void checkRules(List<Rule> rules, List<ASTNode> ASTNodes) {
    RuleValidator validator = new RuleValidator();
    List<String> errors = new ArrayList<>();
    for (Rule rule : rules) {
      LinterResult result = rule.accept(validator, ASTNodes);
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
