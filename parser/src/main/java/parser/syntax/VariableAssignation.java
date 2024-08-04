package parser.syntax;

import AST.nodes.ASTNode;
import AST.nodes.LiteralNode;

import java.util.HashMap;
import java.util.Map;

public class VariableAssignation {
    private final Map<String, LiteralNode> variables = new HashMap<>();
    private static final VariableAssignation instance = new VariableAssignation();


    private VariableAssignation() {
    }

    public static VariableAssignation getInstance() {
        return instance;
    }

    public void addVariable(String name, LiteralNode value) {
        variables.put(name, value);
    }

    public LiteralNode getVariable(String name) {

        return variables.get(name);
    }
}
