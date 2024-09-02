package parser;

import AST.nodes.ASTNode;
import parser.semantic.SemanticAnalyzer;
import parser.semantic.result.SemanticErrorResult;
import parser.semantic.result.SemanticResult;
import parser.syntax.SyntaxParser;
import parser.syntax.SyntaxParserFactory;
import parser.syntax.result.SyntaxErrorResult;
import parser.syntax.result.SyntaxResult;
import parser.syntax.result.SyntaxSuccessResult;
import token.Token;
import parser.syntax.TokenStream;
import java.util.EnumSet;
import java.util.Iterator;

public class Parser {

  public ASTNode parse(Iterator<Token> tokens) {
    ASTNode node = null;
    TokenStream tokenStream = new TokenStream(tokens);
    // Syntax analysis
    SyntaxResult syntaxResult = syntaxParser(tokenStream);
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

  private SyntaxResult syntaxParser(TokenStream tokenStream) {
    return createTree(tokenStream);
  }

  private SyntaxResult createTree(TokenStream tokenStream) {
    EnumSet<ProviderTypeV2> providerTypes = EnumSet.allOf(ProviderTypeV2.class);
    SyntaxParserFactory factory = new SyntaxParserFactory(providerTypes);
    SyntaxParser syntaxParser = factory.getSyntaxParser(tokenStream);
    return syntaxParser.syntaxParse(tokenStream);
  }

  public void resolveSyntaxErrors(SyntaxErrorResult syntaxError) {
    new ErrorResolver().resolveSyntaxErrors(syntaxError);
  }

  public void resolveErrors(SemanticErrorResult semanticError, SyntaxResult syntaxResult) {
    new ErrorResolver().resolveErrors(semanticError, syntaxResult);
  }
}