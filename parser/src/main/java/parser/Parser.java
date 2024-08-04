package parser;

import AST.nodes.ASTNode;
import parser.semantic.SemanticAnalyzer;
import parser.semantic.SemanticResult;
import parser.syntax.AssignationSyntaxParser;
import parser.syntax.SyntaxParser;
import token.Token;

import java.util.List;

public class Parser {
    public List<ASTNode> parse(List<Token> tokens) {
        //Syntax parse and AST generation
        SyntaxParser syntaxParser = new AssignationSyntaxParser();
        ASTNode ast = syntaxParser.syntaxParse(tokens);

        //Semantic analysis
        SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer(List.of(ast));
        SemanticResult result = semanticAnalyzer.analyze();
        if(!result.getResult()) {
            String stringResult = "";
            for(String message : result.getMessages()) {
                stringResult += message + "\n";
            }
            throw new RuntimeException(stringResult);
        }

        return List.of(ast);
    }
}
