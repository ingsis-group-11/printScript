package parser.semantic;

import ast.nodes.AstNode;
import parser.semantic.result.SemanticResult;
import parser.semantic.result.SemanticSuccessResult;

public class SemanticAnalyzer {
  private final SemanticVisitor semanticVisitor;

  public SemanticAnalyzer() {
    this.semanticVisitor = new SemanticVisitor();
  }

  public SemanticResult analyze(AstNode node) {
    SemanticResult result = node.accept(semanticVisitor);
    if (result.hasErrors()) {
      throw new RuntimeException("Semantic analysis failed: " + result.message());
    }
    return new SemanticSuccessResult();
  }
}
