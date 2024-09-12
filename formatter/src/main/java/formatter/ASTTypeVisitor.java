package formatter;

import AST.nodes.*;
import formatter.nodeFormatter.ASTTypes;

public class ASTTypeVisitor {

  public ASTTypes getType(ASTNode node) {
    if (node instanceof AssignationNode) {
      return ASTTypes.ASSIGNATION_NODE;
    }
    if (node instanceof ReassignmentNode) {
      return ASTTypes.REASSIGNMENT_NODE;
    }
    if (node instanceof PrintNode) {
      return ASTTypes.PRINT_NODE;
    }
    if (node instanceof IfNode) {
      return ASTTypes.IF_NODE;
    }
    if (node instanceof ReadInputNode) {
      return ASTTypes.READ_INPUT_NODE;
    }
    if (node instanceof ReadEnvNode) {
      return ASTTypes.READ_ENV_NODE;
    }
    return null;
  }
}
