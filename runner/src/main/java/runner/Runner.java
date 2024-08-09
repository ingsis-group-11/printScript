package runner;

import AST.nodes.ASTNode;
import interpreter.Interpreter;
import lexer.Lexer;
import parser.Parser;
import result.FileFailureResult;
import result.LexingResult;
import result.SuccessfulResult;
import result.UnsuccessfulResult;
import token.Token;

import java.io.IOException;
import java.util.List;

public class Runner {
    public void run(String filePath) throws IOException {
        List<Token> tokens = lexRun(filePath);
        List<ASTNode> ASTNodes = parseRun(tokens);
        interpretRun(ASTNodes);
    }

    private List<Token> lexRun(String filePath) throws IOException {
        Lexer lexer = new Lexer();
        LexingResult lexerResult = lexer.lex(filePath);

        resolveLexerErrors(lexerResult);

        List<Token> tokens = ((SuccessfulResult) lexerResult).tokens();
        return tokens;
    }

    private void resolveLexerErrors(LexingResult lexerResult) throws IOException {
        if(lexerResult instanceof FileFailureResult){
            throw new IOException(((FileFailureResult) lexerResult).message());
        }
        else if(lexerResult instanceof UnsuccessfulResult){
            throw new RuntimeException(((UnsuccessfulResult) lexerResult).message());
        }
    }

    private List<ASTNode> parseRun(List<Token> tokens){
        Parser parser = new Parser();
        return parser.parse(tokens);
    }

    private void interpretRun(List<ASTNode> nodes){
        Interpreter interpreter = new Interpreter();
        interpreter.interpret(nodes);
    }
}
