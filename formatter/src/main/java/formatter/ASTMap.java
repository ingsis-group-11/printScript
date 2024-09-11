package formatter;

import AST.nodes.ASTNode;
import formatter.nodeFormatter.*;

import java.util.HashMap;
import java.util.Map;

public class ASTMap {
  Map<ASTTypes, NodeFormatter> ASTMap = new HashMap<>();

  public ASTMap() {
    ASTMap.put(ASTTypes.ASSIGNATION_NODE, new AssignationFormatter());
    ASTMap.put(ASTTypes.REASSIGNMENT_NODE, new DeclarationFormatter());
    ASTMap.put(ASTTypes.PRINT_NODE, new PrintFormatter());
    ASTMap.put(ASTTypes.IF_NODE, new IfFormatter());
  }

  public NodeFormatter getNodeFormatter(ASTNode node) {
    ASTTypes type = new ASTTypeVisitor().getType(node);
    if (type == null) {
      throw new RuntimeException("Node type not found");
    }
    return ASTMap.get(type);
  }

  public boolean containsNode(ASTNode node) {
    ASTTypes type = new ASTTypeVisitor().getType(node);
    if (type == null) {
      return false;
    }
    return ASTMap.containsKey(type);
  }
}


