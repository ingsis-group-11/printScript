package parser.semantic;

import AST.nodes.ASTNode;
import parser.semantic.result.SemanticResult;
import parser.semantic.result.SemanticSuccessResult;

public class SemanticAnalyzer {
  private final SemanticVisitor semanticVisitor;

  public SemanticAnalyzer() {
    this.semanticVisitor = new SemanticVisitor();
  }

  public SemanticResult analyze(ASTNode node) {
    SemanticResult result = node.accept(semanticVisitor);
    if (result.hasErrors()) {
      throw new RuntimeException("Semantic analysis failed: " + result.message());
    }
    return new SemanticSuccessResult();
  }
}
