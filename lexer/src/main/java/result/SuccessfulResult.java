package result;

import token.Token;
import java.util.List;

public record SuccessfulResult(List<Token> tokens) implements LexingResult {
}