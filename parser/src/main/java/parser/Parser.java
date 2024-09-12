package parser;

import ast.nodes.AstNode;
import java.util.Set;
import parser.semantic.SemanticAnalyzer;
import parser.semantic.variables.VariablesMap;
import parser.syntax.TokenStream;
import parser.syntax.factory.SyntaxParserFactory;
import parser.syntax.parsers.SyntaxParser;
import parser.syntax.provider.ProviderType;
import parser.syntax.resolver.ParserVersionResolver;
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
    SyntaxParser syntaxParser = factory.getSyntaxParser(tokenStream);
    return syntaxParser.syntaxParse(tokenStream, version);
  }
}
