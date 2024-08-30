package formatter;

import fileReader.FileReaderIterator;
import iterator.TokenIterator;
import lexer.Lexer;
import token.Token;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class FormatterRunner {

  public void format(String inputFilePath, String outputFilePath, String configPathRules) throws IOException {
    FileReaderIterator fileIterator = new FileReaderIterator(new File(inputFilePath));
    Iterator<Token> tokens = new TokenIterator(fileIterator, new Lexer(fileIterator));
    Formatter formatter = new Formatter();
    formatter.formatFile(tokens, outputFilePath, configPathRules);
  }
}
