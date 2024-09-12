package runner;

import interpreter.Interpreter;
import iterator.FileReaderIterator;
import iterator.TokenIterator;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import parser.iterator.AstIterator;
import providers.inputProvider.InputProvider;
import providers.observer.Observer;
import providers.printProvider.PrintProvider;

public class Runner {
  public void run(InputStream inputStream, String version) throws IOException {
    FileReaderIterator fileIterator = new FileReaderIterator(inputStream);
    TokenIterator tokens = new TokenIterator(fileIterator, version);
    AstIterator ASTNodes = new AstIterator(tokens, version);
    new Interpreter().interpret(ASTNodes);
  }

  // When you use printProvider
  public void run(InputStream inputStream, String version, PrintProvider printProvider)
      throws IOException {
    FileReaderIterator fileIterator = new FileReaderIterator(inputStream);
    TokenIterator tokens = new TokenIterator(fileIterator, version);
    AstIterator ASTNodes = new AstIterator(tokens, version);
    new Interpreter(printProvider).interpret(ASTNodes);
  }

  // When you use inputProvider
  public void run(InputStream inputStream, String version, InputProvider inputProvider)
      throws IOException {
    FileReaderIterator fileIterator = new FileReaderIterator(inputStream);
    TokenIterator tokens = new TokenIterator(fileIterator, version);
    AstIterator ASTNodes = new AstIterator(tokens, version);
    new Interpreter(inputProvider).interpret(ASTNodes);
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
    AstIterator ASTNodes = new AstIterator(tokens, version);
    new Interpreter(inputProvider, printProvider).interpret(ASTNodes);
  }

  // When you use printProvider and inputProvider
  public void run(
      InputStream inputStream,
      String version,
      PrintProvider printProvider,
      InputProvider inputProvider,
      List<Observer> observers)
      throws IOException {
    FileReaderIterator fileIterator = new FileReaderIterator(inputStream);
    TokenIterator tokens = new TokenIterator(fileIterator, version);
    AstIterator ASTNodes = new AstIterator(tokens, version, observers);
    new Interpreter(inputProvider, printProvider).interpret(ASTNodes);
  }
}
