package providers.outputProvider;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

public class FileWriter implements OutputProvider {
  private final String filePath;

  public FileWriter(String filePath) {
    this.filePath = filePath;
  }

  @Override
  public void write(Iterator<String> content) throws IOException {
    if (checkIfFileIsWritable(filePath)) {
      java.io.FileWriter fileWriter = new java.io.FileWriter(filePath, true);
      while (content.hasNext()) {
        fileWriter.write(content.next());
      }
      fileWriter.flush();
      fileWriter.close();
    } else {
      throw new IOException("File is not writable");
    }
  }

  @Override
  public String getOutput() {
    return "";
  }

  private Boolean checkIfFileIsWritable(String filePath) {
    Path path = Paths.get(filePath);
    return Files.exists(path) && Files.isWritable(path);
  }
}