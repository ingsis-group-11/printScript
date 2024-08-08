package lexer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class FileReader {
    public String readFile(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }
}
