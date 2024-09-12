package variableMap;

import AST.nodes.LiteralNode;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class VariableMap {

  private final Stack<Map<String, VariableAssignation>> scopes;

  public VariableMap() {
    scopes = new Stack<>();
    scopes.push(new HashMap<>());
  }

  public void enterScope() {
    scopes.push(new HashMap<>());
  }

  public void exitScope() {
    if (scopes.size() > 1) {
      scopes.pop();
    } else {
      throw new IllegalStateException("You can't exit the global scope");
    }
  }

  public void addVariable(String name, LiteralNode value, boolean mutable) {
    VariableAssignation variable = new VariableAssignation(value, mutable);
    if (scopes.peek().containsKey(name)) {
      throw new RuntimeException("Variable " + name + " already exists");
    }
    scopes.peek().put(name, variable);
  }

  public LiteralNode getVariable(String name) {
    for (int i = scopes.size() - 1; i >= 0; i--) {
      Map<String, VariableAssignation> scope = scopes.get(i);
      if (scope.containsKey(name)) {
        return scope.get(name).getLiteralNode();
      }
    }
    throw new RuntimeException("Variable " + name + " does not exists");
  }

  public void updateVariable(String name, LiteralNode value) {
    for (int i = scopes.size() - 1; i >= 0; i--) {
      Map<String, VariableAssignation> scope = scopes.get(i);
      if (scope.containsKey(name)) {
        VariableAssignation variable = scope.get(name);
        boolean mutable = variable.isMutable();
        if (variable.getType() != value.getType()) {
          throw new RuntimeException(
              "Variable "
                  + name
                  + " is of type "
                  + variable.getType()
                  + " and can't be updated to type "
                  + value.getType());
        }
        if (!mutable) {
          throw new RuntimeException("Variable " + name + " is of type const and can't be updated");
        }
        scope.put(name, new VariableAssignation(value, mutable));
        return;
      }
    }
  }
}
