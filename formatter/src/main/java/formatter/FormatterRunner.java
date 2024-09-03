package formatter;

import fileReader.FileReaderIterator;
import fileWriter.OutputProvider;
import iterator.TokenIterator;
import token.Token;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class FormatterRunner {

  public void format(String inputFilePath, String configPathRules, OutputProvider outputProvider)
      throws IOException {
    FileReaderIterator fileIterator = new FileReaderIterator(new File(inputFilePath));
    Iterator<Token> tokens = new TokenIterator(fileIterator);
    outputProvider.write(new FormatterIterator(tokens, configPathRules));
  }
}
