package parser.semantic.result;

import java.util.List;

public record SemanticErrorResult(List<String> messages) implements SemanticResult {

    @Override
    public boolean hasErrors() {
        return true;
    }
}
