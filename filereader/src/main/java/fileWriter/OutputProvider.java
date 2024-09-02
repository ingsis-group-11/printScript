package fileWriter;

import java.io.IOException;
import java.util.Iterator;

public interface OutputProvider {
  void write(Iterator<String> iterator) throws IOException;
  String getOutput();
}
