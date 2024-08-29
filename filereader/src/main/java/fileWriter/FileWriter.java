package fileWriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileWriter {
  public void writeFile(String filePath, String content) throws IOException {
    if (!checkIfFileIsWritable(filePath)) {
      throw new IOException("File not writable");
    }
    Files.write(Paths.get(filePath), content.getBytes());
  }

  private Boolean checkIfFileIsWritable(String filePath) {
    Path path = Paths.get(filePath);
    return Files.exists(path) && Files.isWritable(path);
  }
}