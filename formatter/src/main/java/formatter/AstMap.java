package formatter;

import AST.nodes.ASTNode;
import formatter.nodeformatter.AssignationFormatter;
import formatter.nodeformatter.AstTypes;
import formatter.nodeformatter.IfFormatter;
import formatter.nodeformatter.NodeFormatter;
import formatter.nodeformatter.PrintFormatter;
import formatter.nodeformatter.ReadFormatter;
import java.util.HashMap;
import java.util.Map;

public class AstMap {
  Map<AstTypes, NodeFormatter> ASTMap = new HashMap<>();

  public AstMap() {
    ASTMap.put(AstTypes.ASSIGNATION_NODE, new AssignationFormatter());
    ASTMap.put(AstTypes.REASSIGNMENT_NODE, new AssignationFormatter());
    ASTMap.put(AstTypes.PRINT_NODE, new PrintFormatter());
    ASTMap.put(AstTypes.IF_NODE, new IfFormatter());
    ASTMap.put(AstTypes.READ_INPUT_NODE, new ReadFormatter());
    ASTMap.put(AstTypes.READ_ENV_NODE, new ReadFormatter());
  }

  public NodeFormatter getNodeFormatter(ASTNode node) {
    AstTypes type = new AstTypeVisitor().getType(node);
    if (type == null) {
      throw new RuntimeException("Node type not found");
    }
    return ASTMap.get(type);
  }

  public boolean containsNode(ASTNode node) {
    AstTypes type = new AstTypeVisitor().getType(node);
    if (type == null) {
      return false;
    }
    return ASTMap.containsKey(type);
  }
}
