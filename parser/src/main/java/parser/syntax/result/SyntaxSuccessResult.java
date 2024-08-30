package parser.syntax.result;

import AST.nodes.ASTNode;
import java.util.List;

public record SyntaxSuccessResult(ASTNode astNode) implements SyntaxResult {

  @Override
  public boolean hasErrors() {
    return false;
  }

  @Override
  public List<String> messages() {
    return List.of();
  }
}