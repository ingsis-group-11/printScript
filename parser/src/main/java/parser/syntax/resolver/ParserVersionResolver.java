package parser.syntax.resolver;

import java.util.Set;
import parser.syntax.provider.ProviderType;
import parser.syntax.provider.ProviderTypeV1;
import parser.syntax.provider.ProviderTypeV2;

public class ParserVersionResolver {

  public static Set<ProviderType> getParserProviderTypes(String version) {
    return switch (version) {
      case "1.1" -> Set.of(ProviderTypeV2.values());
      case "1.0" -> Set.of(ProviderTypeV1.values());
      default -> throw new IllegalStateException("Unexpected version: " + version);
    };
  }
}
