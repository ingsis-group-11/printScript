package parser.semantic;

import AST.nodes.ASTNode;

import java.util.ArrayList;
import java.util.List;

public class SemanticAnalyzer {
    private final List<ASTNode> ASTTrees;
    private final SemanticVisitor semanticVisitor;

    public SemanticAnalyzer(List<ASTNode> ASTTrees) {
        this.ASTTrees = ASTTrees;
        this.semanticVisitor = new SemanticVisitor();
    }

    public SemanticResult analyze() {
        List<String> messages = new ArrayList<>();
        for (ASTNode tree : ASTTrees) {
            boolean result = tree.accept(semanticVisitor);
            if (!result) {
                messages.add("Semantic error in "+ tree.getLine() + ":" + tree.getColumn());
            }
        }
        return new SemanticResult(!messages.isEmpty(), messages.isEmpty() ? List.of("No semantic errors") : messages);
    }
}
