package parser;

import AST.ASTVisitor;
import AST.nodes.AssignationNode;
import AST.nodes.DeclarationNode;
import AST.nodes.LiteralNode;
import AST.nodes.PrintNode;

public class SemanticVisitor implements ASTVisitor<Boolean> {

    @Override
    public Boolean visit(DeclarationNode node) {
        return true;
    }

    @Override
    public Boolean visit(LiteralNode node) {
        return true;
    }

    @Override
    public Boolean visit(PrintNode node) {
        return true;
    }

    @Override
    public Boolean visit(AssignationNode node) {
        return true;
    }
}
