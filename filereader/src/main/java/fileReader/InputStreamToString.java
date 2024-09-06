package fileReader;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class InputStreamToString {
  public String read(InputStream file) {
    try {
      return new String(file.readAllBytes(), StandardCharsets.UTF_8);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
