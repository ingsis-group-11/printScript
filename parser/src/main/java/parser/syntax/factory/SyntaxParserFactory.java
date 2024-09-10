package parser.syntax.factory;

import parser.syntax.parsers.SyntaxParser;
import parser.syntax.TokenStream;
import parser.syntax.provider.ProviderType;
import parser.syntax.provider.SyntaxParserProvider;
import token.Token;
import token.TokenType;

import java.util.Set;

public class SyntaxParserFactory {
  private final Set<? extends ProviderType> providerTypes;

  public SyntaxParserFactory(Set<? extends ProviderType> providerTypes) {
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
      } else {
        tokens.advance();
      }
    }

    for (ProviderType type : providerTypes) {
      SyntaxParserProvider provider = type.getProvider();
      if (provider.supports(tokens)) {
        return provider.createParser();
      }
    }

    throw new IllegalArgumentException("No suitable parser found");
  }
}