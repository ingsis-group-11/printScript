package runner;

import AST.nodes.ASTNode;
import formatter.FormatterIterator;
import formatter.rules.Rule;
import formatter.rulesMap.RulesMap;
import formatter.rulesMap.RulesMapFactory;
import formatter.rulesMap.RulesReader;
import iterator.FileReaderIterator;
import iterator.TokenIterator;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import parser.iterator.AstIterator;
import providers.iterator.PrintScriptIterator;
import providers.outputProvider.OutputProvider;
import token.Token;

public class FormatterRunner {

  public void format(
      InputStream inputStream,
      InputStream configRules,
      OutputProvider outputProvider,
      String version)
      throws IOException {
    FileReaderIterator fileIterator = new FileReaderIterator(inputStream);
    PrintScriptIterator<Token> tokens = new TokenIterator(fileIterator, version);
    Iterator<ASTNode> nodes = new AstIterator(tokens, version);
    outputProvider.write(new FormatterIterator(nodes, getRules(configRules, version)));
  }

  private List<Rule> getRules(InputStream configFile, String version) throws IOException {
    RulesMap rulesMap = new RulesMapFactory().createRuleMap(version);
    RulesReader rulesReader = new RulesReader();
    List<Rule> rules = rulesReader.loadRulesFromJson(configFile, rulesMap);
    return rules;
  }
}
