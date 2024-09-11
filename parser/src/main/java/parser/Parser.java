package parser;

import AST.nodes.ASTNode;
import parser.semantic.SemanticAnalyzer;
import parser.syntax.parsers.SyntaxParser;
import parser.syntax.factory.SyntaxParserFactory;
import parser.syntax.provider.ProviderType;
import parser.syntax.resolver.ParserVersionResolver;
import providers.iterator.PrintScriptIterator;
import token.Token;
import parser.syntax.TokenStream;

import java.util.Iterator;
import java.util.Set;

public class Parser {
  private final String version;

  public Parser(String version){
    this.version = version;
  }

  public ASTNode parse(PrintScriptIterator<Token> tokens) {
    TokenStream tokenStream = new TokenStream(tokens);
    // Syntax analysis
    ASTNode syntaxResult = syntaxParser(tokenStream);
    // Semantic analysis
    semanticParser(syntaxResult);
    return syntaxResult;
  }

  private void semanticParser(ASTNode node) {
    SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
    semanticAnalyzer.analyze(node);
  }

  private ASTNode syntaxParser(TokenStream tokenStream) {
    return createTree(tokenStream, version);
  }

  private ASTNode createTree(TokenStream tokenStream, String version) {
    Set<ProviderType> providerTypes = ParserVersionResolver.getParserProviderTypes(version);
    SyntaxParserFactory factory = new SyntaxParserFactory(providerTypes);
    SyntaxParser syntaxParser = factory.getSyntaxParser(tokenStream);
    return syntaxParser.syntaxParse(tokenStream, version);
  }


}