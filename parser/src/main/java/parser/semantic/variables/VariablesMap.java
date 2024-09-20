package parser.semantic.variables;

import ast.tokens.AstTokenType;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class VariablesMap {

  private final Stack<Map<String, VariableType>> scopes;

  public VariablesMap() {
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

  public void addVariable(String name, AstTokenType variableType, boolean mutable) {
    if (scopes.peek().containsKey(name)) {
      throw new RuntimeException("Variable " + name + " already exists");
    }
    if (new VariableTokenMap().getType(variableType) == null) {
      scopes.peek().put(name, new VariableType(variableType, mutable));
    } else {
      scopes
          .peek()
          .put(name, new VariableType(new VariableTokenMap().getType(variableType), mutable));
    }
  }

  public AstTokenType getVariableType(String name) {
    for (int i = scopes.size() - 1; i >= 0; i--) {
      Map<String, VariableType> scope = scopes.get(i);
      if (scope.containsKey(name)) {
        return scope.get(name).getType();
      }
    }
    throw new RuntimeException("Variable " + name + " does not exists");
  }

  public void updateVariable(String name, AstTokenType tokenType) {
    for (int i = scopes.size() - 1; i >= 0; i--) {
      Map<String, VariableType> scope = scopes.get(i);
      if (scope.containsKey(name)) {
        VariableType variableType = scope.get(name);
        boolean mutable = variableType.isMutable();
        checkVariableType(name, tokenType, variableType.getType());
        if (!mutable) {
          throw new RuntimeException("Variable " + name + " is of type const and can't be updated");
        }
        scope.put(name, new VariableType(tokenType, true));
        return;
      }
    }
  }

  private static void checkVariableType(
      String name, AstTokenType newTokenType, AstTokenType tokenType) {
    if (tokenType != newTokenType) {
      throw new RuntimeException(
          "Variable "
              + name
              + " is of type "
              + tokenType
              + " and can't be updated to type "
              + newTokenType);
    }
  }
}
