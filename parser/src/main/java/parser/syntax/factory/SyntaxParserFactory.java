package parser.syntax.factory;

import java.util.Set;
import parser.syntax.TokenStream;
import parser.syntax.parsers.SyntaxParser;
import parser.syntax.provider.ProviderType;
import parser.syntax.provider.SyntaxParserProvider;
import parser.syntax.result.SyntaxParserFactoryResult;
import token.Token;
import token.TokenType;

public class SyntaxParserFactory {
  private final Set<? extends ProviderType> providerTypes;

  public SyntaxParserFactory(Set<? extends ProviderType> providerTypes) {
    this.providerTypes = providerTypes;
  }

  public SyntaxParserFactoryResult getSyntaxParser(TokenStream tokens) {
    if (!tokens.hasNext()) {
      throw new IllegalArgumentException("Empty token list");
    }

    while (tokens.hasNext()) {
      Token firstToken = tokens.getCurrentToken();
      if (firstToken.getType() != TokenType.LINE_BREAK
          && firstToken.getType() != TokenType.WHITESPACE) {
        break;
      } else {
        tokens = tokens.advance();
      }
    }

    for (ProviderType type : providerTypes) {
      SyntaxParserProvider provider = type.getProvider();
      if (provider.supports(tokens)) {
        return new SyntaxParserFactoryResult(provider.createParser(), tokens);
      }
    }

    throw new IllegalArgumentException("No suitable parser found");
  }
}
