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

    public boolean analyze() {
        for (ASTNode tree : ASTTrees) {
            boolean result = analyzeTree(tree);
            if (!result) {
                return false;
            }
        }
        return true;
    }

    private boolean analyzeTree(ASTNode tree) {
        return tree.accept(semanticVisitor);
    }
}
