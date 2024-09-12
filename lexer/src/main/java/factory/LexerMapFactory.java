package factory;

import java.util.HashMap;
import java.util.Map;
import lexer.MapReader;
import lexer.MapReaderV1;
import lexer.MapReaderV2;

public class LexerMapFactory {
  Map<String, MapReader> mapReaders = new HashMap<>();

  public LexerMapFactory() {
    mapReaders.put("1.0", new MapReaderV1());
    mapReaders.put("1.1", new MapReaderV2());
  }

  public MapReader getLexerMap(String version) {
    if (mapReaders.containsKey(version)) {
      return mapReaders.get(version);
    }
    throw new IllegalArgumentException("Invalid version: " + version);
  }
}
