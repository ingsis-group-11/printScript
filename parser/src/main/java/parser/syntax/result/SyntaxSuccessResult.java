package parser.syntax.result;

import AST.nodes.ASTNode;
import java.util.List;

public class SyntaxSuccessResult implements SyntaxResult {
    private final ASTNode astNode;

    public SyntaxSuccessResult(ASTNode astNode) {
        this.astNode = astNode;
    }

    @Override
    public boolean hasErrors() {
        return false;
    }

    @Override
    public List<String> messages() {
        return List.of();
    }

    public ASTNode getAstNode() {
        return astNode;
    }
}