package formatter;

import fileReader.FileReaderIterator;
import fileWriter.OutputProvider;
import iterator.TokenIterator;
import token.Token;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class FormatterRunner {

  public void format(InputStream inputStream, InputStream configRules, OutputProvider outputProvider, String version)
      throws IOException {
    FileReaderIterator fileIterator = new FileReaderIterator(inputStream);
    Iterator<Token> tokens = new TokenIterator(fileIterator, version);
    outputProvider.write(new FormatterIterator(tokens, configRules));
  }
}
