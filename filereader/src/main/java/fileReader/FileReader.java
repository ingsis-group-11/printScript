package fileReader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileReader {
  public String read(String filePath) {
    try {
      Path path = Paths.get(filePath);
      return Files.readString(path, StandardCharsets.UTF_8).replace("\r\n", "\n").replace("\r", "\n");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
