package linter;

import ast.nodes.AstNode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import linter.result.LinterResult;
import linter.rules.Rule;

public class Linter {
  public void lintRun(Iterator<AstNode> nodes, List<Rule> rules) {
    while (nodes.hasNext()) {
      lint(nodes.next(), rules);
    }
  }

  private void lint(AstNode node, List<Rule> rules) {
    List<String> errors = new ArrayList<>();
    for (Rule rule : rules) {
      LinterResult result = rule.lint(node);
      if (result.hasErrors()) {
        errors.addAll(result.getErrors());
      }
    }
    resolveErrors(errors);
  }

  private void resolveErrors(List<String> errors) {
    if (!errors.isEmpty()) {
      StringBuilder messages = new StringBuilder();
      for (String message : errors) {
        messages.append(message).append("\n");
      }
      throw new RuntimeException(messages.toString());
    }
  }
}
