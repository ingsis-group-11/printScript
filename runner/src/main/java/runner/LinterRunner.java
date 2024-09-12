package runner;

import AST.nodes.ASTNode;
import iterator.FileReaderIterator;
import iterator.TokenIterator;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import linter.Linter;
import linter.RulesReader;
import linter.rules.Rule;
import linter.rulesMap.RuleMapFactory;
import linter.rulesMap.RulesMap;
import parser.iterator.AstIterator;
import providers.iterator.PrintScriptIterator;
import providers.observer.Observer;
import token.Token;

public class LinterRunner {

  public void linterRun(InputStream input, InputStream configRules, String version)
      throws IOException {
    FileReaderIterator fileIterator = new FileReaderIterator(input);
    PrintScriptIterator<Token> tokens = new TokenIterator(fileIterator, version);
    Iterator<ASTNode> nodes = new AstIterator(tokens, version);
    run(configRules, version, nodes);
  }

  public void linterRun(
      InputStream input, InputStream configRules, String version, List<Observer> observers)
      throws IOException {
    FileReaderIterator fileIterator = new FileReaderIterator(input);
    PrintScriptIterator<Token> tokens = new TokenIterator(fileIterator, version);
    Iterator<ASTNode> nodes = new AstIterator(tokens, version, observers);
    run(configRules, version, nodes);
  }

  private void run(InputStream configRules, String version, Iterator<ASTNode> nodes)
      throws IOException {
    Linter linter = new Linter();
    RulesMap rulesMap = new RuleMapFactory().createRuleMap(version);
    List<Rule> rules = getRules(configRules, rulesMap);
    while (nodes.hasNext()) {
      ASTNode node = nodes.next();
      linter.lint(node, rules);
    }
  }

  private List<Rule> getRules(InputStream configFile, RulesMap rulesMap) throws IOException {
    RulesReader rulesReader = new RulesReader();
    List<Rule> rules = rulesReader.loadRulesFromJson(configFile, rulesMap);
    return rules;
  }
}
