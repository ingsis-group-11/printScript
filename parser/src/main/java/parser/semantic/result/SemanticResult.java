package parser.semantic.result;

import java.util.List;

public interface SemanticResult {
    boolean hasErrors();
    List<String> messages();
}
