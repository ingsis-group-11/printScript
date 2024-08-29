package formatter;

import fileReader.FileReader;
import lexer.Lexer;
import result.LexingResult;
import result.SuccessfulResult;
import result.UnsuccessfulResult;
import token.Token;

import java.io.IOException;
import java.util.List;

public class FormatterRunner {

  public void format(String inputFilePath, String outputFilePath, String configPathRules) throws IOException {
    String fileString = readFile(inputFilePath);
    List<Token> tokens = lexFile(fileString);
    Formatter formatter = new Formatter();
    formatter.formatFile(tokens, outputFilePath, configPathRules);
  }

  private List<Token> lexFile(String filePath) {
    Lexer lexer = new Lexer();
    LexingResult lexerResult = lexer.lex(filePath);

    resolveLexerErrors(lexerResult);

    List<Token> tokens = ((SuccessfulResult) lexerResult).tokens();
    return tokens;
  }

  private void resolveLexerErrors(LexingResult lexerResult) {
    if (lexerResult instanceof UnsuccessfulResult) {
      throw new RuntimeException(((UnsuccessfulResult) lexerResult).message());
    }
  }

  private String readFile(String filePath) throws IOException {
    return new FileReader().readFile(filePath);
  }
}
