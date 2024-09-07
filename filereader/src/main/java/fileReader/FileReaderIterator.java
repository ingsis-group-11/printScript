package fileReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class FileReaderIterator implements InputReader {
  private final Reader reader;
  private int currentChar;

  public FileReaderIterator(InputStream inputStream) throws IOException {
    this.reader = new InputStreamReader(inputStream);
    currentChar = reader.read();
  }

  @Override
  public char next() {
    try {
      currentChar = reader.read();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return (char) currentChar;
  }

  @Override
  public boolean hasNext() {
    if (currentChar != -1) {
      return true;
    } else {
      try {
        reader.close();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
      return false;
    }
  }

  @Override
  public char current() {
    if (currentChar == -1) {
      throw new IllegalStateException("No more characters to read");
    }
    return (char) currentChar;
  }
}
