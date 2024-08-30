package parser.syntax;

import java.util.Iterator;
import java.util.List;

import parser.syntax.result.SyntaxResult;
import token.Token;

public interface SyntaxParser {

  SyntaxResult syntaxParse(Iterator<Token> tokens);
}
