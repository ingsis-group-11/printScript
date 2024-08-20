package runner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileReader {
  public String readFile(String filePath) throws IOException {
    if (!checkIfFileExistsAndIsReadable(filePath)) {
      throw new IOException("File not found or not readable");
    }
    return new String(Files.readAllBytes(Paths.get(filePath)));
  }

  private Boolean checkIfFileExistsAndIsReadable(String filePath) {
    Path path = Paths.get(filePath);
    return Files.exists(path) || Files.isReadable(path);
  }
}
