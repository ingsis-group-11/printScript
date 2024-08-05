package parser.syntax;

import AST.nodes.ASTNode;
import AST.nodes.AssignationNode;
import AST.nodes.DeclarationNode;
import AST.nodes.LiteralNode;
import token.Token;
import token.TokenType;
import token.ValueToken;

import java.util.List;
import java.util.Iterator;

public class AssignationSyntaxParser implements SyntaxParser {

    @Override
    public ASTNode syntaxParse(List<Token> tokens) {
        Iterator<Token> iterator = tokens.iterator();
        return parseAssignation(iterator);
    }

    private ASTNode parseAssignation(Iterator<Token> iterator) {
        if (iterator.hasNext()) {
            Token token = iterator.next();
            if (token.getType() == TokenType.LET_KEYWORD) {
                DeclarationNode declarationNode = parseDeclaration(iterator);
                if (iterator.hasNext()) {
                    token = iterator.next();
                    if (token.getType() == TokenType.ASSIGN) {
                        LiteralNode literalNode = parseLiteral(iterator);
                        VariableAssignation map = VariableAssignation.getInstance();
                        map.addVariable(declarationNode.getNameToken().getValue(), literalNode);
                        return new AssignationNode(declarationNode, literalNode, declarationNode.getLine(), declarationNode.getColumn());
                    }
                }
            }
        }
        throw new IllegalArgumentException("Invalid assignation");
    }

    private DeclarationNode parseDeclaration(Iterator<Token> iterator) {
        Token nameToken = iterator.next();
        Token typeToken = iterator.next();

        if (typeToken instanceof ValueToken && nameToken instanceof ValueToken) {
            return new DeclarationNode(typeToken, nameToken, typeToken.getLine(), typeToken.getColumn());
        }

        throw new IllegalArgumentException("Invalid declaration");
    }

    private LiteralNode parseLiteral(Iterator<Token> iterator) {
        Token literalToken = iterator.next();

        if (literalToken instanceof ValueToken) {
            return new LiteralNode(literalToken);
        }

        throw new IllegalArgumentException("Invalid literal");
    }
}