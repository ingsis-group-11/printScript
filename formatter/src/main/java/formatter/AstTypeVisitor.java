package formatter;

import AST.nodes.ASTNode;
import AST.nodes.AssignationNode;
import AST.nodes.IfNode;
import AST.nodes.PrintNode;
import AST.nodes.ReadEnvNode;
import AST.nodes.ReadInputNode;
import AST.nodes.ReassignmentNode;
import formatter.nodeformatter.AstTypes;

public class AstTypeVisitor {

  public AstTypes getType(ASTNode node) {
    if (node instanceof AssignationNode) {
      return AstTypes.ASSIGNATION_NODE;
    }
    if (node instanceof ReassignmentNode) {
      return AstTypes.REASSIGNMENT_NODE;
    }
    if (node instanceof PrintNode) {
      return AstTypes.PRINT_NODE;
    }
    if (node instanceof IfNode) {
      return AstTypes.IF_NODE;
    }
    if (node instanceof ReadInputNode) {
      return AstTypes.READ_INPUT_NODE;
    }
    if (node instanceof ReadEnvNode) {
      return AstTypes.READ_ENV_NODE;
    }
    return null;
  }
}
