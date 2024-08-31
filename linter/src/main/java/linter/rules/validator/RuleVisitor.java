package linter.rules.validator;

import AST.nodes.ASTNode;
import linter.result.LinterResult;
import linter.rules.CamelCaseRule;
import linter.rules.InputPreventExpressionRule;
import linter.rules.PrintPreventExpressionRule;

public interface RuleVisitor {
    LinterResult visit(CamelCaseRule rule, ASTNode node);
    LinterResult visit(PrintPreventExpressionRule rule, ASTNode node);
    LinterResult visit(InputPreventExpressionRule inputPreventExpressionRule, ASTNode node);
}
