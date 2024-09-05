package interpreter;

import AST.ASTVisitor;
import AST.nodes.*;
import providers.inputProvider.InputProvider;
import providers.printProvider.PrintProvider;
import token.TokenType;
import variableMap.VariableMap;

public class InterpreterVisitor implements ASTVisitor<Void> {
  private final VariableMap variableMap;
  private final LiteralTransformer literalTransformer;
  private final PrintProvider printProvider;
  private final InputProvider inputProvider;

  public InterpreterVisitor(VariableMap variableMap, PrintProvider printProvider, InputProvider inputProvider){
    this.variableMap = variableMap;
    this.printProvider = printProvider;
    this.literalTransformer = new LiteralTransformer(variableMap, inputProvider);
    this.inputProvider = inputProvider;
  }

  @Override
  public Void visit(DeclarationNode node) {
    return null;
  }

  @Override
  public Void visit(LiteralNode node) {
    return null;
  }

  @Override
  public Void visit(PrintNode node) {
    LiteralNode expression = node.getExpression().accept(literalTransformer);
    String message = expression.getValue();
    printProvider.print(message);
    return null;
  }

  @Override
  public Void visit(AssignationNode node) {
    LiteralNode expression = node.getExpression().accept(literalTransformer);
    // boolean mutable = node.getDeclaration().isMutable();
    variableMap.addVariable(node.getDeclaration().getNameToken().getValue(), expression, true);
    return null;
  }

  @Override
  public Void visit(OperatorNode node) {
    return null;
  }

  @Override
  public Void visit(VariableNode node) {
    return null;
  }

  @Override
  public Void visit(ReassignmentNode node) {
    LiteralNode expression = node.getExpression().accept(literalTransformer);
    LiteralNode variable = variableMap.getVariable(node.getVariableNode().getValue());
    TokenType variableType = variable.getType();
    if (variableType.equals(expression.getType())) {
      variableMap.updateVariable(node.getVariableNode().getValue(), expression);
    } else {
      throw new RuntimeException("Variable " + node.getVariableNode().getValue() + " is of type " + variableType +
              " and cannot be reassigned to type " + expression.getType());
    }
    return null;
  }

  @Override
  public Void visit(EmptyNode emptyNode) {
    return null;
  }

  @Override
  public Void visit(ReadInputNode node) {
    LiteralNode expression = node.getExpression().accept(literalTransformer);
    String message = expression.getValue();
    inputProvider.getInput(message);
    return null;
  }
}
