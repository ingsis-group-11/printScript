package parser;

import AST.ASTVisitor;
import AST.nodes.AssignationNode;
import AST.nodes.DeclarationNode;
import AST.nodes.LiteralNode;
import AST.nodes.PrintNode;

public class SemanticVisitor implements ASTVisitor {

    @Override
    public void visit(DeclarationNode node) {

    }

    @Override
    public void visit(LiteralNode node) {
        //Nothing
    }

    @Override
    public void visit(PrintNode node) {
        //Nothing
    }

    @Override
    public void visit(AssignationNode node) {

    }
}
