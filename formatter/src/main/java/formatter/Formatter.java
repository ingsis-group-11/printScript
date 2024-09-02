package formatter;

import fileReader.FileReader;
import formatter.rules.Rule;
import formatter.tokenFormatter.TokenFormatter;
import token.Token;
import token.TokenType;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class Formatter {
  TokenOutput tokenOutput = new TokenOutput();

  public String formatFile(Iterator<Token> tokens, String configPathRules) throws IOException {
    List<Rule> rules = getRules(configPathRules);
    TokenMap tokenMap = new TokenMap();

    Token token = tokens.next();
    TokenType tokenType = token.getType();
    while ((tokenType == TokenType.WHITESPACE || tokenType == TokenType.LINE_BREAK) && tokens.hasNext()) {
      token = tokens.next();
      tokenType = token.getType();
    }
    if (tokenMap.containsToken(token.getType())) {
      TokenFormatter tokenFormatter = tokenMap.getTokenFormatter(token.getType());
      return tokenOutput.toString(tokenFormatter.formatToken(token, rules));
    }
    else {
      return token.getValue() + " ";
    }
  }

  private List<Rule> getRules(String path) throws IOException {
    RulesReader rulesReader = new RulesReader();
    FileReader fileReader = new FileReader();
    String jsonString = fileReader.read(path);
    List<Rule> rules = rulesReader.loadRulesFromJson(jsonString);
    return rules;
  }
}