package parser;

import AST.nodes.ASTNode;
import java.util.ArrayList;
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

  public List<ASTNode> parse(List<Token> tokens) {
    // Syntax analysis
    List<ASTNode> astNodes = syntaxParser(tokens);
    // Semantic analysis
    semanticParser(astNodes);

    return astNodes;
  }

  private void semanticParser(List<ASTNode> astNodes) {
    SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
    semanticError = semanticAnalyzer.analyze(astNodes);
  }

  private List<ASTNode> syntaxParser(List<Token> tokens) {
    List<List<Token>> sentences = TokenSplitter.splitBySemicolons(tokens);

    return createTrees(sentences);
  }

  private List<ASTNode> createTrees(List<List<Token>> sentences) {
    List<ASTNode> astNodes = new ArrayList<>();
    SyntaxParserFactory factory = new SyntaxParserFactory();

    for (List<Token> sentence : sentences) {
      SyntaxParser syntaxParser = factory.getSyntaxParser(sentence);
      syntaxResult = syntaxParser.syntaxParse(sentence);
      if (!syntaxResult.hasErrors()){
        astNodes.add(((SyntaxSuccessResult) syntaxResult).getAstNode());
      }
    }
    return astNodes;
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
