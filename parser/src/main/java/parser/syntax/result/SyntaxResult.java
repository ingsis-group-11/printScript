package parser.syntax.result;

import java.util.List;

public interface SyntaxResult {
    boolean hasErrors();

    List<String> messages();
}
