package formatter;

import AST.ASTVisitor;
import AST.nodes.*;
import formatter.nodeFormatter.ASTTypes;

public class ASTTypeVisitor implements ASTVisitor<ASTTypes> {
  @Override
  public ASTTypes visit(AssignationNode node) {
    return ASTTypes.ASSIGNATION_NODE;
  }

  @Override
  public ASTTypes visit(OperatorNode node) {
    return null;
  }

  @Override
  public ASTTypes visit(VariableNode node) {
    return null;
  }

  @Override
  public ASTTypes visit(ReassignmentNode node) {
    return ASTTypes.REASSIGNMENT_NODE;
  }

  @Override
  public ASTTypes visit(EmptyNode emptyNode) {
    return null;
  }

  @Override
  public ASTTypes visit(ReadInputNode readInputNode) {
    return null;
  }

  @Override
  public ASTTypes visit(ReadEnvNode readEnvNode) {
    return null;
  }

  @Override
  public ASTTypes visit(DeclarationNode node) {
    return null;
  }

  @Override
  public ASTTypes visit(LiteralNode node) {
    return null;
  }

  @Override
  public ASTTypes visit(PrintNode node) {
    return ASTTypes.PRINT_NODE;
  }

  @Override
  public ASTTypes visit(IfNode node) {
    return ASTTypes.IF_NODE;
  }

  @Override
  public ASTTypes visit(BlockNode blockNode) {
    return null;
  }
}
