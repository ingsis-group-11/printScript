package parser.syntax.result;

import java.util.List;

public record SyntaxErrorResult(List<String> messages) implements SyntaxResult {

    @Override
    public boolean hasErrors() {
        return true;
    }
}
