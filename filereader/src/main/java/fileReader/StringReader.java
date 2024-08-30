package fileReader;

public class StringReader implements InputReader {
    private final String input;
    private int position;

    public StringReader(String input) {
        this.input = input;
        this.position = 0;
    }

    @Override
    public int next() {
        if (hasNext()) {
            return input.charAt(position++);
        }
        return -1; // End of input
    }

    @Override
    public boolean hasNext() {
        return position < input.length();
    }

    @Override
    public int current() {
        if (position < input.length()) {
            return input.charAt(position);
        }
        return -1; // End of input
    }
}