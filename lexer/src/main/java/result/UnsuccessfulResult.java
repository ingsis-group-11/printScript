package result;

public record UnsuccessfulResult(String message, Integer column, Integer line) implements LexingResult {
}
