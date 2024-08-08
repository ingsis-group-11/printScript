package lexer;

import org.junit.jupiter.api.Test;
import result.LexingResult;
import result.SuccessfulResult;
import token.NoValueToken;
import token.Token;
import token.TokenType;
import token.ValueToken;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LexerTest {

    @Test
    public void tokenizeOneOperationTest(){
        String filePath = "src/test/resources/operation.txt";
        Lexer lexer = new Lexer();

        SuccessfulResult tokensResult = (SuccessfulResult) lexer.tokenize(filePath);

        List<Token> tokens = tokensResult.tokens();

        assertEquals(4, tokens.size());
        assertEquals("5", tokens.get(0).getValue());
    }

    @Test
    public void tokenizeMultipleOperationsTest(){
        String filePath = "src/test/resources/multiple_operations.txt";
        Lexer lexer = new Lexer();

        SuccessfulResult tokensResult = (SuccessfulResult) lexer.tokenize(filePath);

        List<Token> tokens = tokensResult.tokens();

        System.out.println(tokens);

        assertEquals(8, tokens.size());
        assertEquals("5", tokens.get(0).getValue());
        assertEquals("3", tokens.get(2).getValue());
        assertEquals("10", tokens.get(4).getValue());
        assertEquals("/", tokens.get(5).getValue());
        assertEquals(2, tokens.get(5).getLine());
    }

    @Test
    public void tokenizeVariableAssignationTest(){
        String filePath = "src/test/resources/variable_assignation.txt";
        Lexer lexer = new Lexer();

        SuccessfulResult tokensResult = (SuccessfulResult) lexer.tokenize(filePath);

        List<Token> tokens = tokensResult.tokens();

        System.out.println(tokens);

        assertEquals(7, tokens.size());
        assertEquals(TokenType.LET_KEYWORD, tokens.get(0).getType());
        assertEquals("name", tokens.get(1).getValue());
        assertEquals(TokenType.IDENTIFIER, tokens.get(1).getType());
        assertEquals(TokenType.STRING_TYPE, tokens.get(3).getType());
        assertEquals("\"John\"", tokens.get(5).getValue());
        assertEquals(TokenType.STRING, tokens.get(5).getType());

    }

    @Test
    public void tokenizeStringDeclarationTest(){
        String filePath = "src/test/resources/string_declaration.txt";
        Lexer lexer = new Lexer();

        SuccessfulResult tokensResult = (SuccessfulResult) lexer.tokenize(filePath);

        List<Token> tokens = tokensResult.tokens();

        System.out.println(tokens);
    }

    @Test
    public void tokenizeCompleteTest(){
        String filePath = "src/test/resources/complete.txt";
        Lexer lexer = new Lexer();

        LexingResult result = lexer.tokenize(filePath);

        List<Token> tokens = ((SuccessfulResult) result).tokens();

        System.out.println(tokens);

        assertEquals(new NoValueToken(TokenType.LET_KEYWORD, 1, 1), tokens.get(0));
        assertEquals(new ValueToken(TokenType.IDENTIFIER, "a", 5, 1), tokens.get(1));
        assertEquals(new NoValueToken(TokenType.PRINT_KEYWORD, 1, 4), tokens.get(20));

    }
}
