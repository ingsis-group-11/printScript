package fileWriter;

import java.io.IOException;
import java.util.Iterator;

public class TestWriter implements OutputProvider {
  private String result = "";

  @Override
  public void write(Iterator<String> iterator) throws IOException {
    while (iterator.hasNext()) {
      result += iterator.next();
    }
  }

  @Override
  public String getOutput() {
    return result;
  }
}
