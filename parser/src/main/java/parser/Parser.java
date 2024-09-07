package parser;

import AST.nodes.ASTNode;
import parser.semantic.SemanticAnalyzer;
import parser.semantic.result.SemanticResult;
import parser.syntax.parsers.SyntaxParser;
import parser.syntax.factory.SyntaxParserFactory;
import parser.syntax.provider.ProviderType;
import parser.syntax.resolver.ParserVersionResolver;
import parser.syntax.result.SyntaxErrorResult;
import parser.syntax.result.SyntaxResult;
import parser.syntax.result.SyntaxSuccessResult;
import token.Token;
import parser.syntax.TokenStream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Parser {
  private final String version;

  public Parser(String version){
    this.version = version;
  }

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
    return createTree(tokenStream, version);
  }

  private SyntaxResult createTree(TokenStream tokenStream, String version) {
    Set<ProviderType> providerTypes = ParserVersionResolver.getParserProviderTypes(version);
    SyntaxParserFactory factory = new SyntaxParserFactory(providerTypes);
    SyntaxParser syntaxParser = factory.getSyntaxParser(tokenStream);
    return syntaxParser.syntaxParse(tokenStream);
  }

  public void resolveSyntaxErrors(SyntaxErrorResult syntaxError) {
    new ErrorResolver().resolveSyntaxErrors(syntaxError);
  }

}