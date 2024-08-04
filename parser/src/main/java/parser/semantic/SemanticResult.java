package parser.semantic;

import java.util.List;

public class SemanticResult {
    private final boolean result;
    private final List<String> message;

    public SemanticResult(boolean result, List<String> message) {
        this.result = result;
        this.message = message;
    }

    public boolean getResult() {
        return result;
    }

    public List<String> getMessages() {
        return message;
    }
}
