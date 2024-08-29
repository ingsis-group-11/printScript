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
import java.util.List;
import java.util.stream.Collectors;

public class Formatter {

  public void formatFile(List<Token> tokens, String outputPathFile, String configPathRules) throws IOException {
    List<Rule> rules = getRules(configPathRules);
    List<Token> strippedTokens = removeWhitespacesAndLineBreaks(tokens);
    List<Token> formattedTokens = new ArrayList<>();

    TokenMap tokenMap = new TokenMap();

    for (Token token : strippedTokens) {
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
    System.out.println(output);
  }

  private List<Rule> getRules(String path) throws IOException {
    RulesReader rulesReader = new RulesReader();
    FileReader fileReader = new FileReader();
    String jsonString = fileReader.readFile(path);
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

  private List<Token> removeWhitespacesAndLineBreaks(List<Token> tokens) {
    return tokens.stream()
        .filter(token -> token.getType() != TokenType.WHITESPACE && token.getType() != TokenType.LINE_BREAK)
        .collect(Collectors.toList());
  }
}