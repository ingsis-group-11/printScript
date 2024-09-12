package runner;

import iterator.FileReaderIterator;
import iterator.TokenIterator;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import linter.Linter;
import linter.RulesReader;
import linter.rules.Rule;
import linter.rulesmap.RuleMapFactory;
import linter.rulesmap.RulesMap;
import parser.iterator.AstIterator;
import providers.observer.Observer;

public class LinterRunner {

  private List<Observer> observers = new ArrayList<>();

  public void setObservers(List<Observer> observers) {
    this.observers = observers;
  }

  public void linterRun(InputStream input, InputStream configRules, String version)
      throws IOException {
    FileReaderIterator fileIterator = new FileReaderIterator(input);
    TokenIterator tokens = new TokenIterator(fileIterator, version);
    AstIterator nodes = getAstIterator(tokens, version);
    run(configRules, version, nodes);
  }

  private void run(InputStream configRules, String version, AstIterator nodes) throws IOException {
    Linter linter = new Linter();
    RulesMap rulesMap = new RuleMapFactory().createRuleMap(version);
    List<Rule> rules = getRules(configRules, rulesMap);
    linter.lintRun(nodes, rules);
  }

  private List<Rule> getRules(InputStream configFile, RulesMap rulesMap) throws IOException {
    RulesReader rulesReader = new RulesReader();
    List<Rule> rules = rulesReader.loadRulesFromJson(configFile, rulesMap);
    return rules;
  }

  private AstIterator getAstIterator(TokenIterator tokenIterator, String version)
      throws IOException {
    return observers.isEmpty()
        ? new AstIterator(tokenIterator, version)
        : new AstIterator(tokenIterator, version, observers);
  }
}
