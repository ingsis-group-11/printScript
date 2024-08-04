package parser.syntax;

import AST.nodes.ASTNode;
import AST.nodes.LiteralNode;
import AST.nodes.PrintNode;
import token.Token;
import token.TokenType;
import token.ValueToken;

import java.util.List;
import java.util.Iterator;

public class PrintSyntaxParser implements SyntaxParser {

    @Override
    public ASTNode syntaxParse(List<Token> tokens) {
        Iterator<Token> iterator = tokens.iterator();
        return parsePrint(iterator);
    }

    private ASTNode parsePrint(Iterator<Token> iterator) {
        if (iterator.hasNext()) {
            Token token = iterator.next();
            if (token.getType() == TokenType.PRINT_KEYWORD) {
                Token valueToken = iterator.next();
                if (valueToken instanceof ValueToken && valueToken.getType() == TokenType.IDENTIFIER) {
                    VariableAssignation map = VariableAssignation.getInstance();
                    LiteralNode node = map.getVariable(valueToken.getValue());
                    if (node == null) {
                        throw new IllegalArgumentException("Variable not found at line: " + valueToken.getLine() + " column: " + valueToken.getColumn());
                    }
                    return new PrintNode(node, valueToken.getLine(), valueToken.getColumn());
                }
                else if (valueToken.getType() == TokenType.STRING || valueToken.getType() == TokenType.NUMBER) {
                    return new PrintNode(new LiteralNode(valueToken), valueToken.getLine(), valueToken.getColumn());
                }
                else {
                    throw new IllegalArgumentException("Invalid print");
                }
            }
        }
        throw new IllegalArgumentException("Invalid print");
    }
}