package parser.syntax;

import java.util.Iterator;

import parser.syntax.result.SyntaxResult;
import token.Token;

public interface SyntaxParser {

  SyntaxResult syntaxParse(Iterator<Token> tokens);
}
