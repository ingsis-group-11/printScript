package runner;

import fileReader.FileReaderIterator;
import iterator.TokenIterator;
import providers.observer.Observer;
import parser.iterator.ASTIterator;
import providers.iterator.PrintScriptIterator;
import token.Token;

import java.io.IOException;
import java.io.InputStream;

public class ValidationRunner {
  public void validate(InputStream sourceFile, String version, Observer parserObserver) throws IOException {
    FileReaderIterator fileIterator = new FileReaderIterator(sourceFile);
    PrintScriptIterator<Token> tokens = new TokenIterator(fileIterator, version);
    ASTIterator nodes = new ASTIterator(tokens, version);
    nodes.addObserver(parserObserver);
    while (nodes.hasNext()){
      nodes.next();
    }
  }
}
