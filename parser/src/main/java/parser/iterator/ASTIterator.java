package parser.iterator;

import AST.nodes.ASTNode;
import parser.Parser;
import token.Token;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ASTIterator implements Iterator<ASTNode> {
  private final Parser parser;
  private final Iterator<Token> iterator;

  public ASTIterator (Iterator<Token> iterator, String version){
    this.parser = new Parser(version);
    this.iterator = iterator;

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
    return parser.parse(iterator);
  }
}
