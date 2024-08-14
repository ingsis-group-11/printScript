package parser;

import AST.nodes.ASTNode;
import parser.semantic.SemanticAnalyzer;
import parser.semantic.result.SemanticResult;
import parser.syntax.SyntaxParser;
import parser.syntax.SyntaxParserFactory;
import token.Token;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    public List<ASTNode> parse(List<Token> tokens) {
        // Syntax analysis
        List<ASTNode> astNodes = syntaxParser(tokens);
        // Semantic analysis
        semanticParser(astNodes);

        return astNodes;
    }

    private void semanticParser(List<ASTNode> astNodes) {
        SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
        SemanticResult result = semanticAnalyzer.analyze(astNodes);
        resolveSemanticErrors(result);
    }

    private void resolveSemanticErrors(SemanticResult result) {
        if (result.hasErrors()) {
            String stringResult = "";
            for (String message : result.messages()) {
                stringResult += message + "\n";
            }
            throw new RuntimeException(stringResult);
        }
    }

    private List<ASTNode> syntaxParser(List<Token> tokens) {
        List<List<Token>> sentences = TokenSplitter.splitBySemicolons(tokens);

        return createTrees(sentences);
    }

    private List<ASTNode> createTrees(List<List<Token>> sentences) {
        List<ASTNode> astNodes = new ArrayList<>();
        SyntaxParserFactory factory = new SyntaxParserFactory();

        for (List<Token> sentence : sentences) {
            SyntaxParser syntaxParser = factory.getSyntaxParser(sentence);
            ASTNode ast = syntaxParser.syntaxParse(sentence);
            astNodes.add(ast);
        }

        return astNodes;
    }
}