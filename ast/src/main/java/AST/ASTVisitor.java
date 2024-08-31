package AST;

import AST.nodes.*;

public interface ASTVisitor<T> {
  T visit(DeclarationNode node);

  T visit(LiteralNode node);

  T visit(PrintNode node);

  T visit(AssignationNode node);

  T visit(OperatorNode node);

  T visit(VariableNode node);

  T visit(ReassignmentNode node);

  T visit(ReadInputNode node);
}
