package parser.syntax;

import parser.syntax.provider.SyntaxParserProvider;
import parser.syntax.provider.SyntaxParserProviderType;
import token.Token;
import token.TokenType;

import java.util.EnumSet;

public class SyntaxParserFactory {
  private final EnumSet<SyntaxParserProviderType> providerTypes;

  public SyntaxParserFactory(EnumSet<SyntaxParserProviderType> providerTypes) {
    this.providerTypes = providerTypes;
  }

  public SyntaxParser getSyntaxParser(TokenStream tokens) {
    if (!tokens.hasNext()) {
      throw new IllegalArgumentException("Empty token list");
    }

    while (tokens.hasNext()) {
      Token firstToken = tokens.getCurrentToken();
      if (firstToken.getType() != TokenType.LINE_BREAK && firstToken.getType() != TokenType.WHITESPACE) {
        break;
      }
    }

    for (SyntaxParserProviderType type : providerTypes) {
      SyntaxParserProvider provider = type.getProvider();
      if (provider.supports(tokens)) {
        return provider.createParser();
      }
    }

    throw new IllegalArgumentException("No suitable parser found");
  }
}