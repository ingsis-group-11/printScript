package parser.semantic;

import AST.nodes.ASTNode;

import java.util.List;

public class SemanticAnalyzer {
    private final List<ASTNode> ASTTrees;
    private final SemanticVisitor semanticVisitor;

    public SemanticAnalyzer(List<ASTNode> ASTTrees) {
        this.ASTTrees = ASTTrees;
        this.semanticVisitor = new SemanticVisitor();
    }

    public SemanticResult analyze() {
        for (ASTNode tree : ASTTrees) {
            boolean result = tree.accept(semanticVisitor);
            if (!result) {
                return new SemanticResult(false, List.of("Semantic error in " + tree.getLine() + ":" + tree.getColumn()));
            }
        }
        return new SemanticResult(true, List.of("Semantic analysis completed successfully"));
    }
}
