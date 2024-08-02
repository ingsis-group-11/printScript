package AST;

import AST.nodes.AssignationNode;
import AST.nodes.DeclarationNode;
import AST.nodes.LiteralNode;
import AST.nodes.PrintNode;

public interface ASTVisitor {
    void visit(DeclarationNode node);
    void visit(LiteralNode node);
    void visit(PrintNode node);
    void visit(AssignationNode node);
}
