package formatter;

import ast.nodes.AssignationNode;
import ast.nodes.AstNode;
import ast.nodes.IfNode;
import ast.nodes.PrintNode;
import ast.nodes.ReadEnvNode;
import ast.nodes.ReadInputNode;
import ast.nodes.ReassignmentNode;
import formatter.nodeformatter.AstTypes;

public class AstTypeVisitor {

  public AstTypes getType(AstNode node) {
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
