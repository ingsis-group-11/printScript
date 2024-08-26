package linter.rules;

import AST.nodes.ASTNode;
import linter.result.LinterResult;

import java.util.List;

public interface Rule {
    LinterResult accept(RuleVisitor visitor, List<ASTNode> node);
}
