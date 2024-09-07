package parser.semantic;

import token.TokenType;

public class TypeValidator {
    public static boolean validateType(TokenType variableType, TokenType expressionType) {
        if (variableType == TokenType.NUMBER_TYPE && expressionType == TokenType.NUMBER) {
            return true;
        } else if (variableType == TokenType.STRING_TYPE && expressionType == TokenType.STRING) {
            return true;
        } else if (variableType == TokenType.BOOLEAN_TYPE && expressionType == TokenType.BOOLEAN) {
            return true;
        } else if(expressionType == TokenType.IDENTIFIER){
            return true;
        } else {
            return expressionType == TokenType.READ_INPUT;
        }
    }
}
