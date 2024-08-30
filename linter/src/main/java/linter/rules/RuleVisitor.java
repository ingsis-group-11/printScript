package linter.rules;

import AST.nodes.ASTNode;
import linter.result.LinterResult;

public interface RuleVisitor {
    LinterResult visit(CamelCaseRule rule, ASTNode node);
    LinterResult visit(PrintPreventExpressionRule rule, ASTNode node);
}
