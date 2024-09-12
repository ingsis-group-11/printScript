package parser.syntax.resolver;

import java.util.Set;
import parser.syntax.provider.ProviderType;
import parser.syntax.provider.ProviderType1_0;
import parser.syntax.provider.ProviderType1_1;

public class ParserVersionResolver {

  public static Set<ProviderType> getParserProviderTypes(String version) {
    return switch (version) {
      case "1.1" -> Set.of(ProviderType1_1.values());
      case "1.0" -> Set.of(ProviderType1_0.values());
      default -> throw new IllegalStateException("Unexpected version: " + version);
    };
  }
}
