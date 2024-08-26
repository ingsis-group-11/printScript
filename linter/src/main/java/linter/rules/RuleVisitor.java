package linter.rules;

import AST.nodes.ASTNode;
import linter.result.LinterResult;

import java.util.List;

public interface RuleVisitor {
    LinterResult visit(CamelCaseRule rule, List<ASTNode> nodes);
    LinterResult visit(PrintPreventExpressionRule rule, List<ASTNode> nodes);
}
