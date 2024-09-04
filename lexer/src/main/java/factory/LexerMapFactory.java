package factory;

import lexer.MapReader;
import lexer.MapReader1_0;

import java.util.HashMap;
import java.util.Map;

public class LexerMapFactory {
  Map<String, MapReader> mapReaders = new HashMap<>();

  public LexerMapFactory() {
    mapReaders.put("1.0", new MapReader1_0());
  }

  public MapReader getLexerMap(String version){
    if(mapReaders.containsKey(version)){
      return mapReaders.get(version);
    }
    throw new IllegalArgumentException("Invalid version: " + version);
  }
}
