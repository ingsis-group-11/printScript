package parser.iterator;

import ast.nodes.AstNode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import parser.Parser;
import providers.iterator.PrintScriptIterator;
import providers.observer.Observer;
import token.Token;

public class AstIterator implements Iterator<AstNode> {
  private final Parser parser;
  private final PrintScriptIterator<Token> iterator;
  private List<Observer> observers = new ArrayList<>();

  public AstIterator(PrintScriptIterator<Token> iterator, String version) {
    this.parser = new Parser(version);
    this.iterator = iterator;
  }

  public AstIterator(
      PrintScriptIterator<Token> iterator, String version, List<Observer> observers) {
    this.parser = new Parser(version);
    this.iterator = iterator;
    this.observers = observers;
  }

  public void addObserver(Observer observer) {
    observers.add(observer);
  }

  public void notifyObservers() {
    for (Observer observer : observers) {
      observer.update(" █ ");
    }
  }

  @Override
  public boolean hasNext() {
    return iterator.hasNext();
  }

  @Override
  public AstNode next() {
    if (!hasNext()) {
      throw new NoSuchElementException("No more tokens to parse");
    }
    this.notifyObservers();
    return parser.parse(iterator);
  }
}
