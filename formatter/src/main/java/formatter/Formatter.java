package formatter;

import fileReader.FileReader;
import fileWriter.FileWriter;
import formatter.rules.Rule;
import formatter.tokenFormatter.TokenFormatter;
import token.Token;
import token.TokenType;
import token.ValueToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Formatter {

  public void formatFile(Iterator<Token> tokens, String outputPathFile, String configPathRules) throws IOException {
    List<Rule> rules = getRules(configPathRules);
    Iterator<Token> strippedTokens = removeWhitespacesAndLineBreaks(tokens);
    List<Token> formattedTokens = new ArrayList<>();

    TokenMap tokenMap = new TokenMap();

    while(strippedTokens.hasNext()) {
      Token token = strippedTokens.next();
      if (tokenMap.containsToken(token.getType())) {
        TokenFormatter tokenFormatter = tokenMap.getTokenFormatter(token.getType());
        formattedTokens = tokenFormatter.formatToken(formattedTokens, rules);
      } else {
        formattedTokens.add(token);
        if (token.getType() != TokenType.IDENTIFIER && token.getType() != TokenType.STRING_TYPE &&
                token.getType() != TokenType.NUMBER_TYPE) {
          formattedTokens.addLast(new ValueToken(TokenType.WHITESPACE, " ", token.getLine(),
                  token.getColumn() + 1));
        }
      }
    }

    String output = getOutput(formattedTokens);
    new FileWriter().writeFile(outputPathFile, output);
  }

  private List<Rule> getRules(String path) throws IOException {
    RulesReader rulesReader = new RulesReader();
    FileReader fileReader = new FileReader();
    String jsonString = fileReader.read(path);
    List<Rule> rules = rulesReader.loadRulesFromJson(jsonString);
    return rules;
  }

  private String getOutput(List<Token> tokens) {
    StringBuilder output = new StringBuilder();
    for (Token token : tokens) {
      if (token.getType() == TokenType.STRING) {
        output.append("\"").append(token.getValue()).append("\"");
      } else {
        output.append(token.getValue());
      }
    }
    return output.toString();
  }

  private Iterator<Token> removeWhitespacesAndLineBreaks(Iterator<Token> tokens) {
    List<Token> filteredTokens = new ArrayList<>();

    while (tokens.hasNext()) {
      Token token = tokens.next();
      if (token.getType() != TokenType.WHITESPACE && token.getType() != TokenType.LINE_BREAK) {
        filteredTokens.add(token);
      }
    }

    return filteredTokens.iterator();
  }
}