package parser;

import AST.nodes.ASTNode;
import token.Token;

import java.util.Iterator;

public class ASTIterator implements Iterator<ASTNode> {
  private final Parser parser;
  private final Iterator<Token> iterator;

  public ASTIterator (Parser parser, Iterator<Token> iterator){
    this.parser = parser;
    this.iterator = iterator;
  }

  @Override
  public boolean hasNext() {
    return iterator.hasNext();
  }

  @Override
  public ASTNode next() {
   return parser.parse(iterator);
  }
}
