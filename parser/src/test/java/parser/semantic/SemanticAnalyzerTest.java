package parser.semantic;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import parser.Parser;
import token.Token;
import token.TokenType;
import token.ValueToken;
import java.util.List;

public class SemanticAnalyzerTest {

    /*
    @Test
    public void testOneSemanticError() {
        //GIVEN
        List<Token> tokens = List.of(
                new ValueToken(TokenType.LET_KEYWORD, "let", 0, 0),
                new ValueToken(TokenType.IDENTIFIER, "name", 4, 0),
                new ValueToken(TokenType.COLON, ":", 8, 0),
                new ValueToken(TokenType.STRING_TYPE, "string", 10, 0),
                new ValueToken(TokenType.ASSIGN, "=", 17, 0),
                new ValueToken(TokenType.STRING, "John", 19, 0),
                new ValueToken(TokenType.SEMICOLON, ";", 25, 0)
        );

        // WHEN
        Parser parser = new Parser();
        assertThrows(RuntimeException.class, () -> {
            parser.parse(tokens);
        });
    }

     */

    @Test
    public void testOneSemanticValid() {
        //GIVEN
        List<Token> tokens = List.of(
                new ValueToken(TokenType.LET_KEYWORD, "let", 1, 1),
                new ValueToken(TokenType.IDENTIFIER, "name", 5, 1),
                new ValueToken(TokenType.COLON, ":", 6, 1),
                new ValueToken(TokenType.STRING_TYPE, "string", 11, 1),
                new ValueToken(TokenType.ASSIGN, "=", 18, 1),
                new ValueToken(TokenType.STRING, "Tomas", 20, 1),
                new ValueToken(TokenType.SEMICOLON, ";", 27, 1)
        );

        // WHEN
        Parser parser = new Parser();
        parser.parse(tokens);
    }
}
