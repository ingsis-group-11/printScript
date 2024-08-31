package runner;

import AST.nodes.ASTNode;
import fileReader.FileReaderIterator;
import interpreter.Interpreter;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import interpreter.providers.inputProvider.InputProvider;
import interpreter.providers.printProvider.PrintProvider;
import iterator.TokenIterator;
import lexer.Lexer;
import parser.iterator.ASTIterator;
import parser.Parser;
import token.Token;

public class Runner {
  //When you don't use inputProvider
  public void run(String filePath) throws IOException {
    FileReaderIterator fileIterator = new FileReaderIterator(new File(filePath));
    Iterator<Token> tokens = new TokenIterator(fileIterator, new Lexer(fileIterator));
    Iterator<ASTNode> ASTNodes = new ASTIterator(new Parser(), tokens);
    new Interpreter().interpret(ASTNodes);
  }

  //When you use inputProvider
  public void run(String filePath, InputProvider inputProvider) throws IOException {
    FileReaderIterator fileIterator = new FileReaderIterator(new File(filePath));
    Iterator<Token> tokens = new TokenIterator(fileIterator, new Lexer(fileIterator));
    Iterator<ASTNode> ASTNodes = new ASTIterator(new Parser(), tokens);
    new Interpreter(inputProvider).interpret(ASTNodes);
  }

  //When you use printProvider
  public void run(String filePath, PrintProvider printProvider) throws IOException {
    FileReaderIterator fileIterator = new FileReaderIterator(new File(filePath));
    Iterator<Token> tokens = new TokenIterator(fileIterator, new Lexer(fileIterator));
    Iterator<ASTNode> ASTNodes = new ASTIterator(new Parser(), tokens);
    new Interpreter(printProvider).interpret(ASTNodes);
  }

  //When you use inputProvider and printProvider
  public void run(String filePath, InputProvider inputProvider, PrintProvider printProvider) throws IOException {
    FileReaderIterator fileIterator = new FileReaderIterator(new File(filePath));
    Iterator<Token> tokens = new TokenIterator(fileIterator, new Lexer(fileIterator));
    Iterator<ASTNode> ASTNodes = new ASTIterator(new Parser(), tokens);
    new Interpreter(inputProvider, printProvider).interpret(ASTNodes);
  }
}
