package formatter;

import ast.nodes.AstNode;
import formatter.nodeformatter.AssignationFormatter;
import formatter.nodeformatter.AstTypes;
import formatter.nodeformatter.IfFormatter;
import formatter.nodeformatter.NodeFormatter;
import formatter.nodeformatter.PrintFormatter;
import formatter.nodeformatter.ReadFormatter;
import java.util.HashMap;
import java.util.Map;

public class AstMap {
  Map<AstTypes, NodeFormatter> astMap = new HashMap<>();

  public AstMap() {
    astMap.put(AstTypes.ASSIGNATION_NODE, new AssignationFormatter());
    astMap.put(AstTypes.REASSIGNMENT_NODE, new AssignationFormatter());
    astMap.put(AstTypes.PRINT_NODE, new PrintFormatter());
    astMap.put(AstTypes.IF_NODE, new IfFormatter());
    astMap.put(AstTypes.READ_INPUT_NODE, new ReadFormatter());
    astMap.put(AstTypes.READ_ENV_NODE, new ReadFormatter());
  }

  public NodeFormatter getNodeFormatter(AstNode node) {
    AstTypes type = new AstTypeVisitor().getType(node);
    if (type == null) {
      throw new RuntimeException("Node type not found");
    }
    return astMap.get(type);
  }

  public boolean containsNode(AstNode node) {
    AstTypes type = new AstTypeVisitor().getType(node);
    if (type == null) {
      return false;
    }
    return astMap.containsKey(type);
  }
}
