package formatter;

import fileReader.FileReader;
import formatter.rules.Rule;
import formatter.tokenFormatter.TokenFormatter;
import token.Token;

import java.io.IOException;
import java.util.List;

public class Formatter {

  public void formatFile(List<Token> tokens, String inputFilePath, String outputPathFile, String configPathRules) throws IOException {
    List<Rule> rules = getRules(configPathRules);
    List<Token> formattedTokens = List.copyOf(tokens);
    TokenMap tokenMap = new TokenMap();

    for (Token token : tokens) {
      if (tokenMap.containsToken(token.getType())) {
        TokenFormatter tokenFormatter = tokenMap.getTokenFormatter(token.getType());
        int tokenIndex = getTokenIndex(formattedTokens, token);
        formattedTokens = tokenFormatter.formatToken(formattedTokens, rules);
      }
    }

    String output = getOutput(formattedTokens);
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
      output.append(token.getValue());
    }
    return output.toString();
  }

  private int getTokenIndex(List<Token> tokens, Token token) {
    for (int i = 0; i < tokens.size(); i++) {
      if (tokens.get(i).equals(token)) {
        return i;
      }
    }
    return -1;
  }
}


// let name : string="John";
// LET_TOKEN, WHITESPACE_TOKEN, IDENTIFIER_TOKEN, COLON_TOKEN, WHITESPACE_TOKEN,
// STRING_TOKEN, EQUALS_TOKEN, STRING_LITERAL_TOKEN, SEMICOLON_TOKEN
// let name : string = "John"; -> Nueva lista de tokens, armo un nuevo archivo
// let, id, ws, colon, ..., assign, ..., string_literal, semicolon