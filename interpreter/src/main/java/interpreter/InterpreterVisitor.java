package interpreter;

import AST.ASTVisitor;
import AST.nodes.*;
import interpreter.providers.printProvider.PrintProvider;
import token.TokenType;

public class InterpreterVisitor implements ASTVisitor<Void> {
  private final VariableAssignation variableAssignation;
  private final LiteralTransformer literalTransformer;
  private final PrintProvider printProvider;


  public InterpreterVisitor(VariableAssignation variableAssignation, PrintProvider printProvider){
    this.variableAssignation = variableAssignation;
    this.printProvider = printProvider;
    this.literalTransformer = new LiteralTransformer(variableAssignation);
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
    variableAssignation.addVariable(node.getDeclaration().getNameToken().getValue(), expression);
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
    LiteralNode variable = variableAssignation.getVariable(node.getVariableNode().getValue());
    TokenType variableType = variable.getType();
    if (variableType.equals(expression.getType())) {
      variableAssignation.updateVariable(node.getVariableNode().getValue(), expression);
    } else {
      throw new RuntimeException("Variable " + node.getVariableNode().getValue() + " is of type " + variableType +
              " and cannot be reassigned to type " + expression.getType());
    }
    return null;
  }
}
