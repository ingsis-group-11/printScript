package ast.nodes;

import ast.AstVisitor;
import ast.tokens.AstToken;
import ast.tokens.AstTokenType;

public class DeclarationNode implements AstNode {
  private final AstToken type;
  private final AstToken name;
  private final AstToken declarationKeyWord;
  private final Integer line;
  private final Integer column;

  public DeclarationNode(
          AstToken type, AstToken name, AstToken declarationKeyWord, Integer line, Integer column) {
    this.type = type;
    this.name = name;
    this.declarationKeyWord = declarationKeyWord;
    this.line = line;
    this.column = column;
  }

  public AstToken getTypeToken() {
    return type;
  }

  public AstToken getNameToken() {
    return name;
  }

  public AstToken getDeclarationKeyWord() {
    return declarationKeyWord;
  }

  public boolean isMutable() {
    return declarationKeyWord.getType() == AstTokenType.LET_KEYWORD;
  }

  @Override
  public <T> T accept(AstVisitor<T> visitor) {
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
