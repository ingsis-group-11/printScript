package linter.rules;

import AST.nodes.ASTNode;
import linter.result.LinterResult;

public class CamelCaseRule implements Rule {
    @Override
    public LinterResult accept(RuleVisitor visitor, ASTNode node) {
        return visitor.visit(this, node);
    }
}
