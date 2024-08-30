package linter.rules;

import AST.nodes.ASTNode;
import linter.result.LinterResult;

public interface Rule {
    LinterResult accept(RuleVisitor visitor, ASTNode node);
}
