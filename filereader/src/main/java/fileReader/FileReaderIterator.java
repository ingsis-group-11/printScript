package fileReader;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

public class FileReaderIterator implements InputReader {
    private final Reader reader;
    private int currentChar;

    public FileReaderIterator(File file) throws IOException {
        this.reader = new java.io.FileReader(file);
        currentChar = reader.read();
    }

    @Override
    public int next(){
        try {
            currentChar = reader.read();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        return currentChar;
    }

    @Override
    public boolean hasNext(){
       if ( currentChar != -1){
              return true;
         }
         else{
              try {
                reader.close();
              }
              catch (IOException e) {
                throw new RuntimeException(e);
              }
              return false;
       }
    }

    @Override
    public int current(){
        return currentChar;
    }
}
