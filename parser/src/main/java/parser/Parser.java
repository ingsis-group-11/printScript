package parser;

import AST.nodes.ASTNode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import parser.semantic.SemanticAnalyzer;
import parser.semantic.result.SemanticResult;
import parser.syntax.SyntaxParser;
import parser.syntax.SyntaxParserFactory;
import parser.syntax.result.SyntaxResult;
import parser.syntax.result.SyntaxSuccessResult;
import token.Token;

public class Parser {
  private SemanticResult semanticError;
  private SyntaxResult syntaxResult;

  public ASTNode parse(Iterator<Token> tokens) {
    // Syntax analysis
    ASTNode node = syntaxParser(tokens);
    // Semantic analysis
    semanticParser(node);

    return node;
  }

  private void semanticParser(ASTNode node) {
    SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
    semanticError = semanticAnalyzer.analyze(node);
  }


  }

  private ASTNode syntaxParser(Iterator<Token> tokens) {
    return createTree(tokens);
  }

  private ASTNode createTree(Iterator<Token> tokens) {
    ASTNode astNode;
    SyntaxParserFactory factory = new SyntaxParserFactory();
    SyntaxParser syntaxParser = factory.getSyntaxParser(tokens);
    syntaxResult = syntaxParser.syntaxParse(tokens);
    if (syntaxResult.hasErrors()) {
      return null;
    }
    astNode = ((SyntaxSuccessResult) syntaxResult).getAstNode();
    return astNode;
  }

  public SemanticResult getSemanticError() {
      return semanticError;
  }

  public SyntaxResult getSyntaxResult() {
      return syntaxResult;
  }

  public void resolveErrors() {

    String messages = "";
    if (semanticError.hasErrors()) {
      messages+="Semantic errors:\n";
      for (String message : semanticError.messages()) {
        messages+=message + "\n";
      }
    }
    if (syntaxResult.hasErrors()) {
      messages+="Syntax errors:\n";
      for (String message : syntaxResult.messages()) {
        messages+=message + "\n";
      }
    }
    if (!messages.isEmpty()) {
      throw new RuntimeException(messages);
    }
  }
}
