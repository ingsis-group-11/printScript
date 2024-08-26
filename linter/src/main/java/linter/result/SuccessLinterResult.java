package linter.result;

public class SuccessLinterResult implements LinterResult {
    @Override
    public boolean hasErrors() {
        return false;
    }
}
