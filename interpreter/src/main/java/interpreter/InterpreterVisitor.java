package interpreter;

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
import providers.inputprovider.InputProvider;
import providers.printprovider.PrintProvider;
import ast.tokens.AstTokenType;
import variablemap.TypeValidator;
import variablemap.VariableMap;

public class InterpreterVisitor implements AstVisitor<Void> {
  private final VariableMap variableMap;
  private final LiteralTransformer literalTransformer;
  private final PrintProvider printProvider;
  private final InputProvider inputProvider;

  public InterpreterVisitor(
      VariableMap variableMap, PrintProvider printProvider, InputProvider inputProvider) {
    this.variableMap = variableMap;
    this.printProvider = printProvider;
    this.literalTransformer = new LiteralTransformer(variableMap, inputProvider);
    this.inputProvider = inputProvider;
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
    LiteralNode expression = node.getExpression().accept(literalTransformer);
    String message = expression.getValue();
    printProvider.print(message);
    return null;
  }

  @Override
  public Void visit(AssignationNode node) {
    LiteralNode expression = node.getExpression().accept(literalTransformer);
    AstTokenType variableType = node.getDeclaration().getTypeToken().getType();
    AstTokenType expressionType = expression.getType();
    boolean mutable = node.getDeclaration().isMutable();
    if (TypeValidator.validateType(variableType, expressionType)) {
      variableMap.addVariable(node.getDeclaration().getNameToken().getValue(), expression, mutable);
      return null;
    }
    throw new RuntimeException(
        "Variable "
            + node.getDeclaration().getNameToken().getValue()
            + " is of type "
            + variableType
            + " and cannot be assigned to type "
            + expressionType);
  }

  @Override
  public Void visit(OperatorNode node) {
    return null;
  }

  @Override
  public Void visit(VariableNode node) {
    return null;
  }

  @Override
  public Void visit(ReassignmentNode node) {
    LiteralNode expression = node.getExpression().accept(literalTransformer);
    LiteralNode variable = variableMap.getVariable(node.getVariableNode().getValue());
    AstTokenType variableType = variable.getType();
    if (variableType.equals(expression.getType())) {
      variableMap.updateVariable(node.getVariableNode().getValue(), expression);
    } else {
      throw new RuntimeException(
          "Variable "
              + node.getVariableNode().getValue()
              + " is of type "
              + variableType
              + " and cannot be reassigned to type "
              + expression.getType());
    }
    return null;
  }

  @Override
  public Void visit(EmptyNode emptyNode) {
    return null;
  }

  @Override
  public Void visit(ReadInputNode node) {
    LiteralNode expression = node.getExpression().accept(literalTransformer);
    String message = expression.getValue();
    inputProvider.getInput(message);
    return null;
  }

  @Override
  public Void visit(ReadEnvNode node) {
    LiteralNode expression = node.getExpression().accept(literalTransformer);
    String variableName = expression.getValue();
    String input = System.getenv(variableName);
    if (input == null) {
      throw new RuntimeException("Environment variable " + variableName + " not found");
    }
    return null;
  }

  @Override
  public Void visit(IfNode ifNode) {

    AstNode conditionNode = ifNode.getCondition();
    LiteralNode conditionLiteral = conditionNode.accept(literalTransformer);

    if (conditionLiteral.getType() != AstTokenType.BOOLEAN) {
      throw new RuntimeException("Condition in if statement must be a Boolean");
    }

    boolean conditionValue = Boolean.parseBoolean(conditionLiteral.getValue());

    if (conditionValue) {
      ifNode.getIfBlock().accept(this);
    } else {
      ifNode.getElseBlock().accept(this);
    }

    return null;
  }

  @Override
  public Void visit(BlockNode blockNode) {
    variableMap.enterScope();
    for (AstNode node : blockNode.getStatements()) {
      node.accept(this);
    }
    variableMap.exitScope();
    return null;
  }
}
