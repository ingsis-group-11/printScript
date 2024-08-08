package lexer;


import token.*;

import java.io.IOException;
import java.util.*;
import result.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Lexer {
    public LexingResult lex(String filePath) throws IOException {
        if (!checkIfFileExistsAndIsReadable(filePath)) {
            return new FileFailureResult("File does not exist or is not readable");
        }

        FileReader fileReader = new FileReader();
        String stringFile = fileReader.readFile(filePath);

        Tokenizer tokenizer = new Tokenizer(stringFile);
        List<Token> tokens = tokenizer.tokenize();

        return new SuccessfulResult(tokens);
    }

    private Boolean checkIfFileExistsAndIsReadable(String filePath) {
        Path path = Paths.get(filePath);
        return Files.exists(path) || Files.isReadable(path);
    }
}



