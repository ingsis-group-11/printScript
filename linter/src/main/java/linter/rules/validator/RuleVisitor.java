package linter.rules.validator;

import AST.nodes.ASTNode;
import linter.result.LinterResult;
import linter.rules.CamelCaseRule;
import linter.rules.PrintPreventExpressionRule;
import linter.rules.ReadInputPreventExpressionRule;

public interface RuleVisitor {
    LinterResult visit(CamelCaseRule rule, ASTNode node);
    LinterResult visit(PrintPreventExpressionRule rule, ASTNode node);
    LinterResult visit(ReadInputPreventExpressionRule readInputPreventExpressionRule, ASTNode node);
}
