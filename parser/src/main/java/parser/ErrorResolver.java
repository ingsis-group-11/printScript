package parser;

import parser.semantic.result.SemanticResult;
import parser.syntax.result.SyntaxErrorResult;
import parser.syntax.result.SyntaxResult;

public class ErrorResolver {
  public void resolveErrors(SemanticResult semanticError, SyntaxResult syntaxResult) {
    String messages = "";
    if (semanticError.hasErrors()) {
      messages += "Semantic errors:\n";
      for (String message : semanticError.messages()) {
        messages += message + "\n";
      }
    }
    if (syntaxResult.hasErrors()) {
      messages += "Syntax errors:\n";
      for (String message : syntaxResult.messages()) {
        messages += message + "\n";
      }
    }
    if (!messages.isEmpty()) {
      throw new RuntimeException(messages);
    }
  }

  public void resolveSyntaxErrors(SyntaxErrorResult syntaxError) {
    String messages = "Syntax errors:\n";
    for (String message : syntaxError.messages()) {
      messages += message + "\n";
    }
    throw new RuntimeException(messages);
  }

  public void resolveSemanticErrors(SemanticResult result) {
    String messages = "Semantic errors:\n";
    for (String message : result.messages()) {
      messages += message + "\n";
    }
    throw new RuntimeException(messages);
  }
}
