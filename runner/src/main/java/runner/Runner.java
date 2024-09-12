package runner;

import interpreter.Interpreter;
import iterator.FileReaderIterator;
import iterator.TokenIterator;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import parser.iterator.AstIterator;
import providers.inputprovider.InputProvider;
import providers.observer.Observer;
import providers.printprovider.PrintProvider;

public class Runner {
  private List<Observer> observers = new ArrayList<>();

  public void setObservers(List<Observer> observers) {
    this.observers = observers;
  }

  public void run(InputStream inputStream, String version) throws IOException {
    FileReaderIterator fileIterator = new FileReaderIterator(inputStream);
    TokenIterator tokens = new TokenIterator(fileIterator, version);
    AstIterator astNodes = getAstIterator(tokens, version);
    new Interpreter().interpret(astNodes);
  }

  // When you use printProvider
  public void run(InputStream inputStream, String version, PrintProvider printProvider)
      throws IOException {
    FileReaderIterator fileIterator = new FileReaderIterator(inputStream);
    TokenIterator tokens = new TokenIterator(fileIterator, version);
    AstIterator astNodes = getAstIterator(tokens, version);
    new Interpreter(printProvider).interpret(astNodes);
  }

  // When you use inputProvider
  public void run(InputStream inputStream, String version, InputProvider inputProvider)
      throws IOException {
    FileReaderIterator fileIterator = new FileReaderIterator(inputStream);
    TokenIterator tokens = new TokenIterator(fileIterator, version);
    AstIterator astNodes = getAstIterator(tokens, version);
    new Interpreter(inputProvider).interpret(astNodes);
  }

  // When you use printProvider and inputProvider
  public void run(
      InputStream inputStream,
      String version,
      PrintProvider printProvider,
      InputProvider inputProvider)
      throws IOException {
    FileReaderIterator fileIterator = new FileReaderIterator(inputStream);
    TokenIterator tokens = new TokenIterator(fileIterator, version);
    AstIterator astNodes = getAstIterator(tokens, version);
    new Interpreter(inputProvider, printProvider).interpret(astNodes);
  }

  private AstIterator getAstIterator(TokenIterator tokenIterator, String version)
      throws IOException {
    return observers.isEmpty()
        ? new AstIterator(tokenIterator, version)
        : new AstIterator(tokenIterator, version, observers);
  }
}
