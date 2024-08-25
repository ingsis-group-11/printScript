package parser;

import AST.nodes.ASTNode;
import java.util.ArrayList;
import java.util.List;
import parser.semantic.SemanticAnalyzer;
import parser.semantic.result.SemanticResult;
import parser.syntax.SyntaxParser;
import parser.syntax.SyntaxParserFactory;
import token.Token;

public class Parser {
  private SemanticResult semanticError;

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
      ASTNode ast = syntaxParser.syntaxParse(sentence);
      astNodes.add(ast);
    }

    return astNodes;
  }

  public SemanticResult getSemanticError() {
      return semanticError;
  }

  public void resolveErrors() {
    if (semanticError.hasErrors()) {
      String messages = "";
      for (String message : semanticError.messages()) {
        messages+=message;
      }
      throw new RuntimeException(messages);
    }
  }
}
