package parser.semantic;

import java.util.ArrayList;
import java.util.List;

import AST.nodes.*;
import org.junit.jupiter.api.Test;
import parser.semantic.result.SemanticResult;
import token.TokenType;
import token.ValueToken;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SemanticAssignationTest {
  @Test
  public void validStringAssignationTest(){
    List<ASTNode> trees = new ArrayList<>();
    ASTNode assignmentNode =
          new AssignationNode(
                  new DeclarationNode(
                          new ValueToken(TokenType.STRING_TYPE, "string", 10, 0),
                          new ValueToken(TokenType.IDENTIFIER, "name", 4, 0),
                          1,
                          0),
                  new LiteralNode(new ValueToken(TokenType.STRING, "Olive", 19, 0)),
                  1,
                  1);
    trees.add(assignmentNode);
    SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
    SemanticResult semanticError = semanticAnalyzer.analyze(trees);
    assertFalse(semanticError.hasErrors());
  }

  @Test
  public void validNumberAssignationTest(){
    List<ASTNode> trees = new ArrayList<>();
    ASTNode assignmentNode =
            new AssignationNode(
                    new DeclarationNode(
                            new ValueToken(TokenType.NUMBER_TYPE, "number", 10, 0),
                            new ValueToken(TokenType.IDENTIFIER, "num", 4, 0),
                            1,
                            0),
                    new LiteralNode(new ValueToken(TokenType.NUMBER, "10", 19, 0)),
                    1,
                    1);
    trees.add(assignmentNode);
    SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
    SemanticResult semanticError = semanticAnalyzer.analyze(trees);
    assertFalse(semanticError.hasErrors());
  }

  @Test
  public void invalidStringAssignationTest(){
    List<ASTNode> trees = new ArrayList<>();
    ASTNode assignmentNode =
            new AssignationNode(
                    new DeclarationNode(
                            new ValueToken(TokenType.NUMBER_TYPE, "number", 10, 0),
                            new ValueToken(TokenType.IDENTIFIER, "name", 4, 0),
                            1,
                            0),
                    new LiteralNode(new ValueToken(TokenType.STRING, "Olive", 19, 0)),
                    1,
                    1);
    trees.add(assignmentNode);
    SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
    SemanticResult semanticError = semanticAnalyzer.analyze(trees);
    assertTrue(semanticError.hasErrors());
  }

  @Test
  public void invalidNumberAssignationTest(){
    List<ASTNode> trees = new ArrayList<>();
    ASTNode assignmentNode =
            new AssignationNode(
                    new DeclarationNode(
                            new ValueToken(TokenType.STRING_TYPE, "string", 10, 0),
                            new ValueToken(TokenType.IDENTIFIER, "num", 4, 0),
                            1,
                            0),
                    new LiteralNode(new ValueToken(TokenType.NUMBER, "10", 19, 0)),
                    1,
                    1);
    trees.add(assignmentNode);
    SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
    SemanticResult semanticError = semanticAnalyzer.analyze(trees);
    assertTrue(semanticError.hasErrors());
  }

  @Test
  public void validVariableAssignationTest(){
    List<ASTNode> trees = new ArrayList<>();
    ASTNode assignmentNode =
            new AssignationNode(
                    new DeclarationNode(
                            new ValueToken(TokenType.NUMBER_TYPE, "number", 10, 0),
                            new ValueToken(TokenType.IDENTIFIER, "name", 4, 0),
                            1,
                            0),
                    new LiteralNode(new ValueToken(TokenType.IDENTIFIER, "a", 19, 0)),
                    1,
                    1);
    trees.add(assignmentNode);
    SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
    SemanticResult semanticError = semanticAnalyzer.analyze(trees);
    assertFalse(semanticError.hasErrors());
  }
}
