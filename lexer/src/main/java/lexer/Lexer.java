package lexer;


import token.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import result.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
    private final Map<String, TokenType> tokenMap = generateTokenMap();


    public LexingResult tokenize(String filePath) {
        if (!checkIfFileExistsAndIsReadable(filePath)) {
            return new FileFailureResult("File does not exist or is not readable");
        }

        List<String> words = separateWords(filePath);

        if (words.isEmpty()) {
            return new FileFailureResult("Something went wrong while reading the file, perhaps it is empty.");
        }

        return getTokensFromWords(words);
    }

    private Boolean checkIfFileExistsAndIsReadable(String filePath) {
        Path path = Paths.get(filePath);
        return Files.exists(path) || Files.isReadable(path);
    }

    private LexingResult getTokensFromWords(List<String> words) {
        List<Token> tokens = new ArrayList<>();
        int lineNumber = 1;
        int columnNumber = 1;

        for (String word : words) {
            if (word.equals("__SPECIAL__")) {
                continue;
            }
            if (word.equals(" ")) {
                columnNumber++;
                continue;
            }
            if (word.equals("")){
                continue;
            }
            TokenType type = tokenMap.getOrDefault(word, TokenType.IDENTIFIER);
            if (type == TokenType.IDENTIFIER) {
                if (word.matches("[0-9]*\\\\.?[0-9]+")) {
                    type = TokenType.NUMBER;
                } else if (word.matches("\".*\"")) {
                    type = TokenType.STRING;
                }
            }
            if (!isValueToken(type)) {
                tokens.add(new NoValueToken(type, columnNumber, lineNumber));
            } else {
                tokens.add(new ValueToken(type, word, columnNumber, lineNumber));
            }
            if (type == TokenType.SEMICOLON) {
                lineNumber++;
                columnNumber = 1;
            }
            columnNumber = columnNumber + word.length();
        }

        return new SuccessfulResult(tokens);
    }

    private List<String> separateWords(String filePath){
        List<String> words = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.replaceAll("([:;()+=/*-])", "__SPECIAL__$1__SPECIAL__");
                Matcher m = Pattern.compile("\"[^\"]*\"|'[^']*'|\\S+|\\s|__SPECIAL__").matcher(line);
                while (m.find()) {
                    String[] subWords = m.group().split("__SPECIAL__");
                    words.addAll(Arrays.asList(subWords));
                }
            }
        } catch (IOException e) {
            return new ArrayList<>();
        }

        return words;
    }

    private Map<String, TokenType> generateTokenMap() {
        HashMap<String, TokenType> newTokenMap = new HashMap<>();

        newTokenMap.put("let", TokenType.LET_KEYWORD);
        newTokenMap.put("println", TokenType.PRINT_KEYWORD);
        newTokenMap.put("string", TokenType.STRING_TYPE);
        newTokenMap.put("number", TokenType.NUMBER_TYPE);
        newTokenMap.put(";", TokenType.SEMICOLON);
        newTokenMap.put("=", TokenType.ASSIGN);
        newTokenMap.put("+", TokenType.OPERATOR);
        newTokenMap.put("-", TokenType.OPERATOR);
        newTokenMap.put("*", TokenType.OPERATOR);
        newTokenMap.put("/", TokenType.OPERATOR);
        newTokenMap.put(")", TokenType.PARENTHESIS_CLOSE);
        newTokenMap.put("(", TokenType.PARENTHESIS_OPEN);
        newTokenMap.put(":", TokenType.COLON);

        return newTokenMap;
    }

    private Boolean isValueToken(TokenType type) {
        return type == TokenType.STRING || type == TokenType.NUMBER || type == TokenType.IDENTIFIER
                || type == TokenType.OPERATOR;
    }
}



