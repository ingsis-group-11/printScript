package parser.semantic;

import java.util.List;

public class SemanticResult {
    private final boolean hasErrors;
    private final List<String> message;

    public SemanticResult(boolean hasErrors, List<String> message) {
        this.hasErrors = hasErrors;
        this.message = message;
    }

    public boolean hasErrors() {
        return hasErrors;
    }

    public List<String> getMessages() {
        return message;
    }
}
