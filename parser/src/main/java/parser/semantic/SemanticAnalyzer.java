package parser.semantic;

import AST.nodes.ASTNode;
import parser.semantic.result.SemanticErrorResult;
import parser.semantic.result.SemanticResult;
import parser.semantic.result.SemanticSuccessResult;

import java.util.ArrayList;
import java.util.List;

public class SemanticAnalyzer {
    private final SemanticVisitor semanticVisitor;

    public SemanticAnalyzer() {
        this.semanticVisitor = new SemanticVisitor();
    }

    public SemanticResult analyze(List<ASTNode> ASTTrees) {
        List<String> messages = new ArrayList<>();
        for (ASTNode tree : ASTTrees) {
            SemanticResult result = tree.accept(semanticVisitor);
            if (result.hasErrors()) {
                messages.addAll(result.messages());
            }
        }
        if (messages.isEmpty()) {
            return new SemanticSuccessResult();
        }
        return new SemanticErrorResult(messages);
    }
}
