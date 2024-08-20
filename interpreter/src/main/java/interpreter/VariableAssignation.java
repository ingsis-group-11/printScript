package interpreter;

import AST.nodes.LiteralNode;

import java.util.HashMap;
import java.util.Map;

public class VariableAssignation {
    private final Map<String, LiteralNode> variables = new HashMap<>();



    public VariableAssignation() {
    }


    public void addVariable(String name, LiteralNode value) {
        if (!variables.containsKey(name)) {
            variables.put(name, value);
        } else {
            throw new RuntimeException("Variable " + name + " already exists");
        }
    }

    public LiteralNode getVariable(String name) {
        if (variables.containsKey(name)) {
            return variables.get(name);
        }
        throw new RuntimeException("Variable " + name + " not found");
    }

    public void updateVariable(String name, LiteralNode value) {

        if (variables.containsKey(name)) {

            if (validateType(name, value)) {
                throw new RuntimeException("Variable " + name + " is of type " + variables.get(name).getType() + " and cannot be assigned to a " + value.getType());
            }
            else {
                variables.put(name, value);
            }

        } else {
            throw new RuntimeException("Variable " + name + " not declared yet" );
        }
    }

    private Boolean validateType(String name, LiteralNode value) {
        return variables.get(name).getType() != value.getType();
    }
}
