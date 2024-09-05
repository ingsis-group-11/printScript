package AST.nodes;

import AST.ASTVisitor;
import token.Token;

public class DeclarationNode implements ASTNode {
  private final Token type;
  private final Token name;
  private final Token declarationKeyWord;
  private final Integer line;
  private final Integer column;

  public DeclarationNode(Token type, Token name, Token declarationKeyWord, Integer line, Integer column) {
    this.type = type;
    this.name = name;
    this.declarationKeyWord = declarationKeyWord;
    this.line = line;
    this.column = column;
  }

  public Token getTypeToken() {
    return type;
  }

  public Token getNameToken() {
    return name;
  }

  public Token getDeclarationKeyWord() {
    return declarationKeyWord;
  }

  public boolean isMutable() {
    return declarationKeyWord.getType() == token.TokenType.LET_KEYWORD;
  }

  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visit(this);
  }

  @Override
  public Integer getLine() {
    return line;
  }

  @Override
  public Integer getColumn() {
    return column;
  }
}
