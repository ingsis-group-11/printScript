package runner;

import iterator.FileReaderIterator;
import iterator.TokenIterator;
import java.io.IOException;
import java.io.InputStream;
import parser.iterator.ASTIterator;
import providers.iterator.PrintScriptIterator;
import providers.observer.Observer;
import token.Token;

public class ValidationRunner {
  public void validate(InputStream sourceFile, String version, Observer parserObserver)
      throws IOException {
    FileReaderIterator fileIterator = new FileReaderIterator(sourceFile);
    PrintScriptIterator<Token> tokens = new TokenIterator(fileIterator, version);
    ASTIterator nodes = new ASTIterator(tokens, version);
    nodes.addObserver(parserObserver);
    while (nodes.hasNext()) {
      nodes.next();
    }
  }
}
