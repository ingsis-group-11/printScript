package parser;

import AST.nodes.ASTNode;

import java.util.List;

public class SemanticAnalyzer {
    private final List<ASTNode> ASTTrees;

    public SemanticAnalyzer(List<ASTNode> ASTTrees) {
        this.ASTTrees = ASTTrees;
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
        // Do something with the tree
    }
}
