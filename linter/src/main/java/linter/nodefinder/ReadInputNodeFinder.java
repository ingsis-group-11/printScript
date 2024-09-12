package linter.nodefinder;

import ast.AstVisitor;
import ast.nodes.AssignationNode;
import ast.nodes.AstNode;
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
import java.util.ArrayList;
import java.util.List;

public class ReadInputNodeFinder implements AstVisitor<List<ReadInputNode>> {

  @Override
  public List<ReadInputNode> visit(DeclarationNode node) {
    return List.of();
  }

  @Override
  public List<ReadInputNode> visit(LiteralNode node) {
    return List.of();
  }

  @Override
  public List<ReadInputNode> visit(PrintNode node) {
    return new ArrayList<>(node.getExpression().accept(this));
  }

  @Override
  public List<ReadInputNode> visit(AssignationNode node) {
    return new ArrayList<>(node.getExpression().accept(this));
  }

  @Override
  public List<ReadInputNode> visit(OperatorNode node) {
    List<ReadInputNode> readInputNodes = new ArrayList<>();
    readInputNodes.addAll(node.getLeftNode().accept(this));
    readInputNodes.addAll(node.getRightNode().accept(this));
    return readInputNodes;
  }

  @Override
  public List<ReadInputNode> visit(VariableNode node) {
    return List.of();
  }

  @Override
  public List<ReadInputNode> visit(ReassignmentNode node) {
    return new ArrayList<>(node.getExpression().accept(this));
  }

  @Override
  public List<ReadInputNode> visit(EmptyNode node) {
    return List.of();
  }

  @Override
  public List<ReadInputNode> visit(ReadInputNode node) {
    return List.of(node);
  }

  @Override
  public List<ReadInputNode> visit(ReadEnvNode node) {
    return List.of();
  }

  @Override
  public List<ReadInputNode> visit(IfNode node) {
    List<ReadInputNode> readInputNodes = new ArrayList<>();
    readInputNodes.addAll(node.getCondition().accept(this));
    readInputNodes.addAll(node.getIfBlock().accept(this));
    readInputNodes.addAll(node.getElseBlock().accept(this));
    return readInputNodes;
  }

  @Override
  public List<ReadInputNode> visit(BlockNode blockNode) {
    List<ReadInputNode> readInputNodes = new ArrayList<>();
    for (AstNode node : blockNode.getStatements()) {
      readInputNodes.addAll(node.accept(this));
    }
    return readInputNodes;
  }
}
