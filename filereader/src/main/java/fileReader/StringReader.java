package fileReader;

import java.util.NoSuchElementException;

public class StringReader implements InputReader {
    private final String input;
    private int position;

    public StringReader(String filePath) {
        this.input = new FileReader().read(filePath);
        this.position = 0;
    }

    @Override
    public char next() {
        if (hasNext()) {
            return input.charAt(position++);
        }
        throw new NoSuchElementException("No more characters to read");
    }

    @Override
    public boolean hasNext() {
        return position < input.length();
    }

    @Override
    public char current() {
        if (position < input.length()) {
            return input.charAt(position);
        }
        throw new NoSuchElementException("No more characters to read");
    }
}