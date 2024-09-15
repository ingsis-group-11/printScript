package parser;

import ast.nodes.AstNode;
import java.util.Set;
import parser.semantic.SemanticAnalyzer;
import parser.syntax.TokenStream;
import parser.syntax.factory.SyntaxParserFactory;
import parser.syntax.parsers.SyntaxParser;
import parser.syntax.provider.ProviderType;
import parser.syntax.resolver.ParserVersionResolver;
import parser.syntax.result.SyntaxParserFactoryResult;
import providers.iterator.PrintScriptIterator;
import token.Token;

public class Parser {
  private final String version;
  SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();

  public Parser(String version) {
    this.version = version;
  }

  public AstNode parse(PrintScriptIterator<Token> tokens) {
    TokenStream tokenStream = new TokenStream(tokens);
    // Syntax analysis
    AstNode syntaxResult = syntaxParser(tokenStream);
    // Semantic analysis
    semanticParser(syntaxResult);
    return syntaxResult;
  }

  private void semanticParser(AstNode node) {
    semanticAnalyzer.analyze(node);
  }

  private AstNode syntaxParser(TokenStream tokenStream) {
    Set<ProviderType> providerTypes = ParserVersionResolver.getParserProviderTypes(version);
    SyntaxParserFactory factory = new SyntaxParserFactory(providerTypes);
    SyntaxParserFactoryResult result = factory.getSyntaxParser(tokenStream);
    tokenStream = result.tokenStream();
    SyntaxParser syntaxParser = result.syntaxParser();
    return syntaxParser.syntaxParse(tokenStream, version);
  }
}
