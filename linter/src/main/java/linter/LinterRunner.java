package linter;

import AST.nodes.ASTNode;
import fileReader.FileReaderIterator;
import iterator.TokenIterator;
import linter.rules.Rule;
import linter.rulesMap.RuleMapFactory;
import linter.rulesMap.RulesMap;
import parser.Observer;
import parser.iterator.ASTIterator;
import token.Token;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

public class LinterRunner {

  public void linterRun(InputStream input, InputStream configRules, String version) throws IOException {
    FileReaderIterator fileIterator = new FileReaderIterator(input);
    Iterator<Token> tokens = new TokenIterator(fileIterator, version);
    Iterator<ASTNode> nodes = new ASTIterator(tokens, version);
    run(configRules, version, nodes);
  }

  public void linterRun(InputStream input, InputStream configRules, String version, List<Observer> observers) throws IOException {
    FileReaderIterator fileIterator = new FileReaderIterator(input);
    Iterator<Token> tokens = new TokenIterator(fileIterator, version);
    Iterator<ASTNode> nodes = new ASTIterator(tokens, version, observers);
    run(configRules, version, nodes);
  }

  private void run(InputStream configRules, String version, Iterator<ASTNode> nodes) throws IOException {
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
