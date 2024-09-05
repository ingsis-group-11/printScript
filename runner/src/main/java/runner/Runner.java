package runner;

import AST.nodes.ASTNode;
import fileReader.FileReaderIterator;
import interpreter.Interpreter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import providers.inputProvider.InputProvider;
import providers.printProvider.PrintProvider;
import iterator.TokenIterator;
import parser.iterator.ASTIterator;
import token.Token;

public class Runner {
  //When you don't use printProvider or inputProvider
  public void run(InputStream inputStream, String version) throws IOException {
    FileReaderIterator fileIterator = new FileReaderIterator(inputStream);
    Iterator<Token> tokens = new TokenIterator(fileIterator, version);
    Iterator<ASTNode> ASTNodes = new ASTIterator(tokens);
    new Interpreter().interpret(ASTNodes);
  }

  //When you use printProvider
  public void run(InputStream inputStream, String version, PrintProvider printProvider) throws IOException {
    FileReaderIterator fileIterator = new FileReaderIterator(inputStream);
    Iterator<Token> tokens = new TokenIterator(fileIterator, version);
    Iterator<ASTNode> ASTNodes = new ASTIterator(tokens, version);
    new Interpreter(printProvider).interpret(ASTNodes);
  }

  //When you use inputProvider
  public void run(InputStream inputStream, String version, InputProvider inputProvider) throws IOException {
    FileReaderIterator fileIterator = new FileReaderIterator(inputStream);
    Iterator<Token> tokens = new TokenIterator(fileIterator, version);
    Iterator<ASTNode> ASTNodes = new ASTIterator(tokens);
    new Interpreter(inputProvider).interpret(ASTNodes);
  }

  //When you use printProvider and inputProvider
  public void run(InputStream inputStream, String version, PrintProvider printProvider, InputProvider inputProvider) throws IOException {
    FileReaderIterator fileIterator = new FileReaderIterator(inputStream);
    Iterator<Token> tokens = new TokenIterator(fileIterator, version);
    Iterator<ASTNode> ASTNodes = new ASTIterator(tokens);
    new Interpreter(inputProvider, printProvider).interpret(ASTNodes);
  }
}
