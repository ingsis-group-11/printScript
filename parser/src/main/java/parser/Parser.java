package parser;

import AST.nodes.ASTNode;
import parser.semantic.SemanticAnalyzer;
import parser.semantic.SemanticResult;
import parser.syntax.SyntaxParser;
import parser.syntax.SyntaxParserFactory;
import token.Token;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    public List<ASTNode> parse(List<Token> tokens) {
        List<ASTNode> astNodes = new ArrayList<>();

        // Syntax analysis
        SyntaxParserFactory factory = new SyntaxParserFactory();

        List<List<Token>> sentences = TokenSplitter.splitBySemicolons(tokens);

        for (List<Token> sentence : sentences) {
            SyntaxParser syntaxParser = factory.getSyntaxParser(sentence);
            ASTNode ast = syntaxParser.syntaxParse(sentence);
            astNodes.add(ast);
        }

        // Semantic analysis
        SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer(astNodes);
        SemanticResult result = semanticAnalyzer.analyze();
        if (!result.getResult()) {
            String stringResult = "";
            for (String message : result.getMessages()) {
                stringResult += message + "\n";
            }
            throw new RuntimeException(stringResult);
        }

        return astNodes;
    }
}