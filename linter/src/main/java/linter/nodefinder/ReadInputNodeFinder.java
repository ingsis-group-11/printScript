package linter.nodefinder;

import AST.ASTVisitor;
import AST.nodes.ASTNode;
import AST.nodes.AssignationNode;
import AST.nodes.BlockNode;
import AST.nodes.DeclarationNode;
import AST.nodes.EmptyNode;
import AST.nodes.IfNode;
import AST.nodes.LiteralNode;
import AST.nodes.OperatorNode;
import AST.nodes.PrintNode;
import AST.nodes.ReadEnvNode;
import AST.nodes.ReadInputNode;
import AST.nodes.ReassignmentNode;
import AST.nodes.VariableNode;
import java.util.ArrayList;
import java.util.List;

public class ReadInputNodeFinder implements ASTVisitor<List<ReadInputNode>> {

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
    for (ASTNode node : blockNode.getStatements()) {
      readInputNodes.addAll(node.accept(this));
    }
    return readInputNodes;
  }
}
