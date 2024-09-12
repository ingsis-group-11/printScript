package linter.nodeFinder;

import AST.ASTVisitor;
import AST.nodes.*;
import java.util.ArrayList;
import java.util.List;

public class ReadInputNodeFinder implements ASTVisitor<Void> {
  private List<ReadInputNode> readInputNodes = new ArrayList<>();

  public List<ReadInputNode> getReadInputNodes() {
    return readInputNodes;
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
    node.getExpression().accept(this);
    return null;
  }

  @Override
  public Void visit(AssignationNode node) {
    node.getExpression().accept(this);
    return null;
  }

  @Override
  public Void visit(OperatorNode node) {
    node.getLeftNode().accept(this);
    node.getRightNode().accept(this);
    return null;
  }

  @Override
  public Void visit(VariableNode node) {
    return null;
  }

  @Override
  public Void visit(ReassignmentNode node) {
    node.getExpression().accept(this);
    return null;
  }

  @Override
  public Void visit(EmptyNode emptyNode) {
    return null;
  }

  @Override
  public Void visit(ReadInputNode readInputNode) {
    readInputNodes.add(readInputNode);
    return null;
  }

  @Override
  public Void visit(ReadEnvNode readEnvNode) {
    return null;
  }

  @Override
  public Void visit(IfNode ifNode) {
    ifNode.getCondition().accept(this);
    ifNode.getIfBlock().accept(this);
    ifNode.getElseBlock().accept(this);
    return null;
  }

  @Override
  public Void visit(BlockNode blockNode) {
    for (ASTNode node : blockNode.getStatements()) {
      node.accept(this);
    }
    return null;
  }
}
