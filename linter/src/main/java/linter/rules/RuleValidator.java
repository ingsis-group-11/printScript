package linter.rules;

import AST.ExpressionTypeVisitor;
import AST.nodes.ASTNode;
import AST.nodes.AssignationNode;
import linter.result.FailedLinterResult;
import linter.result.LinterResult;
import linter.result.SuccessLinterResult;
import token.TokenType;

import java.util.ArrayList;
import java.util.List;

public class RuleValidator implements RuleVisitor{

    @Override
    public LinterResult visit(CamelCaseRule rule, List<ASTNode> nodes) {
        List<String> errors = new ArrayList<>();
        for (ASTNode node : nodes) {
            if (node instanceof AssignationNode){
                AssignationNode assignationNode = (AssignationNode) node;
                String variableName = assignationNode.getDeclaration().getNameToken().getValue();
                if (!isCamelCase(variableName)) {
                    int line = assignationNode.getDeclaration().getNameToken().getLine();
                    int column = assignationNode.getDeclaration().getNameToken().getColumn();
                    errors.add("Variable " + variableName + " is not in camelCase format at " + line + ":" + column);
                }
            }
        }
        return errors.isEmpty() ? new SuccessLinterResult() : new FailedLinterResult(errors);
    }

    private boolean isCamelCase(String str) {
        String regex = "^[a-z]+([A-Z][a-z]+)*$";
        return str.matches(regex);
    }

    @Override
    public LinterResult visit(PrintPreventExpressionRule rule, List<ASTNode> nodes) {
        return null;
    }
}
