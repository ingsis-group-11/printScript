package parser.semantic;

import AST.nodes.ASTNode;
import java.util.ArrayList;
import java.util.List;
import parser.semantic.result.SemanticErrorResult;
import parser.semantic.result.SemanticResult;
import parser.semantic.result.SemanticSuccessResult;

public class SemanticAnalyzer {
  private final SemanticVisitor semanticVisitor;

  public SemanticAnalyzer() {
    this.semanticVisitor = new SemanticVisitor();
  }

  public SemanticResult analyze(ASTNode node) {
    List<String> messages = new ArrayList<>();
      SemanticResult result = node.accept(semanticVisitor);
      if (result.hasErrors()) {
        messages.addAll(result.messages());
      }
    if (messages.isEmpty()) {
      return new SemanticSuccessResult();
    }
    return new SemanticErrorResult(messages);
  }
}
