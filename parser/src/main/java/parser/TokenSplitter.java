package parser;

import java.util.ArrayList;
import java.util.List;
import token.Token;
import token.TokenType;

public class TokenSplitter {
  public static List<List<Token>> splitBySemicolons(List<Token> tokens) {
    List<List<Token>> sentences = new ArrayList<>();
    List<Token> currentSentence = new ArrayList<>();

    for (Token token : tokens) {
      if (token.getType() == TokenType.SEMICOLON) {
        currentSentence.add(token);
        sentences.add(new ArrayList<>(currentSentence));
        currentSentence.clear();
      } else {
        currentSentence.add(token);
      }
    }

    if (!currentSentence.isEmpty()) {
      sentences.add(currentSentence);
    }

    return sentences;
  }
}
