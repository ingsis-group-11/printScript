package parser;

import AST.nodes.ASTNode;
import java.util.Iterator;
import parser.semantic.SemanticAnalyzer;
import parser.semantic.result.SemanticErrorResult;
import parser.semantic.result.SemanticResult;
import parser.syntax.SyntaxParser;
import parser.syntax.SyntaxParserFactory;
import parser.syntax.result.SyntaxErrorResult;
import parser.syntax.result.SyntaxResult;
import parser.syntax.result.SyntaxSuccessResult;
import token.Token;

public class Parser {

  public ASTNode parse(Iterator<Token> tokens) {
    ASTNode node = null;
    // Syntax analysis
    SyntaxResult syntaxResult = syntaxParser(tokens);
    // Semantic analysis
    if (syntaxResult instanceof SyntaxErrorResult syntaxError) {
      resolveSyntaxErrors(syntaxError);
    }
    else if ( syntaxResult instanceof SyntaxSuccessResult syntaxSuccess) {
      node = syntaxSuccess.astNode();
      semanticParser(node);
    }

    return node;
  }

  private void semanticParser(ASTNode node) {
    SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
    SemanticResult result = semanticAnalyzer.analyze(node);
    if (result.hasErrors()) {
      resolveSemanticErrors(result);
    }
  }

  private void resolveSemanticErrors(SemanticResult result) {
    new ErrorResolver().resolveSemanticErrors(result);
  }


  private SyntaxResult syntaxParser(Iterator<Token> tokens) {
    return createTree(tokens);
  }

  private SyntaxResult createTree(Iterator<Token> tokens) {
    SyntaxParserFactory factory = new SyntaxParserFactory();
    SyntaxParser syntaxParser = factory.getSyntaxParser(tokens);
    return syntaxParser.syntaxParse(tokens);
  }

  public void resolveSyntaxErrors(SyntaxErrorResult syntaxError) {
    new ErrorResolver().resolveSyntaxErrors(syntaxError);
  }

  public void resolveErrors(SemanticErrorResult semanticError, SyntaxResult syntaxResult) {
    new ErrorResolver().resolveErrors(semanticError, syntaxResult);
  }
}


