package formatter;

import AST.nodes.*;
import formatter.nodeFormatter.ASTTypes;

public class ASTTypeVisitor{

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
    return null;
  }
}
