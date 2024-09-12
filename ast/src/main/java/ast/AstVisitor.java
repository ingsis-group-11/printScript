package ast;

import ast.nodes.AssignationNode;
import ast.nodes.BlockNode;
import ast.nodes.DeclarationNode;
import ast.nodes.EmptyNode;
import ast.nodes.IfNode;
import ast.nodes.LiteralNode;
import ast.nodes.OperatorNode;
import ast.nodes.PrintNode;
import ast.nodes.ReadEnvNode;
import ast.nodes.ReadInputNode;
import ast.nodes.ReassignmentNode;
import ast.nodes.VariableNode;

public interface AstVisitor<T> {
  T visit(DeclarationNode node);

  T visit(LiteralNode node);

  T visit(PrintNode node);

  T visit(AssignationNode node);

  T visit(OperatorNode node);

  T visit(VariableNode node);

  T visit(ReassignmentNode node);

  T visit(EmptyNode emptyNode);

  T visit(ReadInputNode readInputNode);

  T visit(ReadEnvNode readEnvNode);

  T visit(IfNode ifNode);

  T visit(BlockNode blockNode);
}
