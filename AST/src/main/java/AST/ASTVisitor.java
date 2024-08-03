package AST;

import AST.nodes.AssignationNode;
import AST.nodes.DeclarationNode;
import AST.nodes.LiteralNode;
import AST.nodes.PrintNode;

public interface ASTVisitor<T> {
    T visit(DeclarationNode node);
    T visit(LiteralNode node);
    T visit(PrintNode node);
    T visit(AssignationNode node);
}
