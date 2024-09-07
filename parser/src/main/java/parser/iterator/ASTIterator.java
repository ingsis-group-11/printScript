package parser.iterator;

import AST.nodes.ASTNode;
import parser.Observer;
import parser.Parser;
import token.Token;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class ASTIterator implements Iterator<ASTNode> {
  private final Parser parser;
  private final Iterator<Token> iterator;
  private List<Observer> observers = new ArrayList<>();

  public ASTIterator (Iterator<Token> iterator, String version){
    this.parser = new Parser(version);
    this.iterator = iterator;
  }

  public ASTIterator (Iterator<Token> iterator, String version, List<Observer> observers){
    this.parser = new Parser(version);
    this.iterator = iterator;
    this.observers = observers;
  }

  public void addObserver(Observer observer) {
    observers.add(observer);
  }

  public void notifyObservers() {
    for (Observer observer : observers) {
      observer.update(".");
    }
  }

  @Override
  public boolean hasNext() {
    return iterator.hasNext();
  }

  @Override
  public ASTNode next() {
    if(!hasNext()){
      throw new NoSuchElementException("No more tokens to parse");
    }
    this.notifyObservers();
    return parser.parse(iterator);
  }
}
