package linter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JSONReader {

    public String read(String filePath) throws IOException {
      Path path = Paths.get(filePath);
      if (!Files.exists(path)) {
        throw new IOException("El archivo no existe: " + filePath);
      }
      return new String(Files.readAllBytes(path));
    }
}
